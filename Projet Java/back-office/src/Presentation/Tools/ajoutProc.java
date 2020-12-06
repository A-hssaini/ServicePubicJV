package Presentation.Tools;

import Metier.TraitementProcedure;
import Presentation.procedure.listeProcedure;
import Metier.Document;
import Metier.Etape;
import Metier.Procedure;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ajoutProc  extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField text;
    private JPanel panel;
    private JPanel panel1;
    private JButton button;
    private GridBagConstraints c;
    listeProcedure listeProcedure;

    public ajoutProc(listeProcedure listeProcedure){
        super();
        this.listeProcedure = listeProcedure;
        this.setTitle("Service-public");
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        label = new JLabel("Nom de procédure:    ");
        text = new JTextField();
        panel = new JPanel(new GridBagLayout());
        panel1 = new JPanel();
        button = new JButton("Ajouter");
        button.addActionListener(this);
        c = new GridBagConstraints();
        text.setPreferredSize(new Dimension(120,30));
        text.setBackground(Color.white);
        label.setForeground(Color.WHITE);
        panel.setBackground(new Color(53,54,58));
        panel.setBorder(BorderFactory.createTitledBorder(null, "Nouveau Document", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.white));
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
        ArrayList<Procedure> procedures = this.listeProcedure.getProcedure();
        Procedure procedure = new Procedure(Transforme(this.text.getText()),
                this.text.getText(),new ArrayList<Etape>(),
                new ArrayList<Document>());
        TraitementProcedure.add(procedure);
        procedures.add(procedure);
        this.listeProcedure.getjTable().setModel(new ModelProcedure(procedures));
        this.dispose();
    }

    public int Transforme(String text)
    {
        String s = new String();
        int length = text.length();
        Random random = new Random();
        int numero = random.nextInt(50);
        s = s.concat(String.valueOf(numero));
        for(int i = 0; i < length && s.length() < 7; i++)
        {
            int  temp = String.valueOf(text.charAt(length - 1 - i)).compareTo(String.valueOf('\t'));
            s = s.concat(String.valueOf(temp));
        }
        return Integer.parseInt(s);
    }
}
