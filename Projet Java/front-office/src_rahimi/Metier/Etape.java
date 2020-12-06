package Metier;

import java.sql.Date;
import java.util.ArrayList;

public class Etape {
	Integer numero;
	Etat etat;
	ArrayList<Document> document;
	String date_debut;
	String date_fin;
	public Etape(Integer numero,Etat etat, ArrayList<Document> document, String date_debut, String date_fin) {
		super();
		this.etat = etat;
		this.numero = numero;
		this.document = document;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public ArrayList<Document> getDocument() {
		return document;
	}
	public void setDocument(ArrayList<Document> document) {
		this.document = document;
	}
	public String getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(String currentTime) {
		this.date_debut = currentTime;
	}
	public String getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Etape [numero=" + numero + ", etat=" + etat + ", document=" + document + ", date_debut=" + date_debut + ", date_fin="
				+ date_fin + "]";
	}
	
	
}
