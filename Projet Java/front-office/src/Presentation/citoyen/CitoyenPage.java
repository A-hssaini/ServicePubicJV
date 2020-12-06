package Presentation.citoyen;

import Metier.Demande;
import Metier.Etat;
import Metier.Procedure;
import Presentation.Tools.*;
import Presentation.employe2.ConsulterDemandeModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CitoyenPage extends JFrame {
	HashMap<String, String> HashDemande; /* nom_procedure-doc[0-9]: path */
	HashMap<String, Integer> HashProcedures; /* nom_procedure: ordre */
	SuivreDemandeModel ListDemandesModel;
	ToolTextField recherche;
	String ArrayOfProcedures[];
	JPanel panelSelectProcedure, panelRecherche, DeposerPanel, SuivrePanel;
	JScrollPane scrollPaneD, scrollPaneS;
	ToolButton Envoyer;
	JTabbedPane Tabs;
	JTable ListDocuments, ListDemandes;
	JLabel labelProcedures;
	JComboBox BoxProcedures;
	String cin;
	private Controleur controleur;

	public CitoyenPage(String cin, Controleur controleur) {
		super("service-public");

		/////
		this.controleur = controleur;
		this.cin = cin;
		setDefaultOperations();
		deposerOnglet();
		suivreOnglet();
		setBgForComponents();
		add(Tabs);
	}

	private void suivreOnglet() {
		initAttributesSuivre();
		SuivrePanel.setLayout(new BorderLayout());
		SuivrePanel.add(panelRecherche, BorderLayout.NORTH);
		SuivrePanel.add(scrollPaneS, BorderLayout.CENTER);
		Tabs.add("Suivre demande", SuivrePanel);

		Runnable helloRunnable = new Runnable() {//////////////////
			public void run() {
				ListDemandesModel = new SuivreDemandeModel(controleur.getCetoyenDemande(cin));
				ListDemandes.setModel(ListDemandesModel);/////////////////////////////////////////
				tableDemCustomize();
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);

	}

	private void deposerOnglet() {
		initAttributesDeposer();
		DeposerPanel.setLayout(new BorderLayout());
		DeposerPanel.add(panelSelectProcedure, BorderLayout.NORTH);
		DeposerPanel.add(scrollPaneD, BorderLayout.CENTER);
		DeposerPanel.add(Envoyer.button, BorderLayout.SOUTH);
		Tabs.add("Déposer demande", DeposerPanel);
	}

	private void initAttributesSuivre() {
		SuivrePanel = new JPanel();
		recherche = new ToolTextField("Rechercher par jeton, procédure...", true, "resrc/search.png", 25, 25);
		panelRecherche = new JPanel(new FlowLayout());
		ListDemandesModel = new SuivreDemandeModel(controleur.getCetoyenDemande(this.cin));
		ListDemandes = new JTable(ListDemandesModel);
		scrollPaneS = new JScrollPane(ListDemandes);
		initScrollPane(scrollPaneS);
		initTableDemandes();

		recherche.setPreferredSize(new Dimension(700, 35));

		recherche.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				updateListDem();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				updateListDem();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				updateListDem();
			}

		});
		panelRecherche.add(recherche);
		panelRecherche.setBackground(Color.GRAY);
		panelRecherche.setBorder(new EmptyBorder(5, 10, 0, 10));

	}

	private void initTableDemandes() {
		tableDemCustomize();
		ListDemandes.getTableHeader().setPreferredSize(new Dimension(800, 50));
		ListDemandes.getTableHeader().setFont(new Font("Gill Sans", Font.PLAIN, 24));
		ListDemandes.getTableHeader().setBackground(new Color(128, 128, 128));
		ListDemandes.getTableHeader().setForeground(Color.WHITE);
		ListDemandes.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = ListDemandes.rowAtPoint(e.getPoint());
				int col = ListDemandes.columnAtPoint(e.getPoint());
				miseajourDoc(row, col);
			}
		});
	}

	private void miseajourDoc(int row, int col) {
		if (col == 3 && ListDemandesModel.getDemandes().get(row).getEtat() == Etat.MISEAJOUR) {
			setVisible(false);
			UpdateDemande miseajour = new UpdateDemande(ListDemandesModel.getDemandes().get(row).getProcedure(),
					ListDemandesModel.getDemandes().get(row), this);
			miseajour.setVisible(true);
		}
	}

	private void tableDemCustomize() {
		TableCellRenderer tableRenderer = ListDemandes.getDefaultRenderer(JPanel.class);
		ListDemandes.getTableHeader().setReorderingAllowed(false);
		ListDemandes.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
		ListDemandes.getColumnModel().getColumn(3).setHeaderRenderer(new SimpleHeaderRenderer());
		ListDemandes.getColumnModel().getColumn(0).setCellRenderer(new ListDemsRenderer());
		ListDemandes.getColumnModel().getColumn(1).setCellRenderer(new ListDemsRenderer());
		ListDemandes.getColumnModel().getColumn(2).setCellRenderer(new ListDemsRenderer());
		TableColumnModel columnModel = ListDemandes.getColumnModel();
		columnModel.getColumn(3).setMaxWidth(50);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(0).setPreferredWidth(300);
		ListDemandes.setRowHeight(50);
		colorListDemsCells();
	}

	private void initAttributesDeposer() {
		DeposerPanel = new JPanel();
		Tabs = new JTabbedPane();
		Envoyer = new ToolButton("Envoyer", "déposer la demande", 500, 35);
		ListDocuments = new JTable(new DeposerDocumentModel(controleur.getAllProcedure().get(0)));// verifier s'il y a
																									// au moins une
		// procedure !!!!!
		scrollPaneD = new JScrollPane(ListDocuments);
		panelSelectProcedure = new JPanel(new FlowLayout());
		labelProcedures = new JLabel("Procédures");
		initScrollPane(scrollPaneD);
		initTableDocuments();
		initBoxProcedures();
		initDeposerPanel();
	}

	private void initBoxProcedures() {
		BoxProcedures = new JComboBox(ArrayOfProcedures);
		BoxProcedures.setBorder(new EmptyBorder(5, 10, 0, 10));
		BoxProcedures.setPreferredSize(new Dimension(700, 35));
		BoxProcedures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateListDoc();
			}
		});
	}

	public void updateListDem() {
		ArrayList<Demande> SelectDem = new ArrayList<Demande>();
		for (int i = 0; i < controleur.getCetoyenDemande(this.cin).size(); i++) {
			if (controleur.getCetoyenDemande(this.cin).get(i).getJeton().toLowerCase()
					.contains(recherche.getText().toLowerCase())
					|| controleur.getCetoyenDemande(this.cin).get(i).getProcedure().getNom().toLowerCase()
							.contains(recherche.getText().toLowerCase())) {
				SelectDem.add(controleur.getCetoyenDemande(this.cin).get(i));
			}
		}
		ListDemandesModel = new SuivreDemandeModel(SelectDem);
		ListDemandes.setModel(ListDemandesModel);/////////////////////////////////////////
		tableDemCustomize();

	}

	private void updateListDoc() {
		ListDocuments.setModel(new DeposerDocumentModel(
				controleur.getAllProcedure().get(HashProcedures.get(BoxProcedures.getSelectedItem()))));
		tableDocCustomize();
	}

	private void initTableDocuments() {
		tableDocCustomize();
		ListDocuments.getTableHeader().setPreferredSize(new Dimension(800, 50));
		ListDocuments.getTableHeader().setFont(new Font("Gill Sans", Font.PLAIN, 24));
		ListDocuments.getTableHeader().setBackground(new Color(128, 128, 128));
		ListDocuments.getTableHeader().setForeground(Color.WHITE);
		ListDocuments.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = ListDocuments.rowAtPoint(e.getPoint());
				int col = ListDocuments.columnAtPoint(e.getPoint());
				uploadDoc(row, col);
			}
		});
		loadProcedures();
	}

	private void initScrollPane(JScrollPane scrollpane) {
		scrollpane.getViewport().setBackground(Color.lightGray);
		scrollpane.setBorder(new EmptyBorder(20, 10, 10, 10));
		scrollpane.setBackground(new Color(128, 128, 128));
	}

	private void ValiderDemande() {
		/* verifier qu'il n'existe pas une 'encore demande' de meme procedure */
		for (int i = 0; i < controleur.getAllProcedure().get(HashProcedures.get(BoxProcedures.getSelectedItem()))
				.getNbrDocuments(); i++) {
			if (!HashDemande.containsKey((String) BoxProcedures.getSelectedItem() + String.valueOf(i))) {
				JOptionPane.showMessageDialog(this, "Des documents manquants", "Alert", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		Demande demande = new Demande("new",
				controleur.getAllProcedure().get(HashProcedures.get(BoxProcedures.getSelectedItem())), Etat.ENCORE);
		demande.setCin(cin);
		for (int i = 0; i < controleur.getAllProcedure().get(HashProcedures.get(BoxProcedures.getSelectedItem()))
				.getNbrDocuments(); i++) {
			demande.addDocsPath(HashDemande.get((String) BoxProcedures.getSelectedItem() + String.valueOf(i)));
		}
	//	demande.displayPaths();
		/* send it to Metier/DB */
		controleur.insertNewDemande(demande);

		JOptionPane.showMessageDialog(this, "Demande déposée avec succès", "Info", JOptionPane.INFORMATION_MESSAGE);

		////////////////////////////////////////
	}

	public void tableDocCustomize() {
		TableCellRenderer tableRenderer = ListDocuments.getDefaultRenderer(JPanel.class);
		ListDocuments.getTableHeader().setReorderingAllowed(false);
		ListDocuments.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
		ListDocuments.getColumnModel().getColumn(0).setHeaderRenderer(new SimpleHeaderRenderer());
		ListDocuments.getColumnModel().getColumn(1).setCellRenderer(new ListDocsRenderer());
		TableColumnModel columnModel = ListDocuments.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(50);
		columnModel.getColumn(1).setPreferredWidth(750);
		ListDocuments.setRowHeight(50);
		colorListDocsCells();
	}

	private void colorListDemsCells() {
		for (int row = 0; row < ListDemandes.getRowCount(); row++) {
			ListDemandes.prepareRenderer(ListDemandes.getCellRenderer(row, 0), row, 0).setBackground(Color.lightGray);
			ListDemandes.prepareRenderer(ListDemandes.getCellRenderer(row, 1), row, 1).setBackground(Color.lightGray);
			ListDemandes.prepareRenderer(ListDemandes.getCellRenderer(row, 2), row, 2).setBackground(Color.lightGray);
		}
	}

	private void setBgForComponents() {
		Tabs.setBackground(Color.GRAY);
		DeposerPanel.setBackground(new Color(128, 128, 128));
		SuivrePanel.setBackground(Color.GRAY);
		this.getContentPane().setBackground(Color.darkGray);
	}

	private void colorListDocsCells() {
		for (int row = 0; row < ListDocuments.getRowCount(); row++) {
			Component comp = ListDocuments.prepareRenderer(ListDocuments.getCellRenderer(row, 1), row, 1);
			comp.setBackground(Color.lightGray);
		}
	}

	private void initDeposerPanel() {
		labelProcedures.setPreferredSize(new Dimension(100, 35));
		labelProcedures.setForeground(Color.WHITE);
		Envoyer.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ValiderDemande();
			}
		});
		panelSelectProcedure.setBackground(new Color(128, 128, 128));
		panelSelectProcedure.add(labelProcedures);
		panelSelectProcedure.add(BoxProcedures);
	}

	public void copyFile(String oldFile, String newFile) throws IOException {
//        https://coderanch.com/t/423573/java/Passing-wilcard-Runtime-exec-command
//        String[] b = new String[] {"bash", "-c", "cp -R \"" + strSource + "/\"* \"" + strDestination + "/\""};
//        p = Runtime.getRuntime().exec(b);
		File source = new File(oldFile);
		File dest = new File(newFile);

		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest)) {

			byte[] buffer = new byte[1024];
			int length;

			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		}
	}

	public void uploadDoc(int row, int col) {
		if (col == 0) {
			JFileChooser choix = new JFileChooser();
			int retour = choix.showOpenDialog(this);
			if (retour == JFileChooser.APPROVE_OPTION) {/* un fichier a été choisi (sortie par OK) */
				choix.getSelectedFile().getName();/* nom du fichier choisi */
				choix.getSelectedFile().getAbsolutePath();/* chemin absolu du fichier choisi */
				JOptionPane
						.showMessageDialog(this,
								"fichier: " + choix.getSelectedFile().getName() + " a été choisi\npath: "
										+ choix.getSelectedFile().getAbsolutePath(),
								"Info", JOptionPane.INFORMATION_MESSAGE);
				HashDemande.put((String) BoxProcedures.getSelectedItem() + String.valueOf(row),
						choix.getSelectedFile().getAbsolutePath()); /* nom_procedure[0-9]: path */
				controleur.getAllProcedure().get(HashProcedures.get(BoxProcedures.getSelectedItem()))
						.setUploadedDocument(row, true);
				updateListDoc();
			}
		}
	}

	private void loadProcedures() {
		ArrayOfProcedures = new String[controleur.getAllProcedure().size()];
		HashProcedures = new HashMap<String, Integer>();
		HashDemande = new HashMap<String, String>();
		for (int i = 0; i < controleur.getAllProcedure().size(); i++) {
			ArrayOfProcedures[i] = controleur.getAllProcedure().get(i).getNom();
			HashProcedures.put(ArrayOfProcedures[i], i);
		}

	}

	private void setDefaultOperations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer lors du clic sur la
														// croix
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		setSize(900, 700);
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
}
