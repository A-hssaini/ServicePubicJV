package Presentation.Employe;

import Metier.Employe;
import Metier.TraitementEmploye;
import Presentation.Tools.ModelTable;
import Presentation.Tools.ToolTextField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModifyEmploye extends JFrame implements ActionListener {
    ToolTextField rechercher;
    JButton search, valider;
    JTextField nom , prenom, motDePasse, email;
    ArrayList<Employe> emp;
    JTable jTable;
    JLabel  lnom,lprenom,lpasswd ,lmail;
    JPanel pan,pan1,pan3;
    GridBagConstraints c;
    Employe es;
    public ModifyEmploye(JTable jTable, ArrayList<Employe> employe)
    {
        es = new Employe();
        emp = employe;
        this.jTable = jTable;
        this.setTitle("Service-public");
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pan = new JPanel(new GridBagLayout());
        pan1 = new JPanel();
        rechercher = new ToolTextField("Entrez login ici",true);
        rechercher.setIcon(new ImageIcon("./resrc/search.png"));
        c = new GridBagConstraints();
        lnom = new JLabel("nom :",SwingConstants.RIGHT); nom = new JTextField("");
        lprenom =  new JLabel("prénom :",SwingConstants.RIGHT); prenom = new JTextField("");
        lpasswd = new JLabel("mot de passe :",SwingConstants.RIGHT); motDePasse = new JTextField("");
        lmail = new JLabel("e-mail :",SwingConstants.RIGHT); email = new JTextField("");
        search = new JButton("rechercher"); valider = new JButton("valider");
        nom.setPreferredSize(new Dimension(120,30));
        nom.setBackground(Color.white);
        prenom.setPreferredSize(new Dimension(120,30));
        prenom.setBackground(Color.white);
        motDePasse.setPreferredSize(new Dimension(120,30));
        motDePasse.setBackground(Color.white);
        email.setPreferredSize(new Dimension(120,30));
        email.setBackground(Color.white);
        rechercher.setPreferredSize(new Dimension(180,30));
        lnom.setForeground(Color.WHITE);lprenom.setForeground(Color.WHITE);
        lpasswd.setForeground(Color.WHITE); lmail.setForeground(Color.WHITE);
        pan.setBackground(new Color(53,54,58));
        pan.setBorder(BorderFactory.createTitledBorder(null, "modifier employé", TitledBorder.CENTER, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.white));
        search.setPreferredSize(new Dimension(120,20));
        search.setBorderPainted(true);  search.setFont(new Font("times new roman", 0, 12));
        search.setForeground(Color.BLACK);
        search.setBackground(Color.WHITE);
        search.addActionListener(this);
        valider.setPreferredSize(new Dimension(80,20));
        valider.setBorderPainted(true);  valider.setFont(new Font("times new roman", 0, 12));
        valider.setForeground(Color.BLACK);valider.setVerticalTextPosition(SwingConstants.BOTTOM);
        valider.setHorizontalTextPosition(SwingConstants.CENTER);
        valider.setBackground(Color.WHITE);valider.addActionListener(this);
        valider.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 10, 0, 10);
        pan.add(rechercher, addConstraints(1, 0, 2, 1));
        pan.add(search, addConstraints(3, 0, 3, 1));
        c.insets = new Insets(30, 10, 2, 10);
        pan.add(lnom, addConstraints(1, 1, 2, 1));
        pan.add(nom, addConstraints(3, 1, 3, 1));
        c.insets = new Insets(3, 10, 2, 10);
        pan.add(lprenom, addConstraints(1, 2, 2, 1));
        pan.add(prenom, addConstraints(3, 2, 3, 1));
        c.insets = new Insets(2, 10, 2, 10);
        pan.add(lpasswd, addConstraints(1, 3, 2, 1));
        pan.add(motDePasse, addConstraints(3,3, 3, 1));
        c.insets = new Insets(1, 10, 2, 10);
        pan.add(lmail, addConstraints(1, 4, 2, 1));
        pan.add(email, addConstraints(3,4, 3, 1));

        pan1.add(valider);
        this.add(pan,BorderLayout.CENTER);this.add(pan1,BorderLayout.SOUTH);
    }

    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridheight = gridheight;
        c.gridwidth = gridwidth;
        return c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search)
        {
            for (Employe ep : this.emp) {
                if(ep.getLogin().compareTo(rechercher.getText()) == 0)
                    es = ep;
            }
            nom.setText(es.getNom());
            prenom.setText(es.getPrenom());
            motDePasse.setText(es.getMotDePasse());
            email.setText(es.getEmail());
        }
        else if(e.getSource() == valider)
        {
            es.setNom(nom.getText());
            es.setPrenom(prenom.getText());
            es.setMotDePasse(motDePasse.getText());
            es.setEmail(email.getText());
            TraitementEmploye.UpdateEmploye(es);
            jTable.setModel(new ModelTable(emp));
            this.dispose();
        }
    }
}
