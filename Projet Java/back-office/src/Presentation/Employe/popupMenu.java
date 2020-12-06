package Presentation.Employe;

import Metier.Employe;
import Metier.Procedure;
import Presentation.Tools.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class popupMenu extends MouseAdapter {
    JPopupMenu mainMenu;
    ArrayList<JCheckBox> list1,list2;
    JMenu menu1,menu2;
    ButtonGroup checkboxGroup;

    public popupMenu(Employe A, ArrayList<Procedure> procedures , JTable jTable, ArrayList<Employe> list) {
        mainMenu = new JPopupMenu();
        menu1 = new JMenu(A.getProcédures());
        menu2 = new JMenu("Modifier");
        menu2.setIcon(new ImageIcon("./resrc/modify.png"));
        list1 = new ArrayList<JCheckBox>();
        list2 = new ArrayList<JCheckBox>();
        checkboxGroup = new ButtonGroup();
        int i = 0;
        int k = 0;
        while(i < procedures.size())
        {
            if(procedures.get(i).getName().compareTo(A.getProcédures()) == 0)
                break;
            i++;
        }
        Procedure procedure;
        if(i == procedures.size())
            procedure = new Procedure(0,"",new ArrayList<>(),new ArrayList<>());
        else
            procedure = procedures.get(i);
        while (k < procedure.getEtape().size())
        {
            int numero = procedure.getEtape().get(k).getNumero();
            list1.add(new JCheckBox("Etape".concat(String.valueOf(numero))));
            if(A.getEtape().contains(numero))
                list1.get(k).setSelected(true);
            list1.get(k).addMouseListener(new MouseListnerForEtape(A,list1.get(k),procedure));
            menu1.add(list1.get(k));
            k++;
        }
        k = 0;
        while (k < procedures.size())
        {
            list2.add(new JCheckBox(procedures.get(k).getName()));
            if(procedures.get(k).getName().compareTo(A.getProcédures()) == 0)
                list2.get(k).setSelected(true);
            checkboxGroup.add(list2.get(k));
            list2.get(k).addMouseListener(new MouseListnerForPop(jTable, list, list.indexOf(A),
                    list2.get(k).getText(), procedures.get(k).getEtape(), this, procedures.get(k)));
            menu2.add(list2.get(k));
            k++;
        }
        mainMenu.add(menu1);
        mainMenu.add(menu2);
    }

    public JPopupMenu getMainMenu() {
        return mainMenu;
    }

    public JMenu getMenu1() {
        return menu1;
    }

    public JMenu getMenu2() {
        return menu2;
    }

    public ArrayList<JCheckBox> getList1() {
        return list1;
    }

    public ArrayList<JCheckBox> getList2() {
        return list2;
    }
}
