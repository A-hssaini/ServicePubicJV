package Presentation;

import DAO.DAOProcedure;
import Metier.EmployeJSON;
import Metier.Procedure;
import Metier.ProcedureJSON;
import Presentation.Login.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Login auth = new Login("Admin", "Password");
        auth.setVisible(true);
    }
}
