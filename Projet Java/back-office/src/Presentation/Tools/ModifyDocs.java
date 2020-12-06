package Presentation.Tools;

import Metier.Document;
import Metier.Etape;
import Metier.Procedure;
import Metier.TraitementDocument;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyDocs extends JFrame  implements  ActionListener{
    private JPanel panel,panel1,panel2;
    private JScrollPane pane;
    private ArrayList<JCheckBox> boxes;
    private JButton supp,ajouter;
    Procedure procedure;
    JTable procJTable;
    AjoutDoc ajoutDoc;

    public ModifyDocs(Procedure proc, JTable jTable){
        super();
        procedure = proc;
        procJTable = jTable;
        this.setTitle("Service-public");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        boxes = new ArrayList<JCheckBox>();
        panel = new JPanel(new GridLayout(boxes.size(), 1 ));
        int i=0;
        while (i < proc.getDocument().size()){
            boxes.add(new JCheckBox(proc.getDocument().get(i).getNom(),false));
            panel.add(boxes.get(i));
            i++;
        }
        panel1 = new JPanel(new GridBagLayout());
        panel2 = new JPanel();
        pane = new JScrollPane(panel);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(200, 150));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "Modification des documents", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.white));
        panel1.setBackground(new Color(53,54,58));
        ajouter = new JButton("Ajouter");
        ajouter.setBorderPainted(true);
        ajouter.setContentAreaFilled(false);
        ajouter.setFocusPainted(true);
        ajouter.setVerticalTextPosition(SwingConstants.BOTTOM);
        ajouter.setHorizontalTextPosition(SwingConstants.LEFT);
        ajouter.setFont(new Font("Gill Sans", 0, 12));
        ajouter.setForeground(Color.BLACK);
        ajouter.setBackground(Color.WHITE);
        ajouter.addActionListener(this);

        supp = new JButton("Supprimer");
        supp.setBorderPainted(true);
        supp.setContentAreaFilled(false);
        supp.setFocusPainted(true);
        supp.setVerticalTextPosition(SwingConstants.BOTTOM);
        supp.setHorizontalTextPosition(SwingConstants.RIGHT);
        supp.setFont(new Font("Gill Sans", 0, 12));
        supp.setForeground(Color.BLACK);
        supp.setBackground(Color.WHITE);
        supp.addActionListener(this);
        panel2.add(ajouter);
        panel2.add(supp);
        panel1.add(pane);

        this.add(panel1,BorderLayout.CENTER);
        this.add(panel2,BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed (ActionEvent actionEvent){
        if(actionEvent.getSource()== ajouter){
            this.dispose();
            ajoutDoc = new AjoutDoc(procedure, procJTable);
            ajoutDoc.setVisible(true);
        }
        else if(actionEvent.getSource() == supp){
            int size = procedure.getDocument().size();
            ArrayList<Etape> etapes = procedure.getEtape();
            for(int i = 0;i < size; i++)
            {
                if(boxes.get(i).isSelected())
                {
                    String nom = procedure.getDocument().get(i).getNom();
                    Document document = procedure.getDocument().get(i);
                    //Supprimer le document de la base de données
                    TraitementDocument.delete(document);
                    procedure.getDocument().remove(document);
                    removeDocFromEtape(procedure.getEtape(),nom);
                }
            }
            ArrayList<Procedure> procedures = ((ModelProcedure)procJTable.getModel()).getMesProcedure();
            procJTable.setModel(new ModelProcedure(procedures));
            this.dispose();
        }
    }

    public static void removeDocFromEtape(ArrayList<Etape> etapes, String NomDoc)
    {
        for(int j = 0; j < etapes.size(); j++)
        {
            int size1 = etapes.get(j).getDocument().size();
            Etape etape = etapes.get(j);
            for(int k = 0;k < size1 ; k++)
            {
                if(etape.getDocument().get(k).getNom().compareTo(NomDoc) == 0)
                {
                    Document document = etape.getDocument().get(k);
                    etape.getDocument().remove(document);
                }
            }
        }
    }
}
