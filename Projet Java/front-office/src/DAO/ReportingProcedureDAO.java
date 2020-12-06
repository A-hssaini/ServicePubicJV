package DAO;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import Metier.Demande;
import reportingInfo.ReportingEtape;
import reportingInfo.ReportingProcedure;

public class ReportingProcedureDAO {
	private ReportingProcedure monProcedure;
	private int x;

	public ReportingProcedureDAO() {
		super();
	}

	public void initMonProcedure(String procedure) {
		MongoCursor<Document> cursor = ConnectToDB.getprocedureCollection().find().iterator();
		this.x = 0;
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Document document = cursor.next();
			if (document.get("procedure").equals(procedure)) {
				this.x = 1;
				this.monProcedure = gson.fromJson(document.toJson(), ReportingProcedure.class);
			}
		}
		if (this.x == 0)
			this.monProcedure = new ReportingProcedure(procedure, 0, 0, 0.0);
	}

	public void addAcceptedProcedure(String procedure, Double duree, int accepte) {
		// TODO Auto-generated method stub

		if (this.x == 0) {
			Document document = new Document("procedure", procedure).append("dt", this.monProcedure.getDt() + 1)
					.append("accepte", this.monProcedure.getAccepte() + accepte)
					.append("md", (this.monProcedure.getAccepte() * this.monProcedure.getMd() + duree)
							/ (this.monProcedure.getAccepte() + 1));
			ConnectToDB.getprocedureCollection().insertOne(document);
		} else {
			ConnectToDB.getprocedureCollection().updateOne(eq("procedure", procedure),
					combine(set("dt", this.monProcedure.getDt() + 1),
							set("accepte", this.monProcedure.getAccepte() + accepte),
							set("md", (this.monProcedure.getAccepte() * this.monProcedure.getMd() + duree)
									/ (this.monProcedure.getAccepte() + 1))));
		}

	}
}
