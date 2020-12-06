package DAO;

import Metier.Employe;

import java.util.ArrayList;

public interface InterfaceDAOEmploye {
    public abstract boolean add (Employe table);
    public abstract	boolean delete(Employe table);
    public abstract	boolean isExiste(Employe table);
    public abstract	Employe searchById(Object o);
    public abstract ArrayList<Employe> getAll();
    public abstract boolean ChangeProc(Employe employe);
    public abstract boolean ChangeChefStat(Employe employe, Boolean etat);
    public abstract Boolean updateArchive(Employe table, Boolean etat);
    public abstract Boolean updateEmploye(Employe table);
}
