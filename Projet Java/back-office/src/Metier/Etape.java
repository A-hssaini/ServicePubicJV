package Metier;

import java.util.ArrayList;

public class Etape {
	int ID;
	Integer numero;
	ArrayList<Document> document;

	public Etape(int ID, Integer numero, ArrayList<Document> document)
	{
		this.ID = ID;
		this.numero = numero;
		this.document = document;
	}

	public Etape(Integer numero) {
		int ID = 0;
		this.numero = numero;
		document = new ArrayList<Document>();
	}

	public Integer getNumero() {
		return numero;
	}

	public ArrayList<Document> getDocument() {
		return document;
	}

	public int getID() {
		return ID;
	}

	public String DocToString()
	{
		String all = new String();
		for(int i = 0;i < document.size(); i++)
		{
			if(i == 0)
				all = all.concat(document.get(0).getNom());
			else
			{
				all = all.concat(new String(","));
				all = all.concat(document.get(i).getNom());
			}
		}
		return all;
	}
}