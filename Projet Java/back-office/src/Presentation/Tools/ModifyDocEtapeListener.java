package Presentation.Tools;

import Metier.Document;
import Metier.Etape;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModifyDocEtapeListener extends MouseAdapter {
    JTable jTable, procJTable;
    int rowAt, rowProc;
    JPopupMenu main;

    public ModifyDocEtapeListener(JTable jTable, int rowAt, JPopupMenu main, JTable procJTable, int rowProc) {
        this.jTable = jTable;
        this.rowAt = rowAt;
        this.main = main;
        this.procJTable = procJTable;
        this.rowProc = rowProc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        main.setVisible(false);
        ArrayList<Etape> etapes = ((ModelEtape)jTable.getModel()).getMesEtape();
        ArrayList<Document> documents = ((ModelProcedure)procJTable.getModel()).getMesProcedure().get(rowProc).getDocument();
        Docdispo docdispo = new Docdispo(etapes.get(rowAt), documents, jTable);
        docdispo.setVisible(true);
    }
}
