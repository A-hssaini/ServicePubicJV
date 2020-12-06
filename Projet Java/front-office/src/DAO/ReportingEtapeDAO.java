package DAO;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;

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

public class ReportingEtapeDAO {
	private ReportingEtape monEtape;
	private int x;

	public ReportingEtapeDAO() {
		super();
	}

	public void initMonEtape(String login) {
		this.x = 0;
		MongoCursor<Document> cursor = ConnectToDB.getEtapCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Document document = cursor.next();
			if (document.get("employe").equals(login)) {
				this.monEtape = gson.fromJson(document.toJson(), ReportingEtape.class);
				this.x = 1;
			}
		}
		if (this.x == 0)
			this.monEtape = new ReportingEtape(login, 0, 0, 0.0);
	}

	public void addAcceptedEtape(String login, Double duree, int accepte) {
		// TODO Auto-generated method stub
		if(this.x == 0)
		{
			Document document = new Document("employe", login).
					append("et", this.monEtape.getEt() + 1).
					append("accepte", this.monEtape.getAccepte() + accepte).
					append("md", (this.monEtape.getAccepte() * this.monEtape.getMd() + duree)
							/ (this.monEtape.getAccepte() + 1));							
			ConnectToDB.getEtapCollection().insertOne(document);
		}
		else {
			ConnectToDB.getEtapCollection().updateOne(eq("employe", login),
				combine(set("et", this.monEtape.getEt() + 1), set("accepte", this.monEtape.getAccepte() + accepte),
						set("md", (this.monEtape.getAccepte() * this.monEtape.getMd() + duree)
								/ (this.monEtape.getAccepte() + 1))));
		}
		
	}

}
