package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBD {
    static String URL = new String("jdbc:mysql://localhost:3306/back-office");
    static String Login = new String("root");
    static String Password = new String("");
    private static ConnexionBD obj;

    private ConnexionBD()
    {

    }

    public static ConnexionBD getInstance()
    {
        if (obj==null)
            obj = new ConnexionBD();
        return obj;
    }

    public static Connection connexion()
    {
        Connection con;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(ConnexionBD.URL,ConnexionBD.Login,ConnexionBD.Password);
            return con;
        }catch(Exception e){ System.out.println(e);}
        return null;
    }
}
