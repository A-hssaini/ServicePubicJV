package Presentation.Tools;

import Metier.Document;
import Metier.Etape;
import Metier.Procedure;
import Metier.TraitementEtape;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AjoutEtape extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField text;
    private JPanel panel;
    private JPanel panel1;
    private JButton button;
    private GridBagConstraints c;
    JTable jTable, procJTable;
    int rowProc;

    public AjoutEtape(JTable jTable, JTable procJTable, int rowProc){
        super("service-public");
        this.jTable = jTable;
        this.procJTable = procJTable;
        this.rowProc = rowProc;
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        label = new JLabel("numéro de l'étape:    ");
        text = new JTextField();
        panel = new JPanel(new GridBagLayout());
        panel1 = new JPanel();
        button = new JButton("valider");
        button.addActionListener(this);
        c = new GridBagConstraints();
        text.setPreferredSize(new Dimension(120,30));
        text.setBackground(Color.white);
        label.setForeground(Color.WHITE);
        panel.setBackground(new Color(53,54,58));
        panel.setBorder(BorderFactory.createTitledBorder(null, "nouvelle étape", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.white));
        c.gridy = 0; c.gridx = 0;
        panel.add(label,c);
        c.gridy = 0 ; c.gridx = 4;
        panel.add(text,c);
        button.setPreferredSize(new Dimension(80,20));
        button.setBorderPainted(true);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBackground(null);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Gill Sans", 0, 12));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        panel1.add(button);
        this.add(panel,BorderLayout.CENTER);
        this.add(panel1,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       try {
           int numero = Integer.parseInt(this.text.getText());
           ArrayList<Procedure> procedures = ((ModelProcedure)this.procJTable.getModel()).getMesProcedure();
           Etape etape = new Etape(this.Transforme(procedures.get(rowProc).getName(),numero),
                   numero, new ArrayList<Document>());
           procedures.get(rowProc).getEtape().add(etape);
           jTable.setModel(new ModelEtape(procedures.get(rowProc).getEtape()));
           procJTable.setModel(new ModelProcedure(procedures));
           //Pour l'ajouter à la base de données
           TraitementEtape.add(etape, procedures.get(rowProc));
           this.dispose();
       }catch (Exception ex)
       {
           System.out.println(ex);
       }
    }

    public int Transforme(String text, int numero)
    {
        String s = new String();
        int length = text.length();
        s = s.concat(String.valueOf(numero));
        for(int i = 0; i < length && s.length() < 7; i++)
        {
            int  temp = String.valueOf(text.charAt(length - 1 - i)).compareTo(String.valueOf('\t'));
            s = s.concat(String.valueOf(temp));
        }
        return Integer.parseInt(s);
    }
}

