package Presentation.Tools;

import java.awt.Desktop;
import java.io.File;

public class OpenFile
{
    File    file;
    Desktop desktop;
    public OpenFile(String filename) {
        try {
            /* constructor of file class having file as argument */
            file = new File(filename);
            /* check if Desktop is supported by Platform or not */
            if (!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }
            desktop = Desktop.getDesktop();
            /* checks file exists or not */
            if (file.exists())
                desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}