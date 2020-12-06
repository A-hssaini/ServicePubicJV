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

public class NouvelleDemandeModel extends AbstractTableModel {
    ArrayList<Demande>  demandes;
    ArrayList<JPanel>   generers;
    ArrayList<JPanel>   supprimers;
    ArrayList<JPanel>   voirs;

    public NouvelleDemandeModel(ArrayList<Demande> demandes) {
        super();
        this.demandes = demandes;
        setGenerers();
        setSupprimers();
        setVoirs();
    }

    private void setVoirs() {
        voirs = new ArrayList<JPanel>();
        for (int i = 0; i < demandes.size(); i++) {
            JButton button = new JButton("Vérifier");
            JPanel voir = new JPanel();
            voir.setLayout(new BorderLayout());
            voir.setPreferredSize(new Dimension(100, 35));
            button.setBorder(new EmptyBorder(0, 0, 0,0 ));
            voir.add(button, BorderLayout.CENTER);
            voir.add(new JLabel((ImageIcon) resizeIcon(new ImageIcon("./resrc/arrow_right.png"), 20, 20 ), JLabel.CENTER), BorderLayout.EAST);
            voirs.add(voir);
        }
    }

    private void setSupprimers() {
        supprimers = new ArrayList<JPanel>();
        for (int i = 0; i < demandes.size(); i++) {
            ToolButton toolButton = new ToolButton("", "supprimer demande", 30, 30);
            try {
                toolButton.setIcon("./resrc/rejete.png", 30, 30);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            JPanel archiver = new JPanel();
            toolButton.button.setBorder(new EmptyBorder(5, 0, 0,0 ));
            archiver.add(toolButton.button, BorderLayout.CENTER);
            supprimers.add(archiver);
        }
    }

    private void setGenerers() {
        generers = new ArrayList<JPanel>();
        for (int i = 0; i < demandes.size(); i++) {
            JButton button = new JButton("Généré jeton");
            JPanel generer = new JPanel();
            generer.setLayout(new BorderLayout());
            generer.setPreferredSize(new Dimension(150, 35));
            button.setBorder(new EmptyBorder(0, 0, 0,0 ));
            generer.add(button, BorderLayout.CENTER);
            generer.add(new JLabel((ImageIcon) resizeIcon(new ImageIcon("./resrc/arrow_right.png"), 20, 20 ), JLabel.CENTER), BorderLayout.EAST);
            generers.add(generer);
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
            case 0 : return "  " + demandes.get(rowIndex).getCin();
            case 1 : return "  " + demandes.get(rowIndex).getProcedure().getNom();
            case 2 : return generers.get(rowIndex);
            case 3 : return voirs.get(rowIndex);
            case 4 : return supprimers.get(rowIndex);
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex ) {
        switch(columnIndex) {
            case 0 : return "   Demandes (CIN)";
            case 1 : return "   Procédure";
            case 2 : return "";
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
            case 2 : return JPanel.class;
            case 3 : return JPanel.class;
            case 4 : return JPanel.class;
            default : return Object.class;
        }
    }
}
