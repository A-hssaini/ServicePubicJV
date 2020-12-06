package backInfo;

import java.util.ArrayList;

import Metier.Etape;
import Metier.Procedure;

public class BackProcedure {

	private ArrayList<Procedure> procedures;

	public BackProcedure() {
		super();
		this.procedures = new ArrayList<Procedure>();
		// TODO Auto-generated constructor stub
	}

	public BackProcedure(ArrayList<Procedure> procedures) {
		super();
		this.procedures = new ArrayList<Procedure>();
		this.procedures = procedures;
	}

	public ArrayList<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(ArrayList<Procedure> procedures) {
		this.procedures = procedures;
	}

}
