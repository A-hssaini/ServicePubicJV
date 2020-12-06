package Metier;

import DAO.DAODocEtape;

public class TraitementDocEtape {

    static public void add(Etape etape, Document document)
    {
        DAODocEtape daoDocEtape = new DAODocEtape();
        daoDocEtape.add(document, etape);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }

    static public void delete(Etape etape, Document document)
    {
        DAODocEtape daoDocEtape = new DAODocEtape();
        daoDocEtape.delete(document, etape);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }
}
