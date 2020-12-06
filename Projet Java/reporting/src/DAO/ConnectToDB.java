package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

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

	public static MongoCollection<Document> getEtapCollection() {
		return database.getCollection("Etape");
	}

	public static MongoCollection<Document> getprocedureCollection() {
		return database.getCollection("Procedure");
	}

}