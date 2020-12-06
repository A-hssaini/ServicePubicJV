package Presentation.Tools;

import Metier.Document;
import Metier.Etape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModifyEtapeListner extends MouseAdapter {

	JTable jTable;
	JPopupMenu main;
	JLabel item1, item2;
	ArrayList<Document> list;
    JTable procJTable;
    int rowProc;

	public ModifyEtapeListner(JTable jTable, ArrayList<Document> list, JTable procJTable, int rowProc)
	{
		this.jTable = jTable;
		this.list = list;
		this.procJTable = procJTable;
		this.rowProc = rowProc;
	}

	 @Override
    public void mouseClicked(MouseEvent e) {
    	Point point = e.getPoint();
        int colomneAt = jTable.columnAtPoint(point);
        int rowAt = jTable.rowAtPoint(point);
        Etape etape = ((ModelEtape)jTable.getModel()).getetape(rowAt);
        main = new JPopupMenu();
        item1 = new JLabel("Modifier");
        item2 = new JLabel("Supprimer");
        main.add(item1);
        main.add(item2);
        main.show(jTable,point.x,point.y);
        item2.addMouseListener(new DeleteEtapeListener(jTable,rowAt,main,procJTable,rowProc));
        item1.addMouseListener(new ModifyDocEtapeListener(jTable,rowAt,main,procJTable,rowProc));
    }
	
}