package backInfo;

import java.util.ArrayList;

public class Employes {

	private	String login;
	private	String password;
	private Boolean chef;
	private String procedure;
	private ArrayList<Integer> etapes;
	
	
	public Employes(String login, String password, Boolean chef, String procedure, ArrayList<Integer> etapes) {
		super();
		this.etapes = new ArrayList<Integer>();
		this.login = login;
		this.password = password;
		this.chef = chef;
		this.procedure = procedure;
		this.etapes = etapes;
	}
	public Employes() {
		super();
		this.etapes = new ArrayList<Integer>();
		// TODO Auto-generated constructor stub
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getChef() {
		return chef;
	}
	public void setChef(Boolean chef) {
		this.chef = chef;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public ArrayList<Integer> getEtapes() {
		return etapes;
	}
	public void setEtapes(ArrayList<Integer> etapes) {
		this.etapes = etapes;
	}
	@Override
	public String toString() {
		return "BackEmployes [login=" + login + ", password=" + password + ", chef=" + chef + ", procedure=" + procedure
				+ ", etapes=" + etapes + "]";
	}
}
