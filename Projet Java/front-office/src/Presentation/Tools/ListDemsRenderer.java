package Presentation.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ListDemsRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        JLabel c = (JLabel)super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column);
//            String pathValue = value.toString(); // Could be value.toString()
//            c.setToolTipText("<html><p width=\"350px\">" + pathValue + "</p></html>");
        c.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        // ...OR this probably works in your case:
        //c.setToolTipText(c.getText());
        return c;
    }
}