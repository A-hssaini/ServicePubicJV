package Presentation.Tools;
import Metier.Document;
import Metier.Etape;
import Metier.TraitementDocEtape;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Docdispo extends JFrame implements ActionListener {

     JPanel panel,panel1,panel2;
     JScrollPane pane;
     JButton button;
     ArrayList<JCheckBox> array;
     Etape etape;
     ArrayList<Document> documents;
     JTable jTable;

     public Docdispo(Etape etape , ArrayList<Document> docs, JTable jTable){
         super();
         this.jTable = jTable;
         this.etape = etape;
         this.documents = docs;
         this.setTitle("Service-public");
         this.setSize(500,500);
         this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.panel = panel;
         array = new ArrayList<JCheckBox>();
         panel = new JPanel(new  GridLayout(array.size(), 1 ));
         int i=0;
         while (i < docs.size()){
             if(etape.DocToString().contains(docs.get(i).getNom())) {
                 array.add(new JCheckBox(docs.get(i).getNom(), true));
                 panel.add(array.get(i));
             }
             else {
                 array.add(new JCheckBox(docs.get(i).getNom(), false));
                 panel.add(array.get(i));
             }
             i++;

         }

         panel1 = new JPanel();
         panel2 = new JPanel();
         pane = new JScrollPane(panel);
         pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         pane.setPreferredSize(new Dimension(200, 150));
         panel1.setBorder(BorderFactory.createTitledBorder(null, "Documents Disponibles", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.white));
         panel1.setBackground(new Color(53,54,58));
         button = new JButton("Valider");
         button.addActionListener(this);
         button.setBorderPainted(true);
         button.setContentAreaFilled(false);
         button.setFocusPainted(true);
         button.setOpaque(false);
         button.setBackground(null);
         button.setVerticalTextPosition(SwingConstants.BOTTOM);
         button.setHorizontalTextPosition(SwingConstants.RIGHT);
         button.setFont(new Font("Gill Sans", 0, 12));
         button.setForeground(Color.BLACK);
         button.setBackground(Color.WHITE);
         panel2.add(button);
         panel1.add(pane);

         this.add(panel1,BorderLayout.CENTER);
         this.add(panel2,BorderLayout.SOUTH);

     }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < array.size() ; i++)
        {
            if(array.get(i).isSelected())
            {
                if(!etape.DocToString().contains(array.get(i).getText())) {
                    //Ajouter la relation document étape dand la base de données
                    TraitementDocEtape.add(etape, documents.get(i));
                    etape.getDocument().add(new Document(documents.get(i)));
                }
            }
            else
            {
                if(etape.DocToString().contains(array.get(i).getText()))
                {
                    for(int k = 0; k < etape.getDocument().size(); k++)
                    {
                        if(etape.getDocument().get(k).getNom().compareTo(array.get(i).getText()) == 0)
                        {
                            Document document = etape.getDocument().get(k);
                            //Supprimer la relation document étape dand la base de données
                            TraitementDocEtape.delete(etape, document);
                            etape.getDocument().remove(document);
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<Etape> etapes = ((ModelEtape)jTable.getModel()).getMesEtape();
        jTable.setModel(new ModelEtape(etapes));
        this.dispose();
    }
}
