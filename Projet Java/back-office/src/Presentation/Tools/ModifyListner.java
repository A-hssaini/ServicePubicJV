package Presentation.Tools;

import Metier.Document;
import Metier.Etape;
import Metier.Procedure;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModifyListner extends MouseAdapter {
	int n;
	ArrayList<Etape> etape;
	ArrayList<Document> document;
	ModifyEtape modifyEtape;
	JTable procJTable;
	int rowProc;
	ModifyDocs modifyDocs;

	public ModifyListner(int i, ArrayList<Etape> etape, ArrayList<Document> document, JTable procJTable, int rowProc)
	{
		n = i;
		this.etape = etape;
		this.document = document;
		this.procJTable = procJTable;
		this.rowProc = rowProc;
	}

	@Override
    public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
    	if(n == 0)
    	{
			modifyEtape = new ModifyEtape(this.etape,this.document,this.procJTable,this.rowProc);
			modifyEtape.setVisible(true);
    	}
    	if(n == 1)
    	{
			ArrayList<Procedure> procedures = ((ModelProcedure)procJTable.getModel()).getMesProcedure();
			modifyDocs = new ModifyDocs(procedures.get(rowProc),procJTable);
			modifyDocs.setVisible(true);
    	}
    }
}