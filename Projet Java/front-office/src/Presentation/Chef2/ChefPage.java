package Presentation.Chef2;

import Metier.Demande;
import Presentation.Tools.JTableButtonRenderer;
import Presentation.Tools.OpenFile;
import Presentation.Tools.SimpleHeaderRenderer;
import Presentation.citoyen.SuivreDemandeModel;
import Presentation.procedure.ServicePublic;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChefPage extends JFrame {

	private JScrollPane scrollPaneNouvelles;
	private JTable tableNouvelles;
	private JScrollPane scrollPaneConsulter;
	private JTable tableConsulter;
	private JTabbedPane OngletsHandler;
	private String login;

	private Controleur controleur;

	public ChefPage(String login, Controleur controleur) {
		this.controleur = controleur;
		this.login = login;
		this.controleur.setChefPage(this);
		setDefaultOperations();
		OngletsHandler = new JTabbedPane();
	//	if (controleur.getNvDemandes().size() > 0) {
			initNouvellesOnglet();
			OngletsHandler.add("Nouvelles", scrollPaneNouvelles);
	//	}
		initConsulterOnglet();
		OngletsHandler.add("Consulter demandes", scrollPaneConsulter);
		add(OngletsHandler);

		Runnable helloRunnable = new Runnable() {//////////////////
			public void run() {
				tableNouvelles.setModel(new NouvelleDemandeModel(controleur.getNvDemandes()));
				tableConsulter.setModel(new ConsulterDemandeModel(controleur.getEmployeDemande(login)));
				// tableConsulterCustomize();
				// tableConsulterCustomize();

			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
	}

	private void tableNouvellesEvents(int col, int row) {
		if (col == 2) {
			controleur.getNvDemandes().get(row).genererJeton();
			controleur.initTraiter(controleur.getNvDemandes().get(row).getId());
	///		controleur.getEmployeDemande(this.login).add(controleur.getNvDemandes().get(row));
			// controleur.getNvDemandes().remove(row);
			tableNouvellesCustomize();
			tableConsulterCustomize();
//			if (controleur.getNvDemandes().size() == 0)/////////////////
//				OngletsHandler.remove(0);
			JOptionPane.showMessageDialog(this, "generer", "Alert", JOptionPane.ERROR_MESSAGE);
		} else if (col == 3) {
			for (int i = 0; i < controleur.getNvDemandes().get(row).getAllDocsPath().size(); i++) {
				String path = controleur.getNvDemandes().get(row).getDocsPath(i);
				if (path != null) {
					int dialogResult = JOptionPane.showConfirmDialog(this,
							(i == 0 ? "le " + String.valueOf(i + 1) + "er" : "le " + String.valueOf(i + 1) + "ème")
									+ " document est rempli, Voulez-vous l'ouvrir ?",
							"Info", JOptionPane.INFORMATION_MESSAGE);
					if (dialogResult == JOptionPane.YES_OPTION) {
						new OpenFile(path);
					}
				} else
					JOptionPane.showMessageDialog(this,
							(i == 0 ? "le " + String.valueOf(i + 1) + "er " : "le " + String.valueOf(i + 1) + "ème ")
									+ "document est manquant",
							"Alert", JOptionPane.ERROR_MESSAGE);
			}
		} else if (col == 4) {
			// controleur.getNvDemandes().remove(row);
			controleur.removeDemande(this.controleur.getNvDemandes().get(row).getId());
			tableNouvellesCustomize();
///			if (controleur.getNvDemandes().size() == 0)
//				OngletsHandler.remove(0);
			JOptionPane.showMessageDialog(this, "supprimer", "Alert", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void tableConsulterEvents(int col, int row) {
		if (col == 3) {////////////////////////

			controleur.setMesDemandes(controleur.getEmployeDemande(this.login));
			controleur.showDocument(row);
			// mise.setVisible(true);
		} else if (col == 4) {
			JOptionPane.showMessageDialog(this, "Cette demande est archivé avec succès", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			// controleur.getEmployeDemande(this.login).remove(row);
			tableConsulterCustomize();
		}
	}

	private void initNouvellesOnglet() {
		tableNouvelles = new JTable();
		scrollPaneNouvelles = new JScrollPane(tableNouvelles);
		tableNouvellesCustomize();
		tableNouvelles.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = tableNouvelles.rowAtPoint(e.getPoint());
				int col = tableNouvelles.columnAtPoint(e.getPoint());
				tableNouvellesEvents(col, row);
			}
		});

	}

	private void tableNouvellesCustomize() {
		tableNouvelles.setModel(new NouvelleDemandeModel(controleur.getNvDemandes()));
		tableNouvelles.getTableHeader().setReorderingAllowed(false);
		TableCellRenderer tableRenderer = tableNouvelles.getDefaultRenderer(JPanel.class);
		tableNouvelles.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
		tableNouvelles.getColumnModel().getColumn(4).setHeaderRenderer(new SimpleHeaderRenderer());
		tableNouvelles.getColumnModel().getColumn(3).setHeaderRenderer(new SimpleHeaderRenderer());
		tableNouvelles.getColumnModel().getColumn(2).setHeaderRenderer(new SimpleHeaderRenderer());
		// tableNouvelles.getTableHeader().setBackground(new Color(237, 237, 237));
		tableNouvelles.getTableHeader().setPreferredSize(new Dimension(800, 50));
		tableNouvelles.getTableHeader().setFont(new Font("Gill Sans", Font.PLAIN, 24));
		TableColumnModel columnModel = tableNouvelles.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(280);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(70);
		columnModel.getColumn(4).setPreferredWidth(50);
		tableNouvelles.setRowHeight(50);
	}

	private void initConsulterOnglet() {
		tableConsulter = new JTable();
		scrollPaneConsulter = new JScrollPane(tableConsulter);
		tableConsulterCustomize();
		tableConsulter.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = tableConsulter.rowAtPoint(e.getPoint());
				int col = tableConsulter.columnAtPoint(e.getPoint());
				tableConsulterEvents(col, row);
			}
		});

	}

	private void tableConsulterCustomize() {
		tableConsulter.setModel(new ConsulterDemandeModel(controleur.getEmployeDemande(this.login)));
		tableConsulter.getTableHeader().setReorderingAllowed(false);
		TableCellRenderer tableRenderer = tableConsulter.getDefaultRenderer(JPanel.class);
		tableConsulter.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
		tableConsulter.getColumnModel().getColumn(4).setHeaderRenderer(new SimpleHeaderRenderer());
		tableConsulter.getColumnModel().getColumn(3).setHeaderRenderer(new SimpleHeaderRenderer());
		tableConsulter.getTableHeader().setPreferredSize(new Dimension(750, 50));
		tableConsulter.getTableHeader().setFont(new Font("Gill Sans", Font.PLAIN, 24));
		TableColumnModel columnModel = tableConsulter.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setMaxWidth(70);
		columnModel.getColumn(4).setMaxWidth(50);
		tableConsulter.setRowHeight(50);
	}

	private void setDefaultOperations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer lors du clic sur la
														// croix
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		setSize(850, 700);
		// setResizable(false); //On interdit la redimensionnement de la fenêtre
		setMinimumSize(new Dimension(900, 700));
		closeEvent();
	}

	private void closeEvent() {
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void restartPage() {
		tableNouvelles.setModel(new NouvelleDemandeModel(controleur.getNvDemandes()));
		tableConsulter.setModel(new ConsulterDemandeModel(controleur.getEmployeDemande(login)));
	}
}
