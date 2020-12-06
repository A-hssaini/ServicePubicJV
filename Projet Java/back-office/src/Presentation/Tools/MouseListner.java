package Presentation.Tools;

import DAO.DAOProcedure;
import Metier.Employe;
import Metier.Procedure;
import Metier.TraitementEmploye;
import Metier.TraitementProcedure;
import Presentation.Employe.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListner extends MouseAdapter {
    JTable jTable;
    popupMenu Menu;
    ArrayList<Employe> li;
    public MouseListner(JTable jTable,ArrayList<Employe> li) {
        this.jTable = jTable;
        this.li = li;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Point point = e.getPoint();
        int colomneAt = jTable.columnAtPoint(point);
        int rowAt = jTable.rowAtPoint(point);
        Employe et = ((ModelTable)jTable.getModel()).getetudiant(rowAt);
        ArrayList<Employe> temp = ((ModelTable)(jTable.getModel())).getMesEtudiant();
        DAOProcedure daoProcedure = new DAOProcedure();
        ArrayList<Procedure> procedures = daoProcedure.getAll();
        if(colomneAt == 5)
        {
            Menu = new popupMenu(et,procedures,jTable,this.li);
            Menu.getMainMenu().show(jTable, point.x, point.y);
        }
        if(colomneAt == 4)
        {
            if(et.getChef() == true)
                et.setChef(false);
            else
                et.setChef(true);
            TraitementEmploye.ChangeChefStat(et, et.getChef());
            jTable.setModel(new ModelTable(temp));
        }
        if(colomneAt == 6)
        {
            //Changer état dans la base de données
            TraitementEmploye.ChangeArchiveStat(et,true);
            temp.remove(et);
            this.jTable.setModel(new ModelTable(temp));
        }
    }
}
