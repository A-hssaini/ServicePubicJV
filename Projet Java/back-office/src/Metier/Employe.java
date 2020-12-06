package Metier;

import java.util.ArrayList;

public class Employe {
    String Login;
    String MotDePasse;
    String Nom;
    String Prenom;
    String Email;
    Boolean Chef;
    String Procédures;
    ArrayList<Integer> Etape;
    Boolean archiver;

    public Employe()
    {

    }

    public Employe (String login,String motDePasse ,String nom, String prenom,String email,
                    Boolean chef, String procédures, ArrayList<Integer> etape) {
        Login = login;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        Chef = chef;
        Procédures = procédures;
        Etape = etape;
        MotDePasse = motDePasse;
        archiver = false;
    }

    public String getLogin() {
        return Login;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public Boolean getChef() {
        return Chef;
    }

    public String getProcédures() {
        return Procédures;
    }

    public ArrayList<Integer> getEtape() {
        return Etape;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setChef(Boolean chef) {
        Chef = chef;
    }

    public void setProcédures(String procédures) {
        Procédures = procédures;
    }

    public void setEtape(ArrayList<Integer> etape) {
        Etape = etape;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }

    public Boolean getArchiver() {
        return archiver;
    }

    public void setArchiver(Boolean archiver) {
        this.archiver = archiver;
    }
}
