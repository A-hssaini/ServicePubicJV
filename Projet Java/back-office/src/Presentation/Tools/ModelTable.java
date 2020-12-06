package Presentation.Tools;

import Metier.Employe;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelTable extends AbstractTableModel {
    private ArrayList<Employe> mesEmploye;

    public ModelTable(ArrayList<Employe> mesEmploye) {
        super();
        this.mesEmploye = mesEmploye;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return mesEmploye.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        Employe a = mesEmploye.get(rowIndex);
        switch(columnIndex) {
            case 0 : return a.getLogin();
            case 1 : return a.getNom();
            case 2 : return a.getPrenom();
            case 3 : return a.getEmail();
            case 4 : return a.getChef();
            case 5: return a.getProcédures();
            case 6 : return a.getArchiver();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "Login";
            case 1 : return "Nom";
            case 2 : return "Prenom";
            case 3 : return "Email";
            case 4 : return "Chef";
            case 5 : return "Procédures";
            case 6 : return "Archiver";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return String.class;
            case 3 : return String.class;
            case 4 : return Boolean.class;
            case 5 : return String.class;
            case 6 : return Boolean.class;
            default : return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 4 || columnIndex == 6)
            return true;
        return false;
    }

    public Employe getetudiant(int row)
    {
        return this.mesEmploye.get(row);
    }

    public ArrayList<Employe> getMesEtudiant() {
        return mesEmploye;
    }
}
