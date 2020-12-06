package Metier;

import java.util.ArrayList;

public class Procedure {
	private String nom;
	ArrayList<Etape> etapes;
	private ArrayList<String> descDocuments;
	private ArrayList<String> descDocumentsDescription;
	private ArrayList<Boolean> uploadedDocument;
	private Integer nbrEtapes;
	private ArrayList<ArrayList<Integer>> etapeDoc;

	public Procedure() {
		super();
		etapeDoc = new ArrayList<ArrayList<Integer>>();
		etapes = new ArrayList<Etape>();
		descDocuments = new ArrayList<>();
		uploadedDocument = new ArrayList<>();		
		descDocumentsDescription = new ArrayList<>();
		
		// TODO Auto-generated constructor stub
	}

	public Procedure(String nom) {
		etapeDoc = new ArrayList<ArrayList<Integer>>();

		this.nom = nom;
		descDocuments = new ArrayList<>();
		uploadedDocument = new ArrayList<>();
	}

	public Integer getNbrEtapes() {
		return nbrEtapes;
	}

	public void setNbrEtapes(Integer nbrEtapes) {
		this.nbrEtapes = nbrEtapes;
	}

	public ArrayList<ArrayList<Integer>> getEtapeDoc() {
		return etapeDoc;
	}

	public void setEtapeDoc(ArrayList<ArrayList<Integer>> etapeDoc) {
		this.etapeDoc = etapeDoc;
	}

	public void addDocument(String desc, boolean uploaded) {
		descDocuments.add(desc);
		uploadedDocument.add(uploaded);
	}
	
	public void addDocumentDescription(String desc) {
		descDocumentsDescription.add(desc);
	}

	public int getNbrDocuments() {
		return descDocuments.size();
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<String> getDescDocuments() {
		return descDocuments;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDescDocuments(ArrayList<String> descDocuments) {
		this.descDocuments = descDocuments;
	}

	public void setUploadedDocument(int index, Boolean bool) {
		this.uploadedDocument.set(index, bool);
	}
	
	public void setUploadedDocument2(ArrayList<Boolean> docMiseAjour) {
		this.uploadedDocument = docMiseAjour;
	}

	public Boolean getUploadedDocument(int index) {
		return this.uploadedDocument.get(index);
	//	return(false);
	}

	
	public ArrayList<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(ArrayList<Etape> etapes) {
		this.etapes = etapes;
	}

	
	public void implementEtapes(ArrayList<Integer> etapes) {
		this.nbrEtapes = etapes.size();
		
	}
	///////////////////////
	public void addUploadedDocument(boolean b) {
		// TODO Auto-generated method stub
		uploadedDocument.add(b); //////////
	}

	@Override
	public String toString() {
		return "Procedure [nom=" + nom + ", etapes=" + etapes + ", descDocuments=" + descDocuments
				+ ", descDocumentsDescription=" + descDocumentsDescription + ", uploadedDocument=" + uploadedDocument
				+ ", nbrEtapes=" + nbrEtapes + ", etapeDoc=" + etapeDoc + "]";
	}

	public ArrayList<String> getDescDocumentsDescription() {
		return descDocumentsDescription;
	}

	public void setDescDocumentsDescription(ArrayList<String> descDocumentsDescription) {
		this.descDocumentsDescription = descDocumentsDescription;
	}

	public void addEtape(ArrayList<Documentt> document,int numero) {
		Etape e = new Etape();
		e.setNumero(numero);
		e.setDocument(document);
		e.setEtat(Etat.ENCORE);////
		this.etapes.add(e);
	}

	public ArrayList<Boolean> getUploadedDocument() {
		return uploadedDocument;
	}
	
}
