package Metier;

import DAO.DAOEtape;

public class TraitementEtape {

    static public void add(Etape etape, Procedure procedure)
    {
        DAOEtape daoEtape = new DAOEtape();
        daoEtape.add(etape, procedure);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }

    static public void delete(Etape etape)
    {
        DAOEtape daoEtape = new DAOEtape();
        daoEtape.delete(etape);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }
}
