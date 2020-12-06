package Presentation.chef;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

public class ConsulterDemandeModel extends AbstractTableModel {
	
    private ArrayList<ConsulterDemande> consulterListe;

    public ConsulterDemandeModel(ArrayList<ConsulterDemande> consulterListe) {
        super();
        this.consulterListe = consulterListe;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return consulterListe.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
    	ConsulterDemande a = consulterListe.get(rowIndex);
        switch(columnIndex) {
            case 0 : return a.getJetonString();
            case 1 : return a.getProcedureString();
            case 2 : return a.getEtatBoolean();
            case 3 : return a.getButtonPanel();
            case 4 : return a.getButton2Panel();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "Traiter comande(jeton)";
            case 1 : return "Procedure";
            case 2 : return "Etat";
           case 3 : return "";
           case 4 : return "";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return Boolean.class;
            case 3 : return JPanel.class;
            case 4 : return JPanel.class;
            default : return Object.class;
        }
    }
	
}

