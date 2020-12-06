package DAO;


import Metier.Demande;


import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.mongodb.MongoClient;


public class ConnectToDB { 
	private DBCollection myCollection;
 
public ConnectToDB() {
	MongoClient client = new MongoClient("localhost",27017);
	DB db = client.getDB( "mydb" );
	myCollection = db.getCollection("Demande");
	Demande demande = new Demande();
}


public DBCollection getMyCollection() {
	return myCollection;
}

public void setMyCollection(DBCollection myCollection) {
	this.myCollection = myCollection;
}
   
}