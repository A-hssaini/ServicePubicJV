package DAO;

import Metier.Demande;
import jdk.internal.dynalink.beans.StaticClass;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectToDB {
	private static ConnectToDB obj;
	static MongoDatabase database;

	public ConnectToDB() {
	}

	public static ConnectToDB getInstance() {
		if (obj == null)
			obj = new ConnectToDB();
		return obj;
	}

	public static void connexion() {
		MongoClient client = new MongoClient("localhost", 27017);
		database = client.getDatabase("mydb");
	}
	
	public static  MongoCollection<Document> getMyCollection()
	{
		return database.getCollection("Demande");
	}
	
	public static  MongoCollection<Document> getEtapCollection()
	{
		return database.getCollection("Etape");
	}
	
	public static  MongoCollection<Document> getprocedureCollection()
	{
		return database.getCollection("Procedure");
	}
}