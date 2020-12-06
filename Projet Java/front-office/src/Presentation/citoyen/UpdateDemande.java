package Presentation.citoyen;

import Metier.Demande;
import Metier.Etat;
import Metier.Procedure;
import Presentation.Tools.JTableButtonRenderer;
import Presentation.Tools.SimpleHeaderRenderer;
import Presentation.Tools.ToolButton;
import Presentation.Tools.ListDocsRenderer;
import Presentation.Tools.ToolTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateDemande extends JFrame {
    CitoyenPage parent;
    Demande     demande;
    JPanel      DeposerPanel;
    JScrollPane scrollPaneD;
    ToolButton  Envoyer;
    JTable      ListDocuments;
    JLabel      labelProcedures;
    Procedure procedure;

    UpdateDemande(Procedure procedure, Demande demande, CitoyenPage parent) {
        super("service-public");
        this.procedure = procedure;
        this.parent = parent;
        this.demande = demande;
        setDefaultOperations();
        deposerOnglet();
        setBgForComponents();
    }
    private void deposerOnglet() {
        initAttributesDeposer();
        DeposerPanel.setLayout(new BorderLayout());
        DeposerPanel.add(scrollPaneD, BorderLayout.CENTER);
        DeposerPanel.add(Envoyer.button, BorderLayout.SOUTH);
        add(DeposerPanel);
    }
    private void initAttributesDeposer() {
        DeposerPanel = new JPanel();
        Envoyer = new ToolButton("Envoyer", "modifier la demande", 200, 35);
        ListDocuments = new JTable(new DeposerDocumentModel(procedure));
        scrollPaneD = new JScrollPane(ListDocuments);
        labelProcedures = new JLabel("Procédures");
        initScrollPane(scrollPaneD);
        initTableDocuments();
        Envoyer.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValiderDemande();
            }
        });
    }
    private void ValiderDemande() {
        for (int i = 0; i < procedure.getNbrDocuments(); i++) {
        	System.out.println(procedure.getUploadedDocument());///////////////
            if (procedure.getUploadedDocument(i) == false) {
                JOptionPane.showMessageDialog(this, "Des documents manquants", "Alert", JOptionPane.ERROR_MESSAGE);
                parent.setVisible(true);
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Documents modifiés avec succès", "Info", JOptionPane.INFORMATION_MESSAGE);
        demande.setEtat(Etat.ENCORE);
        demande.displayPaths();
        /* send it to DB  */
        parent.updateListDem();
        parent.setVisible(true);
        dispose();
    }
    private void initTableDocuments() {
        tableDocCustomize();
        ListDocuments.getTableHeader().setPreferredSize(new Dimension(500, 50));
        ListDocuments.getTableHeader().setFont(new Font("Gill Sans", Font.PLAIN, 24));
        ListDocuments.getTableHeader().setBackground(new Color(128,128,128));
        ListDocuments.getTableHeader().setForeground(Color.WHITE);
        ListDocuments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e){
                int row = ListDocuments.rowAtPoint(e.getPoint());
                int col = ListDocuments.columnAtPoint(e.getPoint());
                uploadDoc(row, col);
            }
        });
    }
    public void uploadDoc(int row, int col) {
        if(col == 0) {
            JFileChooser choix = new JFileChooser();
            int retour = choix.showOpenDialog(this);
            if (retour == JFileChooser.APPROVE_OPTION) {/* un fichier a été choisi (sortie par OK)*/
                JOptionPane.showMessageDialog(this, "fichier: " + choix.getSelectedFile().getName() + " a été choisi\npath: " + choix.getSelectedFile().getAbsolutePath(), "Info", JOptionPane.INFORMATION_MESSAGE);
                demande.addDocsPathByIndex(row, choix.getSelectedFile().getAbsolutePath());
                procedure.setUploadedDocument(row, true);
                updateListDoc();
            }
        }
    }
    private void updateListDoc() {
        ListDocuments.setModel(new DeposerDocumentModel(procedure));
        tableDocCustomize();
    }
    public void tableDocCustomize() {
        TableCellRenderer tableRenderer = ListDocuments.getDefaultRenderer(JPanel.class);
        ListDocuments.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
        ListDocuments.getColumnModel().getColumn(0).setHeaderRenderer(new SimpleHeaderRenderer());
        ListDocuments.getColumnModel().getColumn(1).setCellRenderer( new ListDocsRenderer() );
        TableColumnModel columnModel = ListDocuments.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(1).setPreferredWidth(550);
        ListDocuments.setRowHeight(50);
        colorListDocsCells();
    }
    private void colorListDocsCells()
    {
        for (int row = 0; row < ListDocuments.getRowCount(); row++) {
            Component comp = ListDocuments.prepareRenderer(ListDocuments.getCellRenderer(row, 1), row, 1);
            comp.setBackground(Color.lightGray);
        }
    }
    private void initScrollPane(JScrollPane scrollpane) {
        scrollpane.getViewport().setBackground(Color.lightGray);
        scrollpane.setBorder(new EmptyBorder(20, 10, 10,10 ));
        scrollpane.setBackground(new Color(128,128,128));
    }
    private void setBgForComponents() {
        DeposerPanel.setBackground(new Color(128,128,128));
        this.getContentPane().setBackground(Color.darkGray);
    }
    private void setDefaultOperations() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setSize(600, 400);
        //setResizable(false); //On interdit la redimensionnement de la fenêtre
        setMinimumSize(new Dimension(600, 400));
        closeEvent();
    }

    private void closeEvent() {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new AbstractAction()  {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
