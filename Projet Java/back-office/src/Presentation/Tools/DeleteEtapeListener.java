package Presentation.Tools;

import Metier.Etape;
import Metier.Procedure;
import Metier.TraitementEtape;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DeleteEtapeListener extends MouseAdapter {
    JTable jTable, procJTable;
    Integer rowAt, rowProc;
    JPopupMenu main;
    public DeleteEtapeListener(JTable jTable, int rowAt, JPopupMenu main, JTable procJTable, int rowProc) {
        this.jTable = jTable;
        this.rowAt = rowAt;
        this.main = main;
        this.procJTable = procJTable;
        this.rowProc = rowProc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        ArrayList<Etape> etapes = ((ModelEtape)jTable.getModel()).getMesEtape();
        Etape etape = etapes.get(rowAt);
        //Pour Supprimer l'etape de la base de données
        TraitementEtape.delete(etape);
        etapes.remove(etape);
        main.setVisible(false);
        jTable.setModel(new ModelEtape(etapes));
        ArrayList<Procedure> procedures = ((ModelProcedure)procJTable.getModel()).getMesProcedure();
        Procedure procedure = procedures.get(rowProc);
        procedure.getEtape().remove(rowAt);
        procJTable.setModel(new ModelProcedure(procedures));
    }
}
