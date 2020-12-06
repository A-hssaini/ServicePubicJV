package Presentation.Employe;

import Metier.Employe;
import Metier.Procedure;
import Metier.TraitementEmploye;
import Metier.TraitementProcedure;
import Presentation.Tools.ModelTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AddEmploye extends JFrame {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZazertyuiopmlkjhgfdsqwcxvnb0123456789";
    GridBagConstraints gbc;
    JPanel content;
    JLabel  nomLabel,
            prenomLabel,
            emailLabel,
            procedureLabel;
    JTextField  nomText,
                prenomText,
            emailText;
    ButtonGroup proceduresGroup;
    ArrayList<JCheckBox> proceduresList;
    JPanel proceduresPanel;
    JScrollPane procedureScrollPane;
    JRadioButton isChef;
    JButton ajouter;
    JTable jTable;
    ArrayList<Employe> list;
    JTextField recherche;
    String motDePasse;

    AddEmploye(JTable jTable, ArrayList<Employe> list, JTextField recherche) {
        super("service-public");
        setDefaultOperations();
        initComponents();
        this.jTable = jTable;
        this.list = list;
        this.recherche = recherche;
        /* ou d'après la DB */
        this.listOfProcedure();
        setPreferredSizeForAttributes();
        loadProcedures();
        addAttributes();
        this.setContentPane(content);
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEmployeProcess();
            }
        });
        pack();
    }

    private void AddEmployeProcess() {
        if (nomText.getText().equals("") || prenomText.getText().equals("") || emailText.getText().equals(""))
            JOptionPane.showMessageDialog(this, "L'un des champs est vide", "Alert", JOptionPane.ERROR_MESSAGE);
        else {
            motDePasse = this.passwordGenerator();
            this.addToJtable(list,jTable);
            JOptionPane.showMessageDialog(this, "Employé ajouté avec succès\nLogin: "+prenomText.getText().charAt(0) + nomText.getText() +"\nPassword: "+motDePasse, "Info", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        }
    }

    private void loadProcedures() {
        proceduresPanel.setLayout(new GridLayout(proceduresList.size(), 1));
        for (int i = 0; i < proceduresList.size(); i++) {
            proceduresPanel.add(proceduresList.get(i));
            proceduresGroup.add(proceduresList.get(i));
        }
        procedureScrollPane = new JScrollPane(proceduresPanel);
        procedureScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        procedureScrollPane.setPreferredSize(new Dimension(200, 90));
    }

    private void initComponents() {
        content = new JPanel();
        content.setPreferredSize(new Dimension(700, 500));
        content.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        nomText = new JTextField();
        nomLabel = new JLabel("Nom", SwingConstants.RIGHT);
        prenomText = new JTextField();
        prenomLabel = new JLabel("Prenom", SwingConstants.RIGHT);
        emailText = new JTextField();
        emailLabel = new JLabel("Email", SwingConstants.RIGHT);
        isChef = new JRadioButton("Chef");
        ajouter = new JButton("Ajouter");
        procedureLabel = new JLabel("Procédures", SwingConstants.RIGHT);
        proceduresList = new ArrayList<JCheckBox>();
        proceduresPanel = new JPanel();
        proceduresGroup = new ButtonGroup();
    }

    private void setPreferredSizeForAttributes() {
        nomLabel.setPreferredSize(new Dimension(150, 40));
        nomText.setPreferredSize(new Dimension(200, 40));
        prenomLabel.setPreferredSize(new Dimension(150, 40));
        prenomText.setPreferredSize(new Dimension(200, 40));
        emailLabel.setPreferredSize(new Dimension(150, 40));
        emailText.setPreferredSize(new Dimension(200, 40));
        isChef.setPreferredSize(new Dimension(150, 40));
        isChef.setBorder(new EmptyBorder(0, 100, 0,0 ));
        ajouter.setPreferredSize(new Dimension(150, 40));
        procedureLabel.setPreferredSize(new Dimension(150, 40));
    }

    private void addAttributes() {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 10, 2, 10);
        content.add(nomLabel, addConstraints(1, 1, 2, 1));
        content.add(nomText, addConstraints(3, 1, 3, 1));
        gbc.insets = new Insets(2, 10, 2, 10);
        content.add(prenomLabel, addConstraints(1, 2, 2, 1));
        content.add(prenomText, addConstraints(3, 2, 3, 1));
        content.add(emailLabel, addConstraints(1, 3, 2, 1));
        content.add(emailText, addConstraints(3, 3, 3, 1));
        content.add(isChef, addConstraints(2, 4, 4, 1));
        content.add(procedureLabel, addConstraints(1, 5, 2, 1));
        content.add(procedureScrollPane, addConstraints(3, 5, 3, 3));
        gbc.insets = new Insets(20, 10, 30, 10);
        content.add(ajouter, addConstraints(1, 8, 5, 1));
        //content.setBackground( new Color(53, 54, 58) );
    }

    private void setDefaultOperations() {
        //setDefaultCloseOperation(this.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setSize(600, 700);
        //setResizable(false); //On interdit la redimensionnement de la fenêtre
        closeEvent();
    }

    private void closeEvent() {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new AbstractAction()  {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private GridBagConstraints addConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridheight = gridheight;
        gbc.gridwidth = gridwidth;
        return gbc;
    }

    private void addToJtable(ArrayList<Employe> li, JTable jTable)
    {
        String start = String.valueOf(prenomText.getText().charAt(0));
        String procedure = new String();
        for(int i = 0;i < proceduresList.size();i++)
        {
            if(proceduresList.get(i).isSelected())
            {
                procedure = proceduresList.get(i).getText();
                break;
            }
        }
        String login= start.concat(new String(nomText.getText()));

        Employe employe = new Employe(login,this.motDePasse,nomText.getText(),prenomText.getText(),
                emailText.getText(),isChef.isSelected(),procedure,new ArrayList<Integer>());
        li.add(employe);
        TraitementEmploye.add(employe);
        ArrayList<Employe> ets = ((ModelTable)(jTable.getModel())).getMesEtudiant();
        String text = recherche.getText();
        if(employe.getLogin().contains(text) || employe.getNom().contains(text)
                || employe.getPrenom().contains(text) || employe.getProcédures().contains(text)
                || employe.getEmail().contains(text))
            if(text.compareTo("") != 0)
                ets.add(employe);
        jTable.setModel(new ModelTable(ets));
    }

    public void listOfProcedure()
    {
        ArrayList<Procedure> procedures = TraitementProcedure.getAll();
        for(int i = 0;i < procedures.size(); i++)
            proceduresList.add(new JCheckBox(procedures.get(i).getName()));
    }

    public String passwordGenerator()
    {
        StringBuilder builder = new StringBuilder();
        int length = 8;
        while (length-- != 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        String pwdStr = builder.toString();
        return pwdStr;
    }
}
