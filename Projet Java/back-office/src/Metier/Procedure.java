package Metier;

import java.util.ArrayList;

public class Procedure {
	int ID;
	String Name;
	ArrayList<Etape> etape;
	ArrayList<Document> document;
	Boolean archiver;

	public Procedure(int ID, String Name, ArrayList<Etape> etape, ArrayList<Document> document)
	{
		this.ID = ID;
		this.Name = Name;
		this.etape = etape;
		this.document = document;
		this.archiver = false;
	}

	public String getName() {
		return Name;
	}

	public ArrayList<Etape> getEtape() {
		return etape;
	}

	public ArrayList<Document> getDocument() {
		return document;
	}

	public Boolean getArchiver() {
		return archiver;
	}

	public int getID() {
		return ID;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setEtape(ArrayList<Etape> etape) {
		this.etape = etape;
	}

	public void setDocument(ArrayList<Document> document) {
		this.document = document;
	}

	public void setArchiver(Boolean archiver) {
		this.archiver = archiver;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
}