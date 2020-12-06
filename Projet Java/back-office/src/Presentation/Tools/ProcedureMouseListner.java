package Presentation.Tools;

import Metier.Procedure;
import Metier.TraitementProcedure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProcedureMouseListner extends MouseAdapter {
	JTable jTable;
	ArrayList<Procedure> procedure,originalList;
	JPopupMenu jPopupMenu;
	JButton jButton;

	public ProcedureMouseListner(JTable jTable,ArrayList<Procedure> originalList)
	{
		this.originalList = originalList;
		this.jTable = jTable;
	}

	@Override
    public void mouseClicked(MouseEvent e) {
    	super.mouseEntered(e);
        Point point = e.getPoint();
        int colomneAt = jTable.columnAtPoint(point);
        int rowAt = jTable.rowAtPoint(point);
        this.procedure = ((ModelProcedure)(jTable.getModel())).getMesProcedure();
        if(colomneAt == 1 || colomneAt == 2) {
        	jPopupMenu = new JPopupMenu();
        	jButton = new JButton("Modifier");
        	jPopupMenu.add(jButton);
        	jPopupMenu.show(jTable, point.x, point.y);
        }
        if(colomneAt == 1) {
			jButton.addMouseListener(new ModifyListner(0, this.procedure.get(rowAt).getEtape(),
					this.procedure.get(rowAt).getDocument(), jTable, rowAt));
		}
        if(colomneAt == 2)
        	jButton.addMouseListener(new ModifyListner(1,this.procedure.get(rowAt).getEtape(),
					this.procedure.get(rowAt).getDocument(), jTable, rowAt));
        if(colomneAt == 3)
		{
			Procedure proc = procedure.get(rowAt);
			//Changer état dans la base de données
			TraitementProcedure.update(proc,true);
			this.procedure.remove(proc);
			this.originalList.remove(proc);
			this.jTable.setModel(new ModelProcedure(this.procedure));
		}

    }
}
