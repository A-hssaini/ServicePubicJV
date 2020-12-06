package Presentation.employe;

import Presentation.chef.ConsulterDemande;
import Presentation.chef.ConsulterDemandeModel;
import Presentation.chef.NouvelleDemandeModel;
import Presentation.procedure.ServicePublic;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AbstractDocument.Content;

import DAO.Controleur;
import Metier.Demande;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.*;

public class TraiterCommandePage extends JFrame {
	private JScrollPane scrollPane;
	private JTable table;
	private Controleur controleur;

	public TraiterCommandePage(Controleur controleur) {
		super();
		this.controleur = controleur;
		this.controleur.setTraiterCommandePage(this);
		TableCellRenderer tableRenderer;
		table = new JTable(new TraitrerComandeModel(controleur.getMesDemandes()));
		tableRenderer = table.getDefaultRenderer(JPanel.class);
		table.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
		table.getColumnModel().getColumn(2).setHeaderRenderer(new SimpleHeaderRenderer());
		table.setGridColor(new java.awt.Color(238, 238, 238));
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(new Color(73, 73, 73));
		setLayout(new BorderLayout(50, 5));
		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createTitledBorder("Formulaire"));
		panel3.add(scrollPane, BorderLayout.CENTER);
		// panel3.setBackground(new Color(73, 73, 73));
		add(panel3, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 500);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (col == 2) {
					controleur.showDocument(row);
			//////
				}
			}
		});
	setVisible(true);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void resatartTable() {
		 TraitrerComandeModel cModel = new TraitrerComandeModel(controleur.getMesDemandes());
		 table.setModel(cModel);
	}

}