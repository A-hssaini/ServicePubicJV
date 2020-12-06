package Presentation.Tools;
import Metier.Procedure;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelProcedure extends AbstractTableModel {
    private ArrayList<Procedure> mesProcedure;

    public ModelProcedure(ArrayList<Procedure> mesProcedure) {
        super();
        this.mesProcedure = mesProcedure;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return mesProcedure.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        Procedure a = mesProcedure.get(rowIndex);
        switch(columnIndex) {
            case 0 : return a.getName();
            case 1 : return a.getEtape().size();
            case 2 : return a.getDocument().size();
            case 3 : return a.getArchiver();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "Nom";
            case 1 : return "NombreEtape";
            case 2 : return "NombreDocument";
            case 3 : return "Archiver";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return String.class;
            case 1 : return Integer.class;
            case 2 : return Integer.class;
            case 3 : return Boolean.class;
            default : return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 3)
            return true;
        return false;
    }

    public Procedure getprocedure(int row)
    {
        return this.mesProcedure.get(row);
    }

    public ArrayList<Procedure> getMesProcedure() {
        return mesProcedure;
    }
}