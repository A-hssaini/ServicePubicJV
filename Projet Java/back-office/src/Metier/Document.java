package Metier;

public class Document {
	int ID;
	String Nom;
	String Description;

	public Document(int ID, String Nom, String Description)
	{
		this.ID = ID;
		this.Nom = Nom;
		this.Description = Description;
	}

	public Document() {
	}

	public Document(Document doc)
	{
		this.ID = doc.getID();
		this.Nom = doc.getNom();
		this.Description = doc.getDescription();
	}

	public String getNom() {
		return Nom;
	}

	public String getDescription() {
		return Description;
	}

	public int getID() {
		return ID;
	}
}