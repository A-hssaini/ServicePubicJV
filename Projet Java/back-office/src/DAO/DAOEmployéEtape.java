package DAO;

import Metier.*;
import Metier.Etape;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOEmployéEtape  implements  InterfaceDAOEmployéEtape{
    Connection connection;

    public DAOEmployéEtape() {
        connection = ConnexionBD.connexion();
    }
    @Override
    public boolean add(Employe emp, Etape etape) {
        try
        {
            String query = " insert into employeetape (idEmploye,idEtape)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, emp.getLogin());
            preparedStmt.setInt (2, etape.getID());

            return preparedStmt.execute();
        }catch (Exception e)
        {
            return false;
        }
    }
    @Override
    public boolean delete(Employe emp,Etape etape) {
        try
        {
            String query = " delete from employeetape where idEmploye=? and idEtape=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, emp.getLogin());
            preparedStmt.setInt(2,etape.getID());
            return (preparedStmt.execute());
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteAllEtapeOfEmploye(Employe employe) {
        try
        {
            String query = " delete from employeetape where idEmploye=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, employe.getLogin());
            return (preparedStmt.execute());
        }catch (Exception e)
        {
            return false;
        }
    }
}
