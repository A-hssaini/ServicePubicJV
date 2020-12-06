package DAO;

import Metier.Etape;
import Metier.*;

public interface InterfaceDAOEtape {
    public abstract boolean add (Etape table, Procedure proc);
    public abstract	boolean delete(Etape table);
    public abstract void deleteEtapeFromDocEtape(int ID);
    public abstract void deleteEtapeFromEmployeEtape(int ID);
}
