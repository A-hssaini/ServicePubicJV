package DAO;

import Metier.*;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

public class DAOEmploye implements InterfaceDAOEmploye {

    Connection connection;

    public DAOEmploye()
    {
        connection = ConnexionBD.connexion();
    }

    @Override
    public boolean add(Employe table) {
        try
        {
            int id = 0;
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from procédure where libellé='"
                    +table.getProcédures()+"'");
            while (rs.next())
                id = rs.getInt(1);
            String query = " insert into employé (id,motDePasse,nom,prenom,email,Proc,Chef)"
                    + " values (?, ?, ?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, table.getLogin());
            preparedStmt.setString (2, table.getMotDePasse());
            preparedStmt.setString  (3, table.getNom());
            preparedStmt.setString (4, table.getPrenom());
            preparedStmt.setString (5, table.getEmail());
            preparedStmt.setInt(6,id);
            preparedStmt.setBoolean (7, table.getChef());

            return preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean isExiste(Employe table) {
        try
        {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from employé where id="
                    +table.getLogin());
            if(rs.next())
                return true;
            return false;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public Employe searchById(Object o) {
        return null;
    }

    @Override
    public boolean delete(Employe table) {
        return false;
    }


    @Override
    public ArrayList<Employe> getAll() {
        try
        {
            ArrayList<Employe> list = new ArrayList<Employe>();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from employé where Archiver=0");
            while(rs.next()) {
                String procedure = new String();
                Statement stmt1=connection.createStatement();
                ResultSet rs1=stmt1.executeQuery("select libellé from procédure where idProcedure="
                +rs.getInt(6)+" and Archive=0");
                while (rs1.next())
                    procedure = rs1.getString(1);
                list.add(new Employe(rs.getString(1), rs.getString(2),
                        rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getBoolean(7), procedure,
                        getAllEtapeOfEmploye(rs.getString(1),rs.getInt(6))));
            }
            return list;
        }catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Integer> getAllEtapeOfEmploye(String IdEmpl, int IdProc)
    {
        try
        {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Statement stmt=connection.createStatement();
            String query = new String("select ID,Numero from etape where ID IN "+
                    "(select idEtape from employeetape where idEmploye='"+IdEmpl+"')"+
                    " and Proc="+IdProc);
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
                list.add(new Integer(rs.getInt(2)));
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
            ResultSet rs=stmt.executeQuery("select libellé,description from document where idDoc IN "+
                    "(select idDoc from docetape where idEtape='"+IdEtape+"')");
            while(rs.next())
                list.add(new Document(rs.getInt(1),
                        rs.getString(2),rs.getString(4)));
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean ChangeProc(Employe employe) {
        try
        {
            int idProc = 0;
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from procédure where libellé='"
                    +employe.getProcédures()+"'");
            while (rs.next())
                idProc = rs.getInt(1);
            String query = " UPDATE  employé SET Proc=? where id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1,idProc);
            preparedStmt.setString(2,employe.getLogin());
            //Pour supprimer les etapes de la procédure precedent
            DAOEmployéEtape daoEmployéEtape = new DAOEmployéEtape();
            daoEmployéEtape.deleteAllEtapeOfEmploye(employe);
            return preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean ChangeChefStat(Employe employe, Boolean etat) {
        try {
            String query = " UPDATE  employé SET Chef=? where id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setBoolean(1,etat);
            preparedStmt.setString(2, employe.getLogin());
            return preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateArchive(Employe table, Boolean etat) {
        try {
            String query = " UPDATE  employé SET Archiver=? where id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setBoolean(1,etat);
            preparedStmt.setString(2, table.getLogin());
            return preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean updateEmploye(Employe table) {
        try {
            String query = " UPDATE  employé SET nom=?,prenom=?,motDePasse=?,email=? where id=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,table.getNom());
            preparedStmt.setString(2, table.getPrenom());
            preparedStmt.setString(3,table.getMotDePasse());
            preparedStmt.setString(4, table.getEmail());
            preparedStmt.setString(5,table.getLogin());
            return preparedStmt.execute();
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
}
