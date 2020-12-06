package Presentation.procedure;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import DAO.Controleur;
import Presentation.Tools.OpenFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServicePublic extends JFrame implements ActionListener {
	private Controleur controleur;

	private static final long serialVersionUID = 1L;
	private JButton b1, b2, b3, b4, b5, b6;
	private JPanel panelHaut, panelBas, panelSud;
	private JLabel label, label2;
	private GridBagConstraints c;
	private Container contenu;
	private HashMap<String, String> hash;
	private String etape;
	private String jeton;

	private void initCompenents(HashMap<String, String> hash, String etape, String jeton) {

		this.setTitle("Service-public");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.hash = new HashMap<String, String>(hash);
		this.etape = new String(etape);
		this.jeton = new String(jeton);
		contenu = this.getContentPane();
		panelHaut = new JPanel(new GridBagLayout());
		panelBas = new JPanel(new GridBagLayout());
		panelSud = new JPanel(new FlowLayout());
		c = new GridBagConstraints();
		label = new JLabel(etape);
		label.setPreferredSize(new Dimension(60, 60));
		Iterator it = hash.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			c.gridx = 0;
			c.gridy = i + 1;
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			JLabel lab = new JLabel(key + ":" + value);
			lab.setPreferredSize(new Dimension(200, 20));
			panelHaut.add(lab, c);
			i++;
		}
		label2 = new JLabel(this.jeton);
		b1 = new JButton("Etat courant");
		b1.setPreferredSize(new Dimension(150, 20));
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Joy", Font.ITALIC, 12));
		b2 = new JButton("Voir les informations");
		b2.setPreferredSize(new Dimension(150, 20));
		b2.setFont(new Font("Joy", Font.ITALIC, 12));
		b2.setBackground(Color.WHITE);
		b2.addActionListener(this);
		b3 = new JButton("Accepter");
		b3.setPreferredSize(new Dimension(100, 20));
		b3.addActionListener(this);
		b3.setForeground(Color.black);
		b3.setBackground(Color.WHITE);
		b4 = new JButton("Mise a jour");
		b4.setPreferredSize(new Dimension(100, 20));
		b4.addActionListener(this);
		b4.setForeground(Color.black);
		b4.setBackground(Color.WHITE);
		b5 = new JButton("Refuser");
		b5.setPreferredSize(new Dimension(100, 20));
		b5.addActionListener(this);
		b5.setForeground(Color.black);
		b5.setBackground(Color.WHITE);
		b6 = new JButton("Rejeter");
		b6.setPreferredSize(new Dimension(100, 20));
		b6.addActionListener(this);
		b6.setForeground(Color.black);
		b6.setBackground(Color.WHITE);

	}

	private void initLayouts() {
		panelHaut.setBorder(new TitledBorder("Traiter demande"));
		c.gridx = 0;
		c.gridy = 0;
		panelHaut.add(label, c);
		c.gridx = 15;
		c.gridy = hash.size() + 2;
		panelHaut.add(b1, c);
		c.gridx = 15;
		c.gridy = hash.size() + 3;
		panelHaut.add(b2, c);
		panelSud.add(label2);
		c.gridx = 0;
		c.gridy = 1;
		panelBas.add(b3, c);
		c.gridx = 1;
		c.gridy = 1;
		panelBas.add(b4, c);
		c.gridx = 2;
		c.gridy = 1;
		panelBas.add(b5, c);
		c.gridx = 3;
		c.gridy = 1;
		panelBas.add(b6, c);
		panelBas.setPreferredSize(new Dimension(600, 50));
		panelBas.setBorder(new TitledBorder("Désicion"));
		contenu.add(panelHaut, BorderLayout.NORTH);
		contenu.add(panelBas, BorderLayout.CENTER);
		contenu.add(panelSud, BorderLayout.SOUTH);
	}

	public ServicePublic(HashMap<String, String> hash, Controleur controleur) {
		this.controleur = controleur;
		int row = controleur.getRow();
		initCompenents(hash,
				"Etape N" + controleur.getMesDemandes().get(row).getProcedure().getEtapes().get(0).getNumero(),
				controleur.getMesDemandes().get(row).getJeton());
		initLayouts();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == b2) {
			for (int i = 0; i < controleur.getMesDemandes().get(controleur.getRow()).getAllDocsPath().size(); i++) {
				String path = controleur.getMesDemandes().get(controleur.getRow()).getDocsPath(i);
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
			
		} else if (actionEvent.getSource() == b4) {
			this.dispose();
			MiseAjour update = new MiseAjour(this.hash, this.controleur);
			update.setVisible(true);
		} else if (actionEvent.getSource() == b3) {
			this.dispose();
			Rapport rapp = new Rapport(this.hash, this.controleur, 1);
			rapp.setVisible(true);
		} else if (actionEvent.getSource() == b5) {
			this.dispose();
			Rapport rapp = new Rapport(this.hash, this.controleur, 3);
			rapp.setVisible(true);
		} else if (actionEvent.getSource() == b6) {
			this.dispose();
			Rapport rapp = new Rapport(this.hash, this.controleur, 4);
			rapp.setVisible(true);

		}

	}

}
