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
import Presentation.employe.Procedure;

public class ProcedureDAO {

	public ProcedureDAO() {
		super();
	}

	public ArrayList<Procedure> getMesProcedures() {

		ArrayList<Procedure> mesProcedures = new ArrayList<Procedure>();
		MongoCursor<Document> cursor = ConnectToDB.getprocedureCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Procedure nvProcedure = gson.fromJson(cursor.next().toJson(), Procedure.class);
			nvProcedure.initProcedure();
			mesProcedures.add(nvProcedure);
		}
		return mesProcedures;
	}

}
