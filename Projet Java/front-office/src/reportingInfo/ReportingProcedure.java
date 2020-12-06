package reportingInfo;

public class ReportingProcedure {
	private String id;
	private String procedure;
	private Integer dt;
	private Integer accepte;
	private Integer refuse;
	private Double ta;
	private Double md;

	public ReportingProcedure(String procedure, Integer dt, Integer accepte, Double md) {
		super();
		this.procedure = procedure;
		this.dt = dt;
		this.accepte = accepte;
		this.md = md;
		this.ta = (double)this.accepte * 100.0 / (double)this.dt;
		this.refuse = this.dt - this.accepte;
	}

	public String getprocedure() {
		return procedure;
	}

	public void setprocedure(String procedure) {
		this.procedure = procedure;
	}

	public Integer getDt() {
		return dt;
	}

	public void setDt(Integer dt) {
		this.dt = dt;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	@Override
	public String toString() {
		return "ReportingProcedure [id=" + id + ", procedure=" + procedure + ", dt=" + dt + ", accepte=" + accepte
				+ ", refuse=" + refuse + ", ta=" + ta + ", md=" + md + "]";
	}
	
}
