package backInfo;

import java.util.ArrayList;

public class BackEmployes {

	private ArrayList<Employes> employes;

	public BackEmployes() {
		super();
		employes = new ArrayList<Employes>();
		// TODO Auto-generated constructor stub
	}

	public BackEmployes(ArrayList<Employes> employes) {
		super();
		this.employes = new ArrayList<Employes>();
		this.employes = new ArrayList<Employes>();
		this.employes = employes;
	}

	public ArrayList<Employes> getEmployes() {
		return employes;
	}

	public void setEmployes(ArrayList<Employes> employes) {
		this.employes = employes;
	}


	@Override
	public String toString() {
		return "BackEmployes [employes=" + employes + "]";
	}
	
	
}