package Presentation.Tools;

import Metier.Employe;
import Metier.Etape;
import Metier.Procedure;
import Metier.TraitementEmploye;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListnerForEtape extends MouseAdapter {
    Employe employe;
    JCheckBox jCheckBox;
    Procedure procedure;

    public MouseListnerForEtape(Employe etudiant, JCheckBox jCheckBox, Procedure procedure) {
        this.employe = etudiant;
        this.jCheckBox = jCheckBox;
        this.procedure = procedure;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        String text = jCheckBox.getText();
        int t = Integer.parseInt(String.valueOf(text.charAt(text.length() - 1)));
        int i = this.SearchIDodEtape(t, procedure.getEtape());
        if(jCheckBox.isSelected())
        {
            if (!employe.getEtape().contains(t)) {
                TraitementEmploye.AjouterEtape(employe, procedure.getEtape().get(i));
                employe.getEtape().add(t);
            }
        }
        else {
                if (employe.getEtape().contains(t)) {
                    TraitementEmploye.DeleteEtape(employe, procedure.getEtape().get(i));
                    this.remove(employe.getEtape(), t);
                }
            }
    }

    private void remove(ArrayList<Integer> li, Integer r)
    {
        int i = 0;
        while (i < li.size())
        {
            if(li.get(i) == r)
                li.remove(i);
            i++;
        }
    }

    private int SearchIDodEtape(int numero, ArrayList<Etape> etapes)
    {
        int i;
        for (i = 0; i < etapes.size(); i++)
        {
            if (etapes.get(i).getNumero() == numero)
                break;
        }
        return i;
    }
}
