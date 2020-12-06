package Presentation.Tools;

import Metier.Document;
import Metier.Procedure;
import Metier.TraitementDocument;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class AjoutDoc extends JFrame implements ActionListener {
    private JLabel label,
                    lbl;
    private JTextArea text;
    private JTextField jtf;
    private JPanel panel,
                    panel1,panel2;
    private JButton button;
    private GridBagConstraints c;
    private Procedure proc;
    private JTable procJTable;

    public AjoutDoc(Procedure proc, JTable procJTable){
        super();
        this.setTitle("Service-public");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500,500));
        panel1 = new JPanel();
        panel2 = new JPanel();
        c = new GridBagConstraints();
        this.proc = proc;
        this.procJTable = procJTable;
        panel.setBorder(BorderFactory.createTitledBorder(null, "Nouveau Document", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.white));
        panel.setBackground(new Color(53,54,58));
        label = new JLabel("Description :",SwingConstants.RIGHT);
        lbl = new JLabel("nom de document:",SwingConstants.RIGHT);
        lbl.setForeground(Color.WHITE);
        label.setForeground(Color.WHITE);
        jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(200,30));
        jtf.setBorder(new TitledBorder(""));
        text = new JTextArea();
        text.setPreferredSize(new Dimension(300,100));
        text.setBorder(new TitledBorder(""));
        button = new JButton("Valider");
        button.addActionListener(this);
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(30, 10, 2, 10);
        panel.add(lbl, addConstraints(1, 1, 2, 1));
        panel.add(jtf, addConstraints(3, 1, 3, 1));
        c.insets = new Insets(2, 10, 2, 10);
        panel.add(label, addConstraints(1, 2, 2, 1));
        panel.add(text, addConstraints(3, 2, 3, 1));
        panel1.add(button);
        this.add(panel,BorderLayout.EAST);
        this.add(panel1,BorderLayout.SOUTH);
    }
    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridheight = gridheight;
        c.gridwidth = gridwidth;
        return c;
    }
    public void nouveauProc(){
        Document document = new Document(Transforme(jtf.getText()), jtf.getText(),text.getText());
        //Ajouter le document à la base de données
        TraitementDocument.add(document, this.proc);
        this.proc.getDocument().add(document);
        ArrayList<Procedure> procedures = ((ModelProcedure)procJTable.getModel()).getMesProcedure();
        procJTable.setModel(new ModelProcedure(procedures));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nouveauProc();
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
