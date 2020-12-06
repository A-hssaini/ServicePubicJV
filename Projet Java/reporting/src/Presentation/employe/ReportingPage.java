package Presentation.employe;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import DAO.EtapeDao;
import DAO.ProcedureDAO;

public class ReportingPage extends JFrame {
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPane2;
	private JTable table2;

	public ReportingPage(EtapeDao etapeDao, ProcedureDAO procedureDAO) {
		super();
		JPanel newComandePanel = new JPanel();
		JPanel ConsulterComandePanel = new JPanel();
		JTabbedPane tp = new JTabbedPane();
		tp.setBounds(50, 50, 200, 200);
		tp.add("Procedure", newComandePanel);
		tp.add("Etape", ConsulterComandePanel);
		table = new JTable(new ProcedureModel(procedureDAO.getMesProcedures()));
		table.setGridColor(new java.awt.Color(238, 238, 238));
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		newComandePanel.setLayout(new BorderLayout(50, 5));
		JPanel panel3 = new JPanel();
		panel3.add(scrollPane, BorderLayout.CENTER);
		newComandePanel.add(panel3, BorderLayout.CENTER);

		TableCellRenderer tableRenderer2;
		table2 = new JTable(new EtapeModel(etapeDao.getmesEtapes()));
		table2.setGridColor(new java.awt.Color(238, 238, 238));
		DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) table2.getDefaultRenderer(Object.class);
		renderer2.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBorder(BorderFactory.createEmptyBorder());
		ConsulterComandePanel.setLayout(new BorderLayout(50, 5));
		JPanel panel4 = new JPanel();
		panel4.add(scrollPane2, BorderLayout.CENTER);
		ConsulterComandePanel.add(panel4, BorderLayout.CENTER);

		add(tp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 500);
		setVisible(true);
	}

}
