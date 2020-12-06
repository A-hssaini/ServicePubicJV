package Presentation.Chef2;

import Metier.Demande;
import Presentation.Tools.ToolButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static Presentation.Tools.ToolButton.resizeIcon;

public class ConsulterDemandeModel extends AbstractTableModel {
    ArrayList<Demande>  demandes;
    ArrayList<JPanel>   traiters;
    ArrayList<JPanel>   archivers;

    public ConsulterDemandeModel(ArrayList<Demande> demandes) {
        super();
        this.demandes = demandes;
        setTraiters();
        setArchivers();
    }

    private void setArchivers() {
        archivers = new ArrayList<JPanel>();
        for (int i = 0; i < demandes.size(); i++) {
            ToolButton toolButton = new ToolButton("", "archiver demande", 30, 30);
            try {
                toolButton.setIcon("./resrc/archiver.png", 30, 30);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            JPanel archiver = new JPanel();
            toolButton.button.setBorder(new EmptyBorder(0, 0, 0,0 ));
            archiver.add(toolButton.button, BorderLayout.CENTER);
            archivers.add(archiver);
        }
    }

    private void setTraiters() {
        traiters = new ArrayList<JPanel>();
        for (int i = 0; i < demandes.size(); i++) {
            JButton button = new JButton("Traiter");
            JPanel traiter = new JPanel();
            traiter.setLayout(new BorderLayout());
            traiter.setPreferredSize(new Dimension(50, 35));
            button.setBorder(new EmptyBorder(0, 0, 0,0 ));
            traiter.add(button, BorderLayout.CENTER);
            traiter.add(new JLabel((ImageIcon) resizeIcon(new ImageIcon("./resrc/arrow_right.png"), 20, 20 ), JLabel.CENTER), BorderLayout.EAST);
            traiters.add(traiter);
        }
    }

    @Override
    public int getRowCount() {
        return demandes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return "  " + demandes.get(rowIndex).getJeton();
            case 1 : return "  " + demandes.get(rowIndex).getProcedure().getNom();
            case 2 : return "  " + demandes.get(rowIndex).getEtat();
            case 3 : return traiters.get(rowIndex);
            case 4 : return archivers.get(rowIndex);
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "   Demandes (jeton)";
            case 1 : return "   Procédure";
            case 2 : return "   État";
            case 3 : return "";
            case 4 : return "";
            default : return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return String.class;
            case 3 : return JPanel.class;
            case 4 : return JPanel.class;
            default : return Object.class;
        }
    }
}
