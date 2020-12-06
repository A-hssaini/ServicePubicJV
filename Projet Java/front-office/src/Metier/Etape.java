package Metier;

import java.sql.Date;
import java.util.ArrayList;

public class Etape {
	Integer numero;
	Etat etat;
	ArrayList<Documentt> document;

	
	
	public Etape() {
		super();
		document = new ArrayList<Documentt>();
		// TODO Auto-generated constructor stub
	}
	
	
	public Etape(Integer numero, Etat etat, ArrayList<Documentt> document) {
		super();
		this.document = new ArrayList<Documentt>();
		this.numero = numero;
		this.etat = etat;
		this.document = document;
	}


	public Etape(Integer numero,Etat etat, ArrayList<Documentt> document, String date_debut, String date_fin) {
		super();
		this.etat = etat;
		this.numero = numero;
		this.document = document;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public ArrayList<Documentt> getDocument() {
		return document;
	}
	public void setDocument(ArrayList<Documentt> document) {
		this.document = document;
	}


	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}


	@Override
	public String toString() {
		return "Etape [numero=" + numero + ", etat=" + etat + ", document=" + document + "]";
	}

	
	
}
