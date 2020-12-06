package DAO;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import Metier.Demande;

public class DemandeDAO {
	private DBCollection myCollection;
	private ArrayList<Demande> mesDemande;


	public DemandeDAO(DBCollection myCollection) {
		super();
		this.myCollection = myCollection;
		mesDemande = new ArrayList<Demande>();
	}


	public void getAlllDemande() {
		DBCursor cursor = myCollection.find();
		while (cursor.hasNext()) {
			DBObject theObj = cursor.next();
			Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(theObj.toMap());
			Demande nvDemande = gson.fromJson(jsonElement, Demande.class);
			nvDemande.setJeton(theObj.get("_id").toString());
			mesDemande.add(nvDemande);
			}
	}


	public ArrayList<Demande> getMesDemande() {
		getAlllDemande();
		return mesDemande;
	}


	public void setMesDemande(ArrayList<Demande> mesDemande) {
		this.mesDemande = mesDemande;
	}
	
}
