package DAO;

import Metier.*;

import java.util.ArrayList;

public interface InterfaceDAOProcedure {
    public abstract boolean add (Procedure table);
    public abstract	boolean delete(Procedure table);
    public abstract	boolean isExiste(Procedure table);
    public abstract Procedure searchById(Object o);
    public abstract ArrayList<Procedure> getAll();
    public abstract	Boolean update(Procedure table, Boolean etat);
}
