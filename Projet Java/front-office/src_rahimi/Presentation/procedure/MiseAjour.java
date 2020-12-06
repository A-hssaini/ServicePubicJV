package Presentation.procedure;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MiseAjour extends JFrame implements ActionListener{
	private Controleur controleur;
    private JButton demander;
    private JPanel pan,pan1;
    private GridBagConstraints c;
    private ArrayList<JCheckBox> docs;
    private ArrayList<String> nameDocs;
    private HashMap<String, String> hash;
    private String etape;
    private static final long serialVersionUID = 1L;

    private void initComponents(HashMap<String,String> hash, String etape) {
        this.hash = new HashMap<>(hash);
        this.etape = etape;
        this.setTitle("Service-public");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c = new GridBagConstraints();
    }
    private void attachComponents(){
        pan = new JPanel(new GridBagLayout());
        pan.setBorder(new TitledBorder("Mise a jour"));
        pan1 = new JPanel();
        demander = new JButton("demander");
        demander.setPreferredSize(new Dimension(100,25));
        demander.setForeground(Color.black);
        demander.setBackground(Color.WHITE);
        demander.addActionListener(this);
        Iterator it = hash.entrySet().iterator();
        int i =0;
        while (it.hasNext()){
            c.gridx = 0; c.gridy = i;
            Map.Entry entry= (Map.Entry)it.next();
            String key = (String)entry.getKey();
            JCheckBox box = new JCheckBox(key,false);
            box.setBounds(100,100,100,100);
            pan.add(box,c);
            i++;
        }
        pan1.add(demander);
        this.getContentPane().add(pan,BorderLayout.CENTER);
        this.getContentPane().add(pan1,BorderLayout.SOUTH);
    }
    MiseAjour(HashMap<String, String> hash, Controleur controleur){
    	this.controleur = controleur;
        initComponents(hash,etape);
        attachComponents();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        this.dispose();
        Rapport rapp = new Rapport(hash, this.controleur);
        rapp.setVisible(true);
    }

}