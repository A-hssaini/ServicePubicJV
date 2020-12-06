package Metier;

import DAO.DAODocument;

public class TraitementDocument {

    static public void add(Document document, Procedure procedure)
    {
        DAODocument daoDocument = new DAODocument();
        daoDocument.add(document, procedure);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }

    static public void delete(Document document)
    {
        DAODocument daoDocument = new DAODocument();
        daoDocument.delete(document.getID());
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }
}
