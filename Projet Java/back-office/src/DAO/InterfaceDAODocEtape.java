package DAO;

import Metier.Document;
import Metier.Etape;

public interface InterfaceDAODocEtape {
    public abstract boolean add (Document doc, Etape etape);
    public abstract	boolean delete(Document doc,Etape etape);
}
