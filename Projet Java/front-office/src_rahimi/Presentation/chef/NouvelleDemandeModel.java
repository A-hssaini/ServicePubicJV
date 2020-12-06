package Presentation.chef;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

public class NouvelleDemandeModel extends AbstractTableModel {
	
    private ArrayList<NouvelleDemande> mesDemandes;

    public NouvelleDemandeModel(ArrayList<NouvelleDemande> mesDemandes) {
        super();
        this.mesDemandes = mesDemandes;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return mesDemandes.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
    	NouvelleDemande a = mesDemandes.get(rowIndex);
        switch(columnIndex) {
            case 0 : return a.getCinString();
            case 1 : return a.getProcedureString();
            case 2 : return a.getButtonPanel();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "demandes (cin)";
            case 1 : return "Procedure";
           case 2 : return "";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return JPanel.class;
            default : return Object.class;
        }
    }
	
}

