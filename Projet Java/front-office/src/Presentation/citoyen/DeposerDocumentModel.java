package Presentation.citoyen;

import Metier.Procedure;
import Presentation.Tools.ToolButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DeposerDocumentModel extends AbstractTableModel {
    Procedure           procedure;
    ArrayList<JPanel>   uploaders;

    public DeposerDocumentModel(Procedure procedure)  {
        super();
        this.procedure = procedure;
        uploaders = new ArrayList<>();
        for (int i = 0; i < procedure.getNbrDocuments(); i++) {
            ToolButton toolButton = new ToolButton("", "uploader document", 30, 30);
            try {
                toolButton.setIcon(!procedure.getUploadedDocument(i) ? "./resrc/upload.png" : "./resrc/valid.png", 30, 30);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            JPanel uploader = new JPanel();
            toolButton.button.setBorder(new EmptyBorder(5, 0, 0,0 ));
            uploader.add(toolButton.button, BorderLayout.CENTER);
            uploaders.add(uploader);
        }
    }
    @Override
    public int getRowCount() {
        return procedure.getNbrDocuments();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return uploaders.get(rowIndex);
            case 1 : return procedure.getDescDocuments().get(rowIndex);
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "";
            case 1 : return "   Documents";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return JPanel.class;
            case 1 : return String.class;
            default : return Object.class;
        }
    }
}
