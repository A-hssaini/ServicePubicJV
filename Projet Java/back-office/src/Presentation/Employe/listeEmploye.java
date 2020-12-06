package Presentation.Employe;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Metier.Employe;
import Metier.TraitementEmploye;
import Presentation.Tools.*;
import Metier.Procedure;
import Presentation.procedure.listeProcedure;
public class listeEmploye extends JFrame{
    ModelTable modelTable;
    JTable jTable;
    JScrollPane jScrollPane;
    JPanel jPanel1;
    JPanel jPanel2,jPanel3,jPanel4;
    ToolTextField recherche;
    JButton jButton, modify;
    ArrayList<Employe> list;
    JTabbedPane jTabbedPane;
    listeProcedure listeProcedure;
    ArrayList<Procedure> procedures;
    public listeEmploye(ArrayList<Employe> li, ArrayList<Procedure> proc)
    {
        list = li;
        procedures = proc;
        listeProcedure = new listeProcedure(procedures);
        jTabbedPane = new JTabbedPane();
        modelTable = new ModelTable(list);
        jTable = new JTable(modelTable);
        jScrollPane = new JScrollPane(jTable);
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jPanel4.add(listeProcedure.getMain());
        jPanel3.setLayout(new BorderLayout());
        this.setTitle("Formulaire");
        this.setSize(600,600);
        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        recherche = new ToolTextField("Rechercher ici",true);
        recherche.setPreferredSize(new Dimension(200,20));
        recherche.setIcon(new ImageIcon("./resrc/search.png"));
        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        jButton = new JButton("Ajouter");
        jButton.setPreferredSize(new Dimension(130,30));
        jButton.setIcon(new ImageIcon("./resrc/add.jpg"));
        jPanel1.add(recherche);
        jPanel1.add(jButton);
        jPanel3.add(jPanel1,BorderLayout.NORTH);
        jPanel3.add(jScrollPane,BorderLayout.CENTER);
        jPanel3.add(jPanel2,BorderLayout.SOUTH);
        jTabbedPane.add("Employé",jPanel3);
        jTabbedPane.add("Procédure",jPanel4);
        this.add(jTabbedPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jTable.addMouseListener(new MouseListner(jTable,this.list));
        jTable.setBackground(new Color(53,54,58));
        jTable.setGridColor(new Color(67, 255, 252));
        jTable.setForeground(new Color(255, 242, 226));
        modify = new JButton("Modifier");
        modify.setPreferredSize(new Dimension(130,30));
        modify.setIcon(new ImageIcon("./resrc/modify.png"));
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyEmploye modifyEmploye = new ModifyEmploye(jTable, list);
                modifyEmploye.setVisible(true);
            }
        });
        jPanel2.add(modify);
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
                ArrayList<Employe> li1 = new ArrayList<Employe>();
                String text = recherche.getText();
                list = TraitementEmploye.getAll();
                int taille = list.size();
               for(int i = 0;i < taille;i++)
                {
                    if(list.get(i).getLogin().contains(text) || list.get(i).getNom().contains(text)
                            || list.get(i).getPrenom().contains(text) || list.get(i).getProcédures().contains(text)
                            || list.get(i).getEmail().contains(text))
                        li1.add(list.get(i));

                }
                jTable.setModel(new ModelTable(li1));
            }
        });
        jButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddEmploye add = new AddEmploye(jTable, list, recherche);
            add.setVisible(true);
        }
    });
    }

    public JTable getjTable() {
        return jTable;
    }

    public JTextField getRecherche() {
        return recherche;
    }
}