package Metier;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.corba.se.spi.ior.ObjectId;

public class Demande {
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private String id;
	private String jeton;
	private Procedure procedure;
	private Etat etat;
	private String cin;
	private ArrayList<Documentt> documents;///////////////
	private ArrayList<String> DocsPath;////////
	private Integer numEtape;
	String date_debut;
	String date_fin;

	public Demande() {
		super();
		this.DocsPath = new ArrayList<>();
		documents = new ArrayList<Documentt>();
		// TODO Auto-generated constructor stub
	}

	public Demande(String jeton, Procedure procedure, Etat etat) {
		this.jeton = jeton;
		this.procedure = procedure;
		this.etat = etat;
		this.DocsPath = new ArrayList<>();
	}

	public ArrayList<Documentt> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Documentt> documents) {
		this.documents = documents;
	}

	public String getJeton() {
		return jeton;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setJeton(String jeton) {
		this.jeton = jeton;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCin() {
		return cin;
	}

	public String getDocsPath(int i) {
		if (i < DocsPath.size())
			return DocsPath.get(i);
		return null;
	}

	public void addDocsPath(String docsPath) {
		if (DocsPath.size() < procedure.getNbrDocuments())
			DocsPath.add(docsPath);
		else
			System.out.println(
					"Erreur: le nombre des chemins ajoutées est plus grand que le nombre des documents nécessaires !");
	}

	public void addDocsPathByIndex(int index, String docsPath) {
		DocsPath.add(index, docsPath);
	}

	public void displayPaths() {
		for (int i = 0; i < DocsPath.size(); i++)
			System.out.println(DocsPath.get(i));
	}

	public void genererJeton() {

		this.jeton = this.id;
		/*
		 * StringBuilder builder = new StringBuilder(); int length = 8; while (length--
		 * != 0) { int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
		 * builder.append(ALPHA_NUMERIC_STRING.charAt(character)); } this.jeton =
		 * builder.toString(); System.out.println(this.jeton); // if
		 * (jetonExist(this.jeton)) // genererJeton();
		 */

	}

	public ArrayList<String> getDocsPath() {
		return DocsPath;
	}

	public void setDocsPath(ArrayList<String> docsPath) {
		DocsPath = docsPath;
	}

	public Integer getNumEtape() {
		return numEtape;
	}

	public void setNumEtape(Integer numEtape) {
		this.numEtape = numEtape;
	}

	public ArrayList<String> getAllDocsPath() {
		return DocsPath;
	}

	public void setAllDocsPath(ArrayList<String> docsPath) {
		DocsPath = docsPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	@Override
	public String toString() {
		return "Demande [id=" + id + ", jeton=" + jeton + ", procedure=" + procedure + ", etat=" + etat + ", cin=" + cin
				+ ", documents=" + documents + ", DocsPath=" + DocsPath + ", numEtape=" + numEtape + ", date_debut="
				+ date_debut + ", date_fin=" + date_fin + "]";
	}

}
