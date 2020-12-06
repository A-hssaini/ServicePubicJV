package Presentation.Tools;

import Metier.Employe;
import Metier.Etape;
import Metier.Procedure;
import Metier.TraitementEmploye;
import Presentation.Employe.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListnerForPop extends MouseAdapter {
    JTable jTable;
    ArrayList<Employe> li;
    String text;
    int row;
    ArrayList<Etape> etapes;
    popupMenu pop;
    ArrayList<JCheckBox> jCheckBoxes;
    Procedure procedure;
    public MouseListnerForPop(JTable jTable, ArrayList<Employe> li, int row, String text,
                              ArrayList<Etape> etapes, popupMenu pop, Procedure procedure)
    {
        this.jTable = jTable;
        this.li = li;
        this.row = row;
        this.text = text;
        this.etapes = etapes;
        this.pop = pop;
        this.procedure = procedure;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        jCheckBoxes = pop.getList1();
        li.get(row).setProcédures(text);
        li.get(row).setEtape(new ArrayList<Integer>());
        ArrayList<Employe> temp = ((ModelTable)(jTable.getModel())).getMesEtudiant();
        jTable.setModel(new ModelTable(temp));
        pop.getMenu1().setText(text);
        pop.getMenu1().removeAll();
        jCheckBoxes = new ArrayList<JCheckBox>();
        int i = 0;
        while(i < etapes.size())
        {
            jCheckBoxes.add(new JCheckBox("Etape".concat(String.valueOf(etapes.get(i).getNumero()))));
            jCheckBoxes.get(i).addMouseListener(new MouseListnerForEtape(li.get(row),jCheckBoxes.get(i), procedure));
            pop.getMenu1().add(jCheckBoxes.get(i));
            i++;
        }
        TraitementEmploye.changerProc(li.get(row));
    }
}
