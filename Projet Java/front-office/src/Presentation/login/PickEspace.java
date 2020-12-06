package Presentation.login;

import Presentation.Tools.*;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PickEspace extends JFrame {
    JLabel      title;
    JPanel      redirector;
    ToolButton  citoyen,
                employe;

    public PickEspace() throws IOException {
        super("service-public");
        setDefaultOperations();
        initAttributes();
        setAttributes();
        add(title, BorderLayout.NORTH);
        add(redirector, BorderLayout.CENTER);
        setEventManager();
    }

    private void setEventManager() {
        citoyen.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login pageCitoyen = new Login("CIN");
                pageCitoyen.setVisible(true);
            }
        });
        employe.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login pageEmploye = new Login("Pseudo", "Mot de passe");
                pageEmploye.setVisible(true);
            }
        });
    }

    private void setAttributes() {
        //title.setPreferredSize(new Dimension(1024, 50));
        title.setFont(new Font("Gill Sans", 0, 34));
        title.setForeground(new Color(239, 240, 241));
        title.setOpaque(false);
        redirector.setLayout(new GridLayout(1, 2));
        redirector.setBackground( new Color(53, 54, 58) );
        redirector.add(citoyen.button);
        redirector.add(employe.button);
    }

    private void initAttributes() throws IOException {
        title = new JLabel("Guide des procédures administratives", SwingConstants.CENTER);
        redirector = new JPanel();
        citoyen = new ToolButton("Espace Citoyens", "Espace Citoyens", 300, 150);
        citoyen.setIcon("./resrc/citoyen.png", 80, 90);
        employe = new ToolButton("Espace Employés", "Espace Employés", 300, 150);
        employe.setIcon("./resrc/employe.png", 80, 90);
    }


    private void setDefaultOperations() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setSize(700, 438);
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setLayout(new BorderLayout());
        closeEvent();
        this.getContentPane().setBackground( new Color(53, 54, 58) );
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

}
