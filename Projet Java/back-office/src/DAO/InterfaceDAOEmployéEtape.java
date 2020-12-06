package DAO;

import Metier.Employe;
import Metier.Etape;

public interface InterfaceDAOEmployéEtape {
    public boolean add(Employe emp, Etape etape);
    public boolean delete(Employe emp, Etape etape);
    public boolean deleteAllEtapeOfEmploye(Employe emp);
}
