package Presentation.login;

import Presentation.Tools.*;
import Presentation.chef.*;
import Presentation.employe.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.ConnectToDB;
import DAO.Controleur;
import DAO.DemandeDAO;
import Metier.Demande;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends JFrame {
    GridBagConstraints gbc;
    JPanel content;
    JLabel labelField, labelPwd;
    JPasswordField textPwd;
    JTextField textField;
    JLabel title;
    JButton login;

    public Login(String oneLabel) {
        super("service-public");
        setDefaultOperations();
        initAttributes(oneLabel, "");
        setPreferredSizeForAttributes();
        setAttributes(oneLabel, "");
        addAttributes(oneLabel, "");
        this.setContentPane(content);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageCitoyen();
            }
        });
    }

    private void pageCitoyen() {
        if (isCIN(textField.getText())) {
            /* Champ pour lire de la DB */
            dispose();
        }
        else if (textField.getText().equals(""))
            JOptionPane.showMessageDialog(this, "Champ CIN vide", "Alert", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "L'un des champs est incorrect!", "Alert", JOptionPane.ERROR_MESSAGE);
    }
    public boolean isCIN(String cin) {
        return true;
    }
    Login(String oneLabel, String twoLabel) {
        super("service-public");
        setDefaultOperations();
        initAttributes(oneLabel, twoLabel);
        setPreferredSizeForAttributes();
        setAttributes(oneLabel, twoLabel);
        addAttributes(oneLabel, twoLabel);
        this.setContentPane(content);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pageEmploye();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void pageEmploye() throws IOException {
        String pwd = new String(textPwd.getPassword());
        if (textField.getText().equals("Chef")) {
            ArrayList<NouvelleDemande> mesDemandes = new ArrayList<NouvelleDemande>();
            mesDemandes.add(new NouvelleDemande("V459809", "Passeport"));
            mesDemandes.add(new NouvelleDemande("BP98345", "CIN"));

            ArrayList<ConsulterDemande> consulterList = new ArrayList<ConsulterDemande>();
            consulterList.add(new ConsulterDemande("H3DdS45", "RAMED",true));
            consulterList.add(new ConsulterDemande("HFS45FF", "Extrait",true));
            ChefPage list = new ChefPage(mesDemandes,consulterList);
            dispose();
        }
        else if (true) {
         	Controleur controleur = new Controleur();
        	controleur.showTraiterCommande();
 //       	ConnectToDB connectToDB = new ConnectToDB();
    //		DemandeDAO dao = new DemandeDAO(connectToDB.getMyCollection());
      //      TraiterCommandePage list = new TraiterCommandePage(dao.getMesDemande());
            /* Champ pour lire de la DB */
         /*   ArrayList<Demande> mesDemandes = new ArrayList<Demande>();
            mesDemandes.add(new Demande("H3DdS45", "RAMED"));
            mesDemandes.add(new Demande("HFS45FF", "Extrait"));
            TraiterCommandePage list = new TraiterCommandePage(mesDemandes);*/
            dispose();
        }
        else if (textField.getText().equals(""))
            JOptionPane.showMessageDialog(this, "Champ Pseudo vide", "Alert", JOptionPane.ERROR_MESSAGE);
        else if (pwd.equals(""))
            JOptionPane.showMessageDialog(this, "Champ Mot de passe vide", "Alert", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "L'un des champs est incorrect!", "Alert", JOptionPane.ERROR_MESSAGE);
    }

    private void setPreferredSizeForAttributes() {
        login.setPreferredSize(new Dimension(150, 40));
        textField.setPreferredSize(new Dimension(200, 40));
        labelField.setPreferredSize(new Dimension(150, 40));
        labelField.setForeground(Color.white);
        title.setBorder(new EmptyBorder(0, 50, 0,0 ));
        title.setPreferredSize(new Dimension(150, 40));
    }

    private void initAttributes(String oneLabel, String twoLabel) {
        content = new JPanel();
        gbc = new GridBagConstraints();
        login = new JButton("Connexion");
        textField = new JTextField();
        labelField = new JLabel(oneLabel, SwingConstants.RIGHT);
        title = new JLabel("Login", SwingConstants.CENTER);
        if (!twoLabel.equals("")) {
            textPwd = new JPasswordField();
            textPwd.setPreferredSize(new Dimension(200, 40));
            labelPwd = new JLabel(twoLabel, SwingConstants.RIGHT);
            labelPwd.setPreferredSize(new Dimension(150, 40));
            labelPwd.setForeground(Color.white);
        }
    }

    private void addAttributes(String oneLabel, String twoLabel) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 40, 10);
        content.add(title, addConstraints(5, 0, 2, 1));
        gbc.insets = new Insets(2, 10, 2, 10);
        content.add(labelField, addConstraints(2, 3, 4, 1));
        content.add(textField, addConstraints(6, 3, 2, 1));
        if (!twoLabel.equals("")) {
            content.add(labelPwd, addConstraints(2, 4, 4, 1));
            content.add(textPwd, addConstraints(6, 4, 2, 1));
        }
        gbc.insets = new Insets(40, 10, 10, 10);
        content.add(login, addConstraints(5, 6, 4, 1));
    }

    private void setAttributes(String oneLabel, String twoLabel) {
        content.setLayout(new GridBagLayout());
        content.setBackground( new Color(53, 54, 58) );
        title.setIcon(ToolButton.resizeIcon(new ImageIcon("./resrc/login.png"), 40, 40 ));
        title.setFont(new Font("Gill Sans", 0, 34));
        title.setForeground(new Color(239, 240, 241));
        title.setOpaque(false);
    }

    private void setDefaultOperations() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setSize(700, 438);
        setResizable(false); //On interdit la redimensionnement de la fenêtre
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
}
