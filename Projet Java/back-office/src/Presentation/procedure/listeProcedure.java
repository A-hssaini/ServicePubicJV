package Presentation.procedure;

import Metier.Procedure;
import Metier.TraitementProcedure;
import Presentation.Tools.ModelProcedure;
import Presentation.Tools.ProcedureMouseListner;
import Presentation.Tools.ToolTextField;
import Presentation.Tools.ajoutProc;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class listeProcedure implements ActionListener {
    JPanel main;
    ToolTextField recherche;
    JPanel jPanel1;
    JButton ajouter;
    ArrayList<Procedure> procedure;
    JTable jTable;
    JScrollPane jScrollPane;
    ajoutProc ajoutProc;

    public listeProcedure(ArrayList<Procedure> procedures) {
        this.procedure = procedures;
        main = new JPanel(new BorderLayout());
        jPanel1 = new JPanel(new FlowLayout());
        recherche = new ToolTextField("Recherche ici", true);
        jTable = new JTable(new ModelProcedure(procedure));
        jTable.addMouseListener(new ProcedureMouseListner(this.jTable, procedure));
        jScrollPane = new JScrollPane(jTable);
        recherche.setPreferredSize(new Dimension(200,20));
        recherche.setIcon(new ImageIcon("./resrc/search.png"));
        ajouter = new JButton("Ajouter");
        ajouter.addActionListener(this);
        ajouter.setPreferredSize(new Dimension(130,30));
        ajouter.setIcon(new ImageIcon("./resrc/add.jpg"));
        jPanel1.add(recherche);
        jPanel1.add(ajouter);
        main.add(jPanel1,BorderLayout.NORTH);
        main.add(jScrollPane,BorderLayout.SOUTH);
        jTable.setBackground(new Color(53,54,58));
        jTable.setGridColor(new Color(67, 255, 252));
        jTable.setForeground(new Color(255, 242, 226));
        recherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                filtre();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                filtre();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                filtre();
            }

            public void filtre()
            {
                ArrayList<Procedure> li1 = new ArrayList<Procedure>();
                String text = recherche.getText();
                procedure = TraitementProcedure.getAll();
                int taille = procedure.size();
                for(int i = 0;i < taille;i++)
                {
                    if(procedure.get(i).getName().contains(text))
                        li1.add(procedure.get(i));

                }
                jTable.setModel(new ModelProcedure(li1));
            }
        });

    }

    public JPanel getMain() {
        return main;
    }

    public ArrayList<Procedure> getProcedure() {
        return procedure;
    }

    public JTable getjTable() {
        return jTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ajoutProc = new ajoutProc(this);
        this.ajoutProc.setVisible(true);
    }
}
