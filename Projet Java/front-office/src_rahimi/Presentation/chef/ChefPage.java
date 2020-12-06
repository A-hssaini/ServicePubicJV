package Presentation.chef;

import Presentation.employe.*;
import Presentation.procedure.*;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import DAO.Controleur;

import static javax.swing.JOptionPane.showMessageDialog;

public class ChefPage extends JFrame{
	
	private Controleur controleur;
	private ArrayList<NouvelleDemande> mesDemandes;
	private ArrayList<ConsulterDemande> consulterListe;///
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPane2;
	private JTable table2;
	
	
	public ChefPage(ArrayList<NouvelleDemande> mesDemandes, ArrayList<ConsulterDemande> consulterListe) {
		super();
		this.mesDemandes = mesDemandes;
		this.consulterListe = consulterListe;
		
		JPanel newComandePanel  = new JPanel();
		JPanel ConsulterComandePanel  = new JPanel();
		JTabbedPane tp = new JTabbedPane();
	    tp.setBounds(50,50,200,200);  
	    tp.add("main",newComandePanel);  
	    tp.add("visit",ConsulterComandePanel);  
	 
		TableCellRenderer tableRenderer;
	      table = new JTable(new NouvelleDemandeModel(mesDemandes));
	      tableRenderer = table.getDefaultRenderer(JPanel.class);
	      table.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
	      table.getColumnModel().getColumn(2).setHeaderRenderer(new SimpleHeaderRenderer());
	      table.setGridColor(new java.awt.Color(238,238,238));
	      DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
	       renderer.setHorizontalAlignment( SwingConstants.CENTER );
	      scrollPane = new JScrollPane(table);
	      scrollPane.setBorder(BorderFactory.createEmptyBorder());
	      newComandePanel.setLayout(new BorderLayout(50,5));      
	      JPanel panel3 = new JPanel();
	      panel3.add(scrollPane, BorderLayout.CENTER);
	      newComandePanel.add(panel3,BorderLayout.CENTER);
	      
	      
	      
	      
	  	TableCellRenderer tableRenderer2;
	      table2 = new JTable(new ConsulterDemandeModel(consulterListe));
	      tableRenderer2 = table2.getDefaultRenderer(JPanel.class);
	      table2.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer2));
	      table2.getColumnModel().getColumn(3).setHeaderRenderer(new SimpleHeaderRenderer());
	      table2.getColumnModel().getColumn(4).setHeaderRenderer(new SimpleHeaderRenderer());
	      table2.setGridColor(new java.awt.Color(238,238,238));
	      DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer)table2.getDefaultRenderer(Object.class);
	       renderer2.setHorizontalAlignment( SwingConstants.CENTER );
	      scrollPane2 = new JScrollPane(table2);
	      scrollPane2.setBorder(BorderFactory.createEmptyBorder());
	      ConsulterComandePanel.setLayout(new BorderLayout(50,5));      
	      JPanel panel4 = new JPanel();
	      panel4.add(scrollPane2, BorderLayout.CENTER);
	      ConsulterComandePanel.add(panel4,BorderLayout.CENTER);
	      
	     
	     add(tp);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLocationRelativeTo(null);
	      setSize(600, 500);
	      setVisible(true);
	      
	     table.addMouseListener(new java.awt.event.MouseAdapter() {
	    	 public void mouseClicked(java.awt.event.MouseEvent e){
	    		 int row = table.rowAtPoint(e.getPoint());
	    		 int col = table.columnAtPoint(e.getPoint());
	    		 if(col == 2) {
					 mesDemandes.get(row).showPopup();
					 try {
						 consulterListe.add(new ConsulterDemande("H6@R534", mesDemandes.get(row).getProcedureString(), false));
						 ConsulterDemandeModel cModel = new ConsulterDemandeModel(consulterListe);
						 table2.setModel(cModel);
					 } catch (IOException ex) {
						 ex.printStackTrace();
					 }
					 mesDemandes.remove(row);
					 NouvelleDemandeModel fModel = new NouvelleDemandeModel(mesDemandes);
					 table.setModel(fModel);
				 }
	    	 }
	     });
	      
	      table2.addMouseListener(new java.awt.event.MouseAdapter() {
	    	 public void mouseClicked(java.awt.event.MouseEvent e){
	    		 int row=table2.rowAtPoint(e.getPoint());
	    		 int col= table2.columnAtPoint(e.getPoint());
	    		 if (col == 3) {
					 HashMap<String, String> hash = new HashMap<String, String>();
					 hash.put("Doc1","Desc de 1er document");
					 hash.put("Doc2","Desc de 2eme document");
					 hash.put("Doc3","Desc de 3eme document");
					 hash.put("Doc4","Desc de 4eme document");
					 ServicePublic mise = new ServicePublic(hash, controleur);
					 mise.setVisible(true);
				 }
	    		 if(col == 4) {
					 consulterListe.get(row).showPopup();
					 consulterListe.remove(row);
					 ConsulterDemandeModel cModel = new ConsulterDemandeModel(consulterListe);
					 table2.setModel(cModel);
				 }
	    		 }
	      });
	}
}
