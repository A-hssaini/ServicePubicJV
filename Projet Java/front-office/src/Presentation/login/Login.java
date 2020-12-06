package Presentation.login;

import Metier.Demande;
import Metier.Etat;
import Metier.Procedure;
import Presentation.Tools.*;
import Presentation.Chef2.*;
import Presentation.citoyen.CitoyenPage;
import Presentation.employe2.EmployePage;
import sun.security.util.Password;
import sun.tools.jar.resources.jar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends JFrame {
	GridBagConstraints gbc;
	cinVerifier cinVerifier;
	JPanel content;
	JLabel labelField, labelPwd;
	JPasswordField textPwd;
	JTextField textField;
	JLabel title;
	JButton login;

	public Login(String oneLabel) {
		super("service-public");
		setDefaultOperations();
		initAttributes(oneLabel, "");
		setPreferredSizeForAttributes();
		setAttributes(oneLabel, "");
		addAttributes(oneLabel, "");
		this.setContentPane(content);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					pageCitoyen();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		cinVerifier = new cinVerifier();
		textField.setInputVerifier(cinVerifier);
	}

	private void pageCitoyen() throws IOException {
		if (cinVerifier.verify(textField)) {
			/* Champ pour lire de la DB */
			ArrayList<Procedure> listProcedure = new ArrayList<>();
			Controleur controleur = new Controleur();
		//	listProcedure = controleur.getAllProcedure();
		
			/*
			 * Procedure proc1 = new Procedure("Passeport"); proc1.addDocument(
			 * "CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN CIN "
			 * , false); proc1.addDocument("CIN2", false); proc1.addDocument("CIN3", false);
			 * Procedure proc2 = new Procedure("CIN"); proc2.addDocument("DFGDFG1", false);
			 * proc2.addDocument("DFGDFG2", false); proc2.addDocument("DFGDFG3", false);
			 * Procedure proc3 = new Procedure("Extrait"); proc3.addDocument("sdfsd",
			 * false); proc3.addDocument("sdfsd2", false); proc3.addDocument("sdfsd3",
			 * false);
			 * 
			 * listProcedure.add(proc1);// listProcedure.add(proc2);////
			 * listProcedure.add(proc3);//
			 * 
			 * Procedure procUPDATE = new Procedure("CIN"); procUPDATE.addDocument("sdfsd",
			 * false); procUPDATE.addDocument("sdfsd2", false);
			 * procUPDATE.addDocument("sdfsd3", false);
			 * 
			 * ArrayList<Demande> listDemande = new ArrayList<>(); Demande dem1 = new
			 * Demande("G35GF33", proc1, Etat.ENCORE);// must add cin !! Demande dem2 = new
			 * Demande("F354334", proc2, Etat.ACCEPTE); Demande dem3 = new
			 * Demande("F343545", procUPDATE, Etat.MISEAJOUR); Demande dem4 = new
			 * Demande("T345345", proc3, Etat.REJETE); listDemande.add(dem1);
			 * listDemande.add(dem2); listDemande.add(dem3); listDemande.add(dem4);
			 */
		 String cin = textField.getText();
	///		ArrayList<Demande> listDemande = controleur.getCetoyenDemande(cin);

			CitoyenPage citoyenPage = new CitoyenPage(cin, controleur);

			citoyenPage.setVisible(true);
			dispose();
		} else if (textField.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Champ vide !", "Alert", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Le format CIN est incorrect!", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	public boolean isCIN(String cin) {
		return true;
	}

	Login(String oneLabel, String twoLabel) {
		super("service-public");
		setDefaultOperations();
		initAttributes(oneLabel, twoLabel);
		setPreferredSizeForAttributes();
		setAttributes(oneLabel, twoLabel);
		addAttributes(oneLabel, twoLabel);
		this.setContentPane(content);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					pageEmploye();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	private void pageEmploye() throws IOException {
		String login = new String(textField.getText());
		String pwd = new String(textPwd.getPassword());
		
		Controleur controleur = new Controleur();
		if (controleur.verifierConnexion(login, pwd)) {
			if (controleur.isChef()) {			
				ChefPage list = new ChefPage(login,controleur);
				list.setVisible(true);
			} else {
				controleur.showTraiterCommande(login);
			}
			dispose();
		}
		/*
		 * else if (textField.getText().equals("")) JOptionPane.showMessageDialog(this,
		 * "Champ Pseudo vide", "Alert", JOptionPane.ERROR_MESSAGE); else if
		 * (pwd.equals("")) JOptionPane.showMessageDialog(this,
		 * "Champ Mot de passe vide", "Alert", JOptionPane.ERROR_MESSAGE); else
		 * JOptionPane.showMessageDialog(this, "L'un des champs est incorrect!",
		 * "Alert", JOptionPane.ERROR_MESSAGE);
		 */
	}

	private void setPreferredSizeForAttributes() {
		login.setPreferredSize(new Dimension(150, 40));
		textField.setPreferredSize(new Dimension(200, 40));
		labelField.setPreferredSize(new Dimension(150, 40));
		labelField.setForeground(Color.white);
		title.setBorder(new EmptyBorder(0, 50, 0, 0));
		title.setPreferredSize(new Dimension(150, 40));
	}

	private void initAttributes(String oneLabel, String twoLabel) {
		content = new JPanel();
		gbc = new GridBagConstraints();
		login = new JButton("Connexion");
		textField = new JTextField();
		labelField = new JLabel(oneLabel, SwingConstants.RIGHT);
		title = new JLabel("Login", SwingConstants.CENTER);
		if (!twoLabel.equals("")) {
			textPwd = new JPasswordField();
			textPwd.setPreferredSize(new Dimension(200, 40));
			labelPwd = new JLabel(twoLabel, SwingConstants.RIGHT);
			labelPwd.setPreferredSize(new Dimension(150, 40));
			labelPwd.setForeground(Color.white);
		}
	}

	private void addAttributes(String oneLabel, String twoLabel) {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 40, 10);
		content.add(title, addConstraints(5, 0, 2, 1));
		gbc.insets = new Insets(2, 10, 2, 10);
		content.add(labelField, addConstraints(2, 3, 4, 1));
		content.add(textField, addConstraints(6, 3, 2, 1));
		if (!twoLabel.equals("")) {
			content.add(labelPwd, addConstraints(2, 4, 4, 1));
			content.add(textPwd, addConstraints(6, 4, 2, 1));
		}
		gbc.insets = new Insets(40, 10, 10, 10);
		content.add(login, addConstraints(5, 6, 4, 1));
	}

	private void setAttributes(String oneLabel, String twoLabel) {
		content.setLayout(new GridBagLayout());
		content.setBackground(new Color(53, 54, 58));
		title.setIcon(ToolButton.resizeIcon(new ImageIcon("./resrc/login.png"), 40, 40));
		title.setFont(new Font("Gill Sans", 0, 34));
		title.setForeground(new Color(239, 240, 241));
		title.setOpaque(false);
	}

	private void setDefaultOperations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à l'application de se fermer lors du clic sur la
														// croix
		setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		setSize(700, 438);
		setResizable(false); // On interdit la redimensionnement de la fenêtre
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

	private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridheight = gridheight;
		gbc.gridwidth = gridwidth;
		return gbc;
	}
}
