package DAO;

import Metier.Document;
import Metier.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAODocument implements InterfaceDAODocument{
    Connection connection;

    public DAODocument() {
        connection = ConnexionBD.connexion();
    }

    @Override
    public boolean add(Document table, Procedure proc) {
        try
        {
            String query = " insert into document (idDoc,libellé,proc,description)"
                    + " values (?, ?, ?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, table.getID());
            preparedStmt.setString (2, table.getNom());
            preparedStmt.setInt  (3, proc.getID());
            preparedStmt.setString (4, table.getDescription());

            return preparedStmt.execute();
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean delete(int idDoc) {
        try
        {
            //Supprimer la relation étape document
            deleteDocumentFromDocumentEtape(idDoc);
            String query = " delete from document where idDoc=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1,idDoc);
            return (preparedStmt.execute());
        }catch (Exception e)
        {
            return false;
        }
    }

    public boolean deleteDocumentFromDocumentEtape(int idDoc) {
        try
        {
            String query = " delete from docetape where idDoc=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1,idDoc);
            return (preparedStmt.execute());
        }catch (Exception e)
        {
            return false;
        }
    }


}
