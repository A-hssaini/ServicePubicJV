package Presentation.Tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToolButton {
    private ImageIcon   buttonIcon;
    private int             width, height;
    public JButton          button;

    public ToolButton(String text, String tooltip, int width, int height) {
        this.width = width;
        this.height = height;
        button = new JButton(text);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBackground(null);
        button.setPreferredSize(new Dimension(width, height));
        button.setToolTipText(tooltip);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("Gill Sans", 0, 24));
        button.setForeground(Color.WHITE);
    }
    public void setColor(Color color) {
        button.setForeground(color);
    }
    public void setIcon(String filename, int width, int height) throws IOException {
        buttonIcon = new ImageIcon(filename);
        button.setIcon(resizeIcon(buttonIcon, width, height ));
        button.setOpaque(false);
    }

    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
