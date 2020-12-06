package DAO;

import Metier.Document;
import Metier.Etape;
import Metier.Procedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOProcedure implements InterfaceDAOProcedure{

    Connection connection;

    public DAOProcedure()
    {
        connection = ConnexionBD.connexion();
    }

    @Override
    public boolean add(Procedure table) {
        try
        {
            String query = " insert into procédure (idProcedure,libellé)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, table.getID());
            preparedStmt.setString (2, table.getName());

            return preparedStmt.execute();
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean isExiste(Procedure table) {
        try
        {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from procédure where idProcedure="
                    +table.getID());
            if(rs.next())
                return true;
            return false;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean delete(Procedure table) {
        return false;
    }


    @Override
    public Procedure searchById(Object o) {
        return null;
    }

    @Override
    public ArrayList<Procedure> getAll() {
        try
        {
            ArrayList<Procedure> list = new ArrayList<Procedure>();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from procédure where Archive=0");
            while (rs.next())
                list.add(new Procedure(rs.getInt(1),rs.getString(2),
                        getAllEtapeofProcedure(rs.getInt(1)),
                        getAllDocOfProc(rs.getInt(1))));
            return list;
        }catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Boolean update(Procedure table, Boolean etat) {
        try {
            String query = " UPDATE  procédure SET Archive=? where idProcedure=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setBoolean(1,etat);
            preparedStmt.setInt(2, table.getID());
            return preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<Etape> getAllEtapeofProcedure(int IDproc)
    {
        try
        {
            ArrayList<Etape> list = new ArrayList<Etape>();
            Statement stmt=connection.createStatement();
            String query = new String("select ID,Numero from etape where Proc="+IDproc);
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
                list.add(new Etape(rs.getInt(1),rs.getInt(2),
                        getAllDocOfEtape(rs.getInt(1))));
            return list;
        }catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Document> getAllDocOfEtape(int IdEtape)
    {
        try
        {
            ArrayList<Document> list = new ArrayList<Document>();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from document where idDoc IN "+
                    "(select idDoc from docetape where idEtape='"+IdEtape+"')");
            while(rs.next())
                list.add(new Document(rs.getInt(1),rs.getString(2),
                        rs.getString(4)));
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }

    public ArrayList<Document> getAllDocOfProc(int IDproc)
    {
        try
        {
            ArrayList<Document> list = new ArrayList<Document>();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from document where proc="+
                        IDproc);
            while(rs.next())
                list.add(new Document(rs.getInt(1), rs.getString(2),
                        rs.getString(4)));
            return list;
        }catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
