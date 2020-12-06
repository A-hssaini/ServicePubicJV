package Presentation.Tools;

import Metier.Employe;
import Presentation.Employe.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

public class jTextFieldListner implements DocumentListener {

    JTable jTable;
    ArrayList<Employe> li;
    String text;

    public jTextFieldListner(JTable jTable, ArrayList<Employe> li, listeEmploye listeEtudiant) {
        this.jTable = jTable;
        this.li = li;
        this.text = listeEtudiant.getRecherche().getText();
        System.out.println(this.text);
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        filtre();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        filtre();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        filtre();
    }

    public void filtre()
    {
        System.out.println(text);
        for(int i = 0;i < li.size();i++)
        {
            if(!(li.get(i).getLogin().contains(text) || li.get(i).getNom().contains(text)
            || li.get(i).getPrenom().contains(text) || li.get(i).getProcédures().contains(text)
        || li.get(i).getEmail().contains(text)))
                li.remove(i);

        }
        jTable.setModel(new ModelTable(li));
    }


}
