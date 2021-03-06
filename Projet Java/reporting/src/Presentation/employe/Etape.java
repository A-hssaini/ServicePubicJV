package Presentation.employe;

public class Etape {
	private String employe;
	private Integer et;
	private Integer accepte;
	private Integer refuse;
	private Double ta;
	private Double md;


	public Etape(String employe, Integer et, Integer accepte, Double md) {
		super();
		this.employe = employe;
		this.et = et;
		this.accepte = accepte;
		this.md = md;
		this.ta = (double)this.accepte * 100.0 / (double)this.et;
		this.refuse = this.et - this.accepte;
	}


	
	public  void initEtape() {
		this.ta = (double)this.accepte * 100.0 / (double)this.et;
		this.refuse = this.et - this.accepte;
		// TODO Auto-generated constructor stub
	}



	public String getEmploye() {
		return employe;
	}


	public void setEmploye(String employe) {
		this.employe = employe;
	}


	public Integer getEt() {
		return et;
	}


	public void setEt(Integer et) {
		this.et = et;
	}


	public Integer getAccepte() {
		return accepte;
	}


	public void setAccepte(Integer accepte) {
		this.accepte = accepte;
	}


	public Integer getRefuse() {
		return refuse;
	}


	public void setRefuse(Integer refuse) {
		this.refuse = refuse;
	}


	public Double getTa() {
		return ta;
	}


	public void setTa(Double ta) {
		this.ta = ta;
	}


	public Double getMd() {
		return md;
	}


	public void setMd(Double md) {
		this.md = md;
	}

	
}
