package Presentation.employe2;

import Metier.Demande;
import Presentation.Tools.JTableButtonRenderer;
import Presentation.Tools.SimpleHeaderRenderer;
import Presentation.procedure.ServicePublic;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import DAO.Controleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmployePage extends JFrame {

  //  private ArrayList<Demande>  ConsulterDemandes;
    private JScrollPane         scrollPaneConsulter;
    private JTable              tableConsulter;
    private JPanel              contentPanel;
    private Controleur controleur;
    
    public EmployePage(Controleur controleur) {
        super("service-public");
        this.controleur = controleur;
        this.controleur.setTraiterCommandePage(this);
        setDefaultOperations();
        initConsulterOnglet();
        contentPanel.setBorder(BorderFactory.createTitledBorder("Traiter demandes"));
        contentPanel.add(scrollPaneConsulter, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
            }

    private void initConsulterOnglet() {
        tableConsulter = new JTable();
        scrollPaneConsulter = new JScrollPane(tableConsulter);
        contentPanel = new JPanel(new BorderLayout());
        scrollPaneConsulter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPaneConsulter.setBackground(new Color(237, 237, 237));
        tableConsulterCustomize();
        tableConsulter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e){
                int row = tableConsulter.rowAtPoint(e.getPoint());
                int col = tableConsulter.columnAtPoint(e.getPoint());
                tableConsulterEvents(col, row);
            }
        });

    }

    private void tableConsulterEvents(int col, int row) {
        if (col == 3) {
          /*  HashMap<String, String> hash = new HashMap<String, String>();
            hash.put("Doc1", "Desc de 1er document");
            hash.put("Doc2", "Desc de 2eme document");
            hash.put("Doc3", "Desc de 3eme document");
            hash.put("Doc4", "Desc de 4eme document");
            ServicePublic mise = new ServicePublic(hash, new String("Etape-N3"));
            mise.setVisible(true);*/
        	
        //	controleur.test(row);////////
        //////////////////////////	controleur.nextEtape(row);
        	
        	controleur.showDocument(row);
        }
    }

    private void tableConsulterCustomize() {
        tableConsulter.setModel(new ConsulterDemandeModel(controleur.getMesDemandes()));
        tableConsulter.getTableHeader().setReorderingAllowed(false);
        TableCellRenderer tableRenderer = tableConsulter.getDefaultRenderer(JPanel.class);
        tableConsulter.setDefaultRenderer(JPanel.class, new JTableButtonRenderer(tableRenderer));
        tableConsulter.getColumnModel().getColumn(3).setHeaderRenderer(new SimpleHeaderRenderer());
        tableConsulter.getTableHeader().setPreferredSize(new Dimension(750, 50));
        tableConsulter.getTableHeader().setFont(new Font("Gill Sans", Font.PLAIN, 24));
        TableColumnModel columnModel = tableConsulter.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        tableConsulter.setRowHeight(50);
        
        
   		Runnable helloRunnable = new Runnable() {
   	    public void run() {
   	     tableConsulter.setModel(new ConsulterDemandeModel(controleur.getMesDemandes()));
   	    }
   	};

   	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
   	executor.scheduleAtFixedRate(helloRunnable, 0, 5, TimeUnit.SECONDS);
    }

    private void setDefaultOperations() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setSize(800, 750);
        //setResizable(false); //On interdit la redimensionnement de la fenêtre
        setMinimumSize(new Dimension(800, 750));
        setLayout(new BorderLayout());
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
    
    public void resatartTable() {
		  //  tableConsulter.setModel(new ConsulterDemandeModel(controleur.getMesDemandes()));
	}
}
