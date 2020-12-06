package Presentation.procedure;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;

public class Rapport extends JFrame implements ActionListener {
	private Controleur controleur;
	private JScrollPane scroller;
	private JScrollBar scroll;
	private JTextArea text;
	private static final long serialVersionUID = 1L;
	private JButton valider;
	private JPanel panel, panel1;
	private GridBagConstraints c;
	private HashMap<String, String> hash;
	private String etape;
	private Container contenu;
	private Integer choix;

	private void initComponents(HashMap<String, String> hash, String etape) {
		this.setTitle("Service-public");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.hash = new HashMap<String, String>(hash);
		this.etape = etape;
		contenu = this.getContentPane();
		panel = new JPanel(new GridBagLayout());
		panel1 = new JPanel();
	}

	private void attachComponents() {
		panel.setBorder(new TitledBorder("Rapport"));
		valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(80, 20));
		valider.setForeground(Color.black);
		valider.setBackground(Color.WHITE);
		valider.addActionListener(this);

		text = new JTextArea("Rediger un rapport qui justifie la décision....");
		text.setPreferredSize(new Dimension(300, 300));
		scroller = new JScrollPane(text);
		scroll = new JScrollBar();
		scroller.add(scroll);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel1.add(valider);
		panel.add(scroller);
		contenu.add(panel, BorderLayout.CENTER);
		contenu.add(panel1, BorderLayout.SOUTH);
	}

	/*private void action() {
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fff");
			controleur.valideEtape();
		dispose();
			}

		});
	}*/

	public Rapport(HashMap<String, String> hash, Controleur controleur, Integer choix) {
		this.controleur = controleur;
		this.choix = choix;
		initComponents(hash, "le rapport");
		attachComponents();

	}

	

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	
		if(controleur.valideEtape(this.choix) == 0)
			JOptionPane.showMessageDialog(this, "etape deja traite", "Alert", JOptionPane.ERROR_MESSAGE);
		this.dispose();
		//ServicePublic serv = new ServicePublic(this.hash, this.etape, "ddd");
		//serv.setVisible(true);
	}
}
