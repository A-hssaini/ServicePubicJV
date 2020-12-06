package Presentation.Tools;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableCellRenderer;

public class JTableButtonRenderer implements TableCellRenderer {
	   private TableCellRenderer defaultRenderer;
	   
	   public JTableButtonRenderer(TableCellRenderer renderer) {
	      defaultRenderer = renderer;
	   }
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, 
			   boolean isSelected, boolean hasFocus, int row, int column) { 
		   JComponent c = (JComponent)value;
		   c.setBorder(BorderFactory.createEmptyBorder());
		  	         return c;
	      
	   }
	}
