package Presentation.Tools;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

import static Presentation.Tools.ToolButton.resizeIcon;

public class ToolTextField extends JTextField implements FocusListener {
    private boolean roundRect;
    private Shape shape;
    private JTextField jtf;
    private Icon icon;
    private String hint;

    public ToolTextField(String hint, boolean roundRect){
        this.roundRect = roundRect;
        this.jtf = new JTextField();
        this.hint = hint;
        addFocusListener(this);
    }

    public ToolTextField(String hint, boolean roundRect, String icon, int iconWidth, int iconHeight){
        this.roundRect = roundRect;
        setOpaque(false);
        this.jtf = new JTextField();
        setIcon(resizeIcon(new ImageIcon(icon), iconWidth, iconHeight ));
        this.hint = hint;

        addFocusListener(this);
    }

    public void setIcon(Icon newIcon){
        this.icon = newIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (roundRect) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        super.paintComponent(g);

        if(this.icon != null) {
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            int x = this.getWidth() - icon.getIconWidth() - 5;
            int y = (this.getHeight() - iconHeight)/2;
            icon.paintIcon(this, g, x, y); /* icon coordinate */
        }

        if ( this.getText().equals("")) {
            int width = this.getWidth();
            int height = this.getHeight();
            Font prev = g.getFont();
            Font italic = prev.deriveFont(Font.ITALIC);
            Color prevColor = g.getColor();
            g.setFont(italic);
            g.setColor(UIManager.getColor("textInactiveText"));
            int h = g.getFontMetrics().getHeight();
            int textBottom = (height - h) / 2 + h - 4;
            int x = this.getInsets().left;
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints hints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(hint, x, textBottom);
            g2d.setRenderingHints(hints);
            g.setFont(prev);
            g.setColor(prevColor);
        }

    }
    @Override
    protected void paintBorder(Graphics g) {
        if (roundRect) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
    }
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }

    @Override
    public void focusGained(FocusEvent arg0) {
        this.repaint();
    }

    @Override
    public void focusLost(FocusEvent arg0) {
        this.repaint();
    }


}