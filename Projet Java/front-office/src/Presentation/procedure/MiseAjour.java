package Presentation.procedure;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MiseAjour extends JFrame implements ActionListener {
	private Controleur controleur;
	private JButton demander;
	private JPanel pan, pan1;
	private GridBagConstraints c;
	private ArrayList<JCheckBox> boxs;
	private ArrayList<String> nameDocs;
	private HashMap<String, String> hash;
	private String etape;
	private ArrayList<Boolean> docMiseAjoure;
	private static final long serialVersionUID = 1L;

	private void initComponents(HashMap<String, String> hash, String etape) {
		this.hash = new HashMap<>(hash);
		this.docMiseAjoure = new ArrayList<Boolean>();
		this.etape = etape;
		this.setTitle("Service-public");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		c = new GridBagConstraints();
	}

	private void attachComponents() {
		pan = new JPanel(new GridBagLayout());
		pan.setBorder(new TitledBorder("Mise a jour"));
		pan1 = new JPanel();
		this.boxs = new ArrayList<JCheckBox>();
		demander = new JButton("demander");
		demander.setPreferredSize(new Dimension(100, 25));
		demander.setForeground(Color.black);
		demander.setBackground(Color.WHITE);
		demander.addActionListener(this);
		Iterator it = hash.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			c.gridx = 0;
			c.gridy = i;
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			boxs.add(new JCheckBox(key, false));
			boxs.get(i).setBounds(100, 100, 100, 100);
			pan.add(boxs.get(i), c);
			i++;
		}
		pan1.add(demander);
		this.getContentPane().add(pan, BorderLayout.CENTER);
		this.getContentPane().add(pan1, BorderLayout.SOUTH);
	}

	MiseAjour(HashMap<String, String> hash, Controleur controleur) {
		this.controleur = controleur;
		initComponents(hash, etape);
		attachComponents();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.dispose();
		for (int i = 0; i < boxs.size(); i++) {
			if (boxs.get(i).isSelected() == true)
				docMiseAjoure.add(false);
			else {
				docMiseAjoure.add(true);
			}

		}
		controleur.updateDocument(docMiseAjoure);
		Rapport rapp = new Rapport(hash, this.controleur, 2);
		rapp.setVisible(true);
	}

}