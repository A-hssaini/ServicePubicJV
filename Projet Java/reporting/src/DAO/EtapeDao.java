package DAO;

import java.util.ArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import Presentation.employe.Etape;

public class EtapeDao {

	public EtapeDao() {
		super();
		ConnectToDB.connexion();
	}

	public ArrayList<Etape> getmesEtapes() {
		ArrayList<Etape> mesEtapes = new ArrayList<Etape>();
		MongoCursor<Document> cursor = ConnectToDB.getEtapCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Etape nvEtape = gson.fromJson(cursor.next().toJson(), Etape.class);
			nvEtape.initEtape();
			mesEtapes.add(nvEtape);
		}
		return mesEtapes;
	}

}
