package DAO;

import Metier.Etape;
import Metier.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOEtape implements InterfaceDAOEtape{

    Connection connection;

    public DAOEtape() {
        connection = ConnexionBD.connexion();
    }

    @Override
    public boolean add(Etape table, Procedure proc) {
        try
        {
            String query = " insert into etape (ID,Numero,Proc)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1,table.getID());
            preparedStmt.setInt (2,table.getNumero());
            preparedStmt.setInt(3,proc.getID());

            return preparedStmt.execute();
        }catch (Exception e)
        {
            return false;
        }
    }
    @Override
    public boolean delete(Etape table) {
        try
        {
            deleteEtapeFromDocEtape(table.getID());//Supprimer les relations avec les documents
            deleteEtapeFromEmployeEtape(table.getID());//Supprimer les relations avec les employés
            String query = " delete from etape where ID=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, table.getID());
            return (preparedStmt.execute());
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public void deleteEtapeFromDocEtape(int ID)
    {
        try
        {
            String query = " delete from docetape where idEtape=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, ID);
            preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteEtapeFromEmployeEtape(int ID)
    {
        try
        {
            String query = " delete from employeetape where idEtape=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, ID);
            preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
