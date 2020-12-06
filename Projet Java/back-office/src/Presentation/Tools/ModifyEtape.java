package Presentation.Tools;

import Metier.Document;
import Metier.Etape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyEtape extends JFrame implements ActionListener {
	JTable jTable, procJTable;
	ArrayList<Etape> etape;
	ArrayList<Document> document;
	JScrollPane jScrollPane;
	JButton ajouter;
	AjoutEtape ajoutEtape;
	int rowProc;

	public ModifyEtape(ArrayList<Etape> etape, ArrayList<Document> document, JTable procJTable, int rowProc)
	{
		this.setTitle("Etape contenue");
		this.setSize(600,400);
		this.setLayout(new BorderLayout());
		this.etape = etape;
		this.document = document;
		this.procJTable = procJTable;
		this.rowProc = rowProc;
		jTable = new JTable(new ModelEtape(this.etape));
		jTable.addMouseListener(new ModifyEtapeListner(jTable, document, this.procJTable, rowProc));
		jScrollPane = new JScrollPane(jTable);
		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(this);
		ajouter.setPreferredSize(new Dimension(80,30));
		ajouter.setIcon(new ImageIcon("./resrc/add.jpg"));
		this.add(jScrollPane, BorderLayout.CENTER);
		this.add(ajouter, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ajoutEtape = new AjoutEtape(this.jTable, this.procJTable, this.rowProc);
		ajoutEtape.setVisible(true);
	}
}
