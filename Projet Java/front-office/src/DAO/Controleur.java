package DAO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bson.types.ObjectId;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import Metier.Demande;
import Metier.Documentt;
import Metier.Etat;
import Metier.Procedure;
import Presentation.Chef2.ChefPage;
import Presentation.employe2.EmployePage;
import Presentation.procedure.ServicePublic;
import backInfo.BackEmployes;
import backInfo.BackProcedure;

public class Controleur {
	ArrayList<Demande> mesDemandes;
	int row;
	int etapindex;
	EmployePage traiterCommandePage;
	private String login;
	private BackEmployes backEmployes;
	private boolean isChef;
	DemandeDAO daoDemande;
	private ArrayList<String> mesProcStrings = new ArrayList<String>();
	private ReportingEtapeDAO reportingEtapeDAO;
	private ReportingProcedureDAO reportingProcedureDAO;
	private ChefPage chefPage;

	public Controleur() {
		super();
		this.mesDemandes = new ArrayList<Demande>();
		this.daoDemande = new DemandeDAO();
		this.reportingEtapeDAO = new ReportingEtapeDAO();
		this.reportingProcedureDAO = new ReportingProcedureDAO();
	}

	public void showTraiterCommande(String login) {
		this.mesDemandes = getEmployeDemande(login);
		// this.mesDemandes = this.daoDemande.getMesDemande();
		EmployePage list = new EmployePage(this);
		list.setVisible(true);

//		Runnable helloRunnable = new Runnable() {
//		    public void run() {
//		        System.out.println("Hello world");
//		    }
//		};
//
//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//		executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
	}

	public void showDocument(int row) {
		mesDemandes.get(row).setDate_debut(java.time.LocalTime.now().toString());
		this.daoDemande.setDemandeDate(mesDemandes.get(row));
		//	System.out.println(this.mesDemandes.get(row).getProcedure().getDescDocuments());
		//	System.out.println(this.mesDemandes.get(row).getProcedure().getUploadedDocument(0));
	//	if (isEncore()) {

			this.row = row;
			mesDemandes.get(row).setDate_debut(java.time.LocalTime.now().toString());
			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < mesDemandes.get(row).getProcedure().getDescDocuments().size(); i++) {				
				map.put(mesDemandes.get(row).getProcedure().getDescDocuments().get(i),
						mesDemandes.get(row).getProcedure().getDescDocumentsDescription().get(i));

			}
			ServicePublic serv = new ServicePublic(map, this);
			serv.setVisible(true);
	//	} //else {
			// this.mesDemandes.remove(row);
		//	if (isChef)
			//	chefPage.restartPage();
		//	else
			//	traiterCommandePage.resatartTable();
		//}
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public int valideEtape(int choix) {
		if (getEtapeNum(this.mesDemandes.get(row).getId()) == this.mesDemandes.get(row).getNumEtape()) {
			mesDemandes.get(row).setDate_fin(java.time.LocalTime.now().toString());
			long duree = Duration.between(LocalTime.parse(mesDemandes.get(row).getDate_debut()),
					LocalTime.parse(mesDemandes.get(row).getDate_fin())).toMinutes() * 60;

			if (choix == 1) /// accepter
			{
				this.addAcceptedEtape(this.login, duree, 1);
				this.nextEtape(this.row, mesDemandes.get(row).getProcedure().getNom(), duree);
				if (isChef)
					chefPage.restartPage();
				else
					traiterCommandePage.resatartTable();
			} else if (choix == 2) { // mise a jour
			
				this.miseAjourEtape(row);
				this.docMiseAjour(this.mesDemandes.get(row));
				if (isChef)
					chefPage.restartPage();
				else
					traiterCommandePage.resatartTable();
			} else if (choix == 3) { // refuser
				this.addAcceptedEtape(this.login, duree, 0);
				this.previousEtape(this.row);
				if (isChef)
					chefPage.restartPage();
				else
					traiterCommandePage.resatartTable();
			} else if (choix == 4) { //// rejeter
				this.addAcceptedEtape(this.login, duree, 0);
				this.rejeterEtape(this.row, mesDemandes.get(row).getProcedure().getNom(), duree);
				if (isChef)
					chefPage.restartPage();
				else
					traiterCommandePage.resatartTable();
			}
			return (1);
		} else {
			return (0);

		}
	}

	private void docMiseAjour(Demande demande) {
		// TODO Auto-generated method stub
		this.daoDemande.miseAjour(demande);
	}

	public ArrayList<Demande> getMesDemandes() {
		this.mesDemandes = getEmployeDemande(login);
		return mesDemandes;
	}

	public ChefPage getChefPage() {
		return chefPage;
	}

	public void setChefPage(ChefPage chefPage) {
		this.chefPage = chefPage;
	}

	public void setMesDemandes(ArrayList<Demande> mesDemandes) {
		this.mesDemandes = mesDemandes;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isEncore() {
		int i = 0;
		etapindex = -1;
		while (i < mesDemandes.get(row).getProcedure().getEtapes().size()) {
			if (mesDemandes.get(row).getProcedure().getEtapes().get(i).getEtat() == Etat.ENCORE)
				etapindex = i;
			i++;
		}
		if (etapindex == -1)
			return (false);
		return (true);
	}

	public EmployePage getTraiterCommandePage() {
		return traiterCommandePage;
	}

	public void setTraiterCommandePage(EmployePage traiterCommandePage) {
		this.traiterCommandePage = traiterCommandePage;
	}

	public boolean isChef() {
		return isChef;
	}

	public boolean verifierConnexion(String login, String password) throws JsonIOException, IOException {

		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		String json = "{}";
		try {
			json = readFile("C:/tmp/EmployeJsonfile.json", StandardCharsets.US_ASCII);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JsonElement jsonElement = parser.parse(json);
		BackEmployes backEmployes = gson.fromJson(jsonElement, BackEmployes.class);
		for (int i = 0; i < backEmployes.getEmployes().size(); i++) {
			if (login.equals(backEmployes.getEmployes().get(i).getLogin())
					&& password.equals(backEmployes.getEmployes().get(i).getPassword())) {
				this.isChef = backEmployes.getEmployes().get(i).getChef();
				this.backEmployes = backEmployes; /////
				this.login = login;
				return (true);
			}
		}
		return false;
	}

	public ArrayList<Procedure> getAllProcedure() {

		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		String json = "{}";
		try {
			json = readFile("C:/tmp/ProcedureJsonfile.json", StandardCharsets.US_ASCII);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JsonElement jsonElement = parser.parse(json);
		BackProcedure backProcedure = new BackProcedure();
		backProcedure = gson.fromJson(jsonElement, BackProcedure.class);

		for (int i = 0; i < backProcedure.getProcedures().size(); i++) {
			for (int j = 0; j < backProcedure.getProcedures().get(i).getNbrDocuments(); j++) {
				backProcedure.getProcedures().get(i).addUploadedDocument(false);
			}
		}
		return backProcedure.getProcedures();
	}

	public ArrayList<Demande> getEmployeDemande(String login) { // et chef
		// return (this.daoDemande.getEmployesDemande(cin));
		this.mesProcStrings.clear();
		for (int i = 0; i < this.backEmployes.getEmployes().size(); i++) {
			if (this.backEmployes.getEmployes().get(i).getLogin().equals(login)) {
				mesProcStrings.add(this.backEmployes.getEmployes().get(i).getProcedure());
			}
		}

		ArrayList<Demande> empDemandes = this.daoDemande.getEmployeDemande(mesProcStrings);
		// implementer(empDemandes);

		for (int i = 0; i < empDemandes.size(); i++) {
			ArrayList<Integer> etape = new ArrayList<Integer>();
			for (int j = 0; j < this.backEmployes.getEmployes().size(); j++) {
				if (empDemandes.get(i).getProcedure().getNom()
						.equals(this.backEmployes.getEmployes().get(j).getProcedure())
						&& this.backEmployes.getEmployes().get(j).getLogin().equals(login)) {
					etape = this.backEmployes.getEmployes().get(j).getEtapes();//////////////

				}
			}

			/////// empDemandes.get(i).getNumEtape()
			if (etape.contains(empDemandes.get(i).getNumEtape())) {
				ArrayList<Procedure> backProcedures = getAllProcedure();
				for (int k = 0; k < backProcedures.size(); k++) {
					if (empDemandes.get(i).getProcedure().getNom().equals(backProcedures.get(k).getNom())) {

						empDemandes.get(i).getProcedure().setNbrEtapes(backProcedures.get(k).getNbrEtapes());

						ArrayList<Documentt> documentts = new ArrayList<Documentt>();
						
						
						
						
						empDemandes.get(i).getProcedure().setDescDocuments(new ArrayList<String>());
						empDemandes.get(i).getProcedure().setUploadedDocument2(new ArrayList<Boolean>());;
						
						
						
						
						for (int w = 0; w < backProcedures.get(k).getEtapeDoc()
								.get(empDemandes.get(i).getNumEtape() - 1).size(); w++) {
							empDemandes.get(i).getProcedure()
									.addDocument(backProcedures.get(k).getDescDocuments().get(w), true);
							empDemandes.get(i).getProcedure()
									.addDocumentDescription(backProcedures.get(k).getDescDocumentsDescription().get(w));

						}
						empDemandes.get(i).getProcedure().addEtape(documentts, empDemandes.get(i).getNumEtape());
					}
				}
			} else {
				empDemandes.remove(i);
				i--;
			}
		}
		return (empDemandes);
	}

	public ArrayList<Demande> getNvDemandes() {
		this.mesProcStrings.clear();
		this.mesProcStrings = new ArrayList<String>();
		for (int i = 0; i < this.backEmployes.getEmployes().size(); i++) {
			if (this.backEmployes.getEmployes().get(i).getLogin().equals(this.login)) {
				mesProcStrings.add(this.backEmployes.getEmployes().get(i).getProcedure());
			}
		}

		return (this.daoDemande.getNewDemande(mesProcStrings));

	}

	public void insertNewDemande(Demande demande) {
		this.daoDemande.insertNewDemande(demande);
	}

	public ArrayList<Demande> getCetoyenDemande(String cin) {
		// TODO Auto-generated method stub
		ArrayList<Demande> listDemande = new ArrayList<Demande>();
		listDemande = this.daoDemande.getCetoyenDemande(cin);

		for (int i = 0; i < listDemande.size(); i++) {
			for (int j = 0; j < listDemande.get(i).getProcedure().getNbrDocuments(); j++) {
				listDemande.get(i).getProcedure().addUploadedDocument(false);
			}

		}
		return (listDemande);
	}

	public void test(int row) {
		// TODO Auto-generated method stub
		this.daoDemande.test(this.mesDemandes.get(row).getId());
	}

	public void initTraiter(String id) {
		// TODO Auto-generated method stub
		this.daoDemande.initTraiter(id);
	}

	public void nextEtape(int row2, String procedure, double duree) {
		// TODO Auto-generated method stub
		Etat etat = Etat.ENCORE;
		if (this.mesDemandes.get(row2).getNumEtape() == this.mesDemandes.get(row2).getProcedure().getNbrEtapes()) {
			addAcceptedProcedure(procedure, duree, 1);
			etat = Etat.ACCEPTE;
		}
		this.daoDemande.nextEtape(this.mesDemandes.get(row).getId(), this.mesDemandes.get(row2).getNumEtape() + 1,
				etat);
	}

	private void miseAjourEtape(int row2) {

		this.daoDemande.nextEtape(this.mesDemandes.get(row).getId(), this.mesDemandes.get(row2).getNumEtape(),
				Etat.MISEAJOUR);
	}

	private void previousEtape(int row2) {
		// TODO Auto-generated method stub
		Etat etat = Etat.ENCORE;
		if (this.mesDemandes.get(row2).getNumEtape() == 1)
			etat = Etat.REJETE;
		this.daoDemande.nextEtape(this.mesDemandes.get(row).getId(), this.mesDemandes.get(row2).getNumEtape() - 1,
				etat);
	}

	private void rejeterEtape(int row2, String procedure, double duree) {
		// TODO Auto-generated method stub
		addAcceptedProcedure(procedure, duree, 0);
		this.daoDemande.nextEtape(this.mesDemandes.get(row).getId(), this.mesDemandes.get(row2).getNumEtape(),
				Etat.REJETE);
	}

	private void addAcceptedEtape(String login, double duree, int accepte) {
		// TODO Auto-generated method stub
		this.reportingEtapeDAO.initMonEtape(login);
		this.reportingEtapeDAO.addAcceptedEtape(login, duree, accepte);
	}

	private void addAcceptedProcedure(String procedure, double duree, int accepte) {
		// TODO Auto-generated method stub
		this.reportingProcedureDAO.initMonProcedure(procedure);
		this.reportingProcedureDAO.addAcceptedProcedure(procedure, duree, accepte);
	}

	private Integer getEtapeNum(String id) {
		// TODO Auto-generated method stub
		return this.daoDemande.getEtapeNum(id);
	}

	public void removeDemande(String id) {
		// TODO Auto-generated method stub
		this.daoDemande.removeDemande(id);
	}

	public void updateDocument(ArrayList<Boolean> docMiseAjoure) {
		// TODO Auto-generated method stub
		this.mesDemandes.get(row).getProcedure().setUploadedDocument2(docMiseAjoure);
	}

}
