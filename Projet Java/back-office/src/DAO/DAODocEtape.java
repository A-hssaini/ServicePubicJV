package DAO;

import Metier.Document;
import Metier.Etape;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAODocEtape implements InterfaceDAODocEtape {
    Connection connection;

    public DAODocEtape() {
        connection = ConnexionBD.connexion();
    }

    @Override
    public boolean add(Document doc,Etape etape) {
        try
        {
            String query = " insert into docetape (idEtape,idDoc)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, etape.getID());
            preparedStmt.setInt (2, doc.getID());

            return preparedStmt.execute();
        }catch (Exception e)
        {
            return false;
        }
    }
    @Override
    public boolean delete(Document doc,Etape etape) {
        try
        {
            String query = " delete from docetape where idEtape=? and idDoc=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, etape.getID());
            preparedStmt.setInt(2,doc.getID());
            return (preparedStmt.execute());
        }catch (Exception e)
        {
            return false;
        }
    }
}
