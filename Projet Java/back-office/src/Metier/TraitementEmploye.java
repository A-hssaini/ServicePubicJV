package Metier;
import DAO.DAOEmploye;
import DAO.DAOEmployéEtape;

import java.util.ArrayList;

public class TraitementEmploye {

    static public ArrayList<Employe> getAll()
    {
        DAOEmploye daoEmploye = new DAOEmploye();
        return daoEmploye.getAll();
    }

    static public void add(Employe employe)
    {
        DAOEmploye daoEmploye = new DAOEmploye();
        daoEmploye.add(employe);
        EmployeJSON.CreatJSonfile();
    }

    static public void changerProc(Employe employe)
    {
        DAOEmploye daoEmploye = new DAOEmploye();
        daoEmploye.ChangeProc(employe);
        EmployeJSON.CreatJSonfile();
    }

    static public void AjouterEtape(Employe employe, Etape etape)
    {
        DAOEmployéEtape employéEtape = new DAOEmployéEtape();
        employéEtape.add(employe, etape);
        EmployeJSON.CreatJSonfile();
    }

    static public void DeleteEtape(Employe employe, Etape etape)
    {
        DAOEmployéEtape employéEtape = new DAOEmployéEtape();
        employéEtape.delete(employe, etape);
        EmployeJSON.CreatJSonfile();
    }

    static public void ChangeChefStat(Employe employe, Boolean etat)
    {
        DAOEmploye daoEmploye = new DAOEmploye();
        daoEmploye.ChangeChefStat(employe, etat);
        EmployeJSON.CreatJSonfile();
    }

    static public void ChangeArchiveStat(Employe employe, Boolean etat)
    {
        DAOEmploye daoEmploye = new DAOEmploye();
        daoEmploye.updateArchive(employe, etat);
        EmployeJSON.CreatJSonfile();
    }

    static public void UpdateEmploye(Employe employe)
    {
        DAOEmploye daoEmploye = new DAOEmploye();
        daoEmploye.updateEmploye(employe);
        EmployeJSON.CreatJSonfile();
    }
}
