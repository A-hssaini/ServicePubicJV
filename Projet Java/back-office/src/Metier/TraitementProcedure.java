package Metier;

import DAO.DAOProcedure;

import java.util.ArrayList;

public class TraitementProcedure {

    static public ArrayList<Procedure> getAll()
    {
        DAOProcedure daoProcedure = new DAOProcedure();
        return daoProcedure.getAll();
    }

    static public void add(Procedure procedure)
    {
        DAOProcedure daoProcedure = new DAOProcedure();
        daoProcedure.add(procedure);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }

    static public void update(Procedure procedure, Boolean stat)
    {
        DAOProcedure daoProcedure = new DAOProcedure();
        daoProcedure.update(procedure, stat);
        ProcedureJSON.CreatJSonfile();
        EmployeJSON.CreatJSonfile();
    }
}
