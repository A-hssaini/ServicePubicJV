package DAO;

import Metier.Document;
import Metier.Procedure;


public interface InterfaceDAODocument {
    public abstract boolean add (Document table, Procedure proc);
    public abstract boolean delete(int IDDoc);
}
