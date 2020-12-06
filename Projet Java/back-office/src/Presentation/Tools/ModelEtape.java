package Presentation.Tools;
import Metier.Etape;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelEtape extends AbstractTableModel {
    private ArrayList<Etape> mesEtape;

    public ModelEtape(ArrayList<Etape> mesEtape) {
        super();
        this.mesEtape = mesEtape;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return mesEtape.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        Etape a = mesEtape.get(rowIndex);
        switch(columnIndex) {
            case 0 : return a.getNumero();
            case 1 : return a.DocToString();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "Numero";
            case 1 : return "Document";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return Integer.class;
            case 1 : return String.class;
            default : return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Etape getetape(int row)
    {
        return this.mesEtape.get(row);
    }

    public ArrayList<Etape> getMesEtape() {
        return mesEtape;
    }
}