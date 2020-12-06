package Presentation.citoyen;

import Metier.Demande;
import Metier.Etat;
import Presentation.Tools.ToolButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SuivreDemandeModel extends AbstractTableModel {
    ArrayList<JPanel>   uploaders;
    ArrayList<Demande>  demandes;

    SuivreDemandeModel(ArrayList<Demande>  demandes) {
        super();
        this.demandes = demandes;
        uploaders = new ArrayList<>();
        for (int i = 0; i < demandes.size(); i++) {
            ToolButton toolButton = new ToolButton("", "uploader document", 30, 30);
            try {
                if (demandes.get(i).getEtat() == Etat.REJETE) toolButton.setIcon("./resrc/rejete.png", 30, 30);
                else if (demandes.get(i).getEtat() == Etat.ACCEPTE) toolButton.setIcon("./resrc/valid.png", 30, 30);
                else if (demandes.get(i).getEtat() == Etat.ENCORE) toolButton.setIcon("./resrc/encore.png", 30, 30);
                else if (demandes.get(i).getEtat() == Etat.MISEAJOUR) toolButton.setIcon("./resrc/upload.png", 30, 30);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            JPanel uploader = new JPanel();
            toolButton.button.setBorder(new EmptyBorder(5, 0, 0,0 ));
            uploader.add(toolButton.button, BorderLayout.CENTER);
            uploaders.add(uploader);
        }
    }

    public ArrayList<Demande> getDemandes() {
        return demandes;
    }

    @Override
    public int getRowCount() {
        return demandes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return demandes.get(rowIndex).getJeton();
            case 1 : return demandes.get(rowIndex).getProcedure().getNom();
            case 2 : return demandes.get(rowIndex).getEtat();
            case 3 : return uploaders.get(rowIndex);
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "   Demandes(jeton)";
            case 1 : return "   Procédure";
            case 2 : return "   État";
            case 3 : return "";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return String.class;
            case 3 : return JPanel.class;
            default : return Object.class;
        }
    }
}
