package DAO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;


import Metier.Demande;
import Metier.Etat;

import Presentation.employe.TraiterCommandePage;
import Presentation.procedure.ServicePublic;

public class Controleur {
	ConnectToDB connectToDB;
	ArrayList<Demande> mesDemandes;
	int row;
	int etapindex;
	TraiterCommandePage traiterCommandePage;
	
	public Controleur() {
		super();
		this.mesDemandes = new ArrayList<Demande>();
		// TODO Auto-generated constructor stub
	}

	public void showTraiterCommande() {
		 this.connectToDB = new ConnectToDB();
		DemandeDAO dao = new DemandeDAO(this.connectToDB.getMyCollection());
		this.mesDemandes = dao.getMesDemande();
		TraiterCommandePage list = new TraiterCommandePage(this);
	}

	public void showDocument(int row) {
		if(isEncore())
		{
		this.row = row;
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < mesDemandes.get(row).getEtapes().get(0).getDocument().size(); i++) {
			mesDemandes.get(row).getEtapes().get(etapindex).setDate_debut(java.time.LocalTime.now().toString());
			String content = "error loading Document, check document path !!!!!";
			try {
				content = readFile(mesDemandes.get(row).getEtapes().get(etapindex).getDocument().get(i).getPath(), StandardCharsets.US_ASCII);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//System.out.println(mesDemandes.get(row).getEtapes().get(0).getDocument().get(i).getPath());
			map.put("Doc N" + i, content);

		}
	//	ServicePublic serv = new ServicePublic(map, "Etape N"+mesDemandes.get(row).getEtapes().get(0).getNumero(), mesDemandes.get(row).getJeton());
		ServicePublic serv = new ServicePublic(map, this);
		serv.setVisible(true);
		}
		else {
			this.mesDemandes.remove(row);			 
			 traiterCommandePage.resatartTable();
		}
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	public void valideEtape()
	{
		mesDemandes.get(row).getEtapes().get(etapindex).setDate_fin(java.time.LocalTime.now().toString());
		this.mesDemandes.get(row).getEtapes().get(etapindex).setEtat(Etat.ACCEPTE);
	}

	public ConnectToDB getConnectToDB() {
		return connectToDB;
	}

	public void setConnectToDB(ConnectToDB connectToDB) {
		this.connectToDB = connectToDB;
	}

	public ArrayList<Demande> getMesDemandes() {
		return mesDemandes;
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
		while (i < mesDemandes.get(row).getEtapes().size()) {		
			if(mesDemandes.get(row).getEtapes().get(i).getEtat() == Etat.ENCORE)
				etapindex = i;
			i++;
		}
		if(etapindex == -1)
			return (false);
		return (true);
	}

	public TraiterCommandePage getTraiterCommandePage() {
		return traiterCommandePage;
	}

	public void setTraiterCommandePage(TraiterCommandePage traiterCommandePage) {
		this.traiterCommandePage = traiterCommandePage;
	}

	 
}
