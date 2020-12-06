package DAO;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;
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
import com.mongodb.util.JSON;

import Metier.Demande;
import Metier.Documentt;
import Metier.Etape;
import Metier.Etat;
import Metier.Procedure;
import backInfo.Employes;

public class DemandeDAO {
	private ArrayList<Demande> mesDemande;

	public DemandeDAO() {
		super();
		mesDemande = new ArrayList<Demande>();
		ConnectToDB.connexion();
	}

	public void getAlllDemande() {
		// DBCursor cursor = myCollection.find();
		MongoCursor<Document> cursor = ConnectToDB.getMyCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Demande nvDemande = gson.fromJson(cursor.next().toJson(), Demande.class);
			mesDemande.add(nvDemande);
		}
	}

	public ArrayList<Demande> getCetoyenDemande(String cin) { //

		ArrayList<Demande> employeDemandes = new ArrayList<Demande>();
		MongoCursor<Document> cursor = ConnectToDB.getMyCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Demande nvDemande = gson.fromJson(cursor.next().toJson(), Demande.class);
			// nvDemande.setJeton(theObj.get("_id").toString());
			if (nvDemande.getCin().equals(cin) && !nvDemande.getJeton().equals("new"))
				employeDemandes.add(nvDemande);
		}
		return (employeDemandes);
	}

	public ArrayList<Demande> getMesDemande() {
		getAlllDemande();
		return mesDemande;
	}

	public void setMesDemande(ArrayList<Demande> mesDemande) {
		this.mesDemande = mesDemande;
	}

	public void insertNewDemande(Demande demande) {

		Document document = new Document("jeton", demande.getJeton()).append("cin", demande.getCin())
				.append("etat", demande.getEtat().toString())
				.append("procedure", new Document("nom", demande.getProcedure().getNom()))
				.append("DocsPath", demande.getAllDocsPath()).append("numEtape", "0")
				.append("date_debut", demande.getDate_debut());
		ConnectToDB.getMyCollection().insertOne(document);
	}

	public ArrayList<Demande> getNewDemande(ArrayList<String> mesProce) {

		ArrayList<Demande> newDemandes = new ArrayList<Demande>();
		MongoCursor<Document> cursor = ConnectToDB.getMyCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Document document = cursor.next();
			Demande nvDemande = gson.fromJson(document.toJson(), Demande.class);
			nvDemande.setJeton(document.get("_id").toString());

			if (mesProce.contains(nvDemande.getProcedure().getNom()) && (nvDemande.getNumEtape() == 0)) {
				nvDemande.setId(document.get("_id").toString());
				newDemandes.add(nvDemande);
			}
		}
		return (newDemandes);
	}

	public ArrayList<Demande> getEmployeDemande(ArrayList<String> mesProce) {

		ArrayList<Demande> newDemandes = new ArrayList<Demande>();
		MongoCursor<Document> cursor = ConnectToDB.getMyCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();

			Document document = cursor.next();
			Demande nvDemande = gson.fromJson(document.toJson(), Demande.class);

			if (mesProce.contains(nvDemande.getProcedure().getNom()) && (nvDemande.getNumEtape() > 0)
					&& (nvDemande.getEtat() != Etat.REJETE)) {
				nvDemande.setId(document.get("_id").toString());
				newDemandes.add(nvDemande);
			}

		}
		return (newDemandes);
	}

	public void test(String id) {
		// TODO Auto-generated method stub
		/// this.myCollection.updateOne(eq("_id", new ObjectId(id)), set("jeton",
		/// "aaaaa"));
	}

	public void initTraiter(String id) {

		ConnectToDB.getMyCollection().updateOne(eq("_id", new ObjectId(id)),
				combine(set("numEtape", "1"), set("jeton", id)));
	}

	public void nextEtape(String id, int num, Etat etat) {
		// TODO Auto-generated method stub
		ConnectToDB.getMyCollection().updateOne(eq("_id", new ObjectId(id)),
				combine(set("numEtape", num), set("etat", etat.toString())));
	}

	public void setDemandeDate(Demande demande) {
		// TODO Auto-generated method stub
		if (this.getEtapeNum(demande.getId()) == demande.getNumEtape()) {
			ConnectToDB.getMyCollection().updateOne(eq("_id", new ObjectId(demande.getId())),
					combine(set("date_debut", demande.getDate_debut())));
		}
	}

	public int getEtapeNum(String id) {
		MongoCursor<Document> cursor = ConnectToDB.getMyCollection().find().iterator();
		while (cursor.hasNext()) {
			Gson gson = new Gson();
			Demande nvDemande = gson.fromJson(cursor.next().toJson(), Demande.class);
			if (nvDemande.getJeton().equals(id)) {
				return (nvDemande.getNumEtape());
			}
		}
		return (0);
	}

	public void removeDemande(String id) {
		// TODO Auto-generated method stub
		ConnectToDB.getMyCollection().deleteOne(new Document("_id", new ObjectId(id)));
	}

	public void miseAjour(Demande demande) {
//		ConnectToDB.getMyCollection().updateOne(eq("_id", new ObjectId(demande.getId())),
//				combine(set("procedure", combine(set("descDocuments",demande.getProcedure().getDescDocuments()),
//						set("uploadedDocument",demande.getProcedure().getUploadedDocument() )))));

		ConnectToDB.getMyCollection().updateOne(eq("_id", new ObjectId(demande.getId())),
				combine(set("procedure",
						new Document("nom", demande.getProcedure().getNom())
								.append("descDocuments", demande.getProcedure().getDescDocuments())
								.append("uploadedDocument", demande.getProcedure().getUploadedDocument()))));
	}
}
