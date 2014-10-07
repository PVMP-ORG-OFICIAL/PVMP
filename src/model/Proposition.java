package model;


public class Proposition {
	
	private Integer idProp;
	private Integer yearProp;
	/* menu => ementa */
	private String menuProp;
	private String authorProp;
	private String accProp;
	private String numProp;
	private String situationProp;
	
	
	public String getSituationProp() {
		return situationProp;
	}
	public void setSituationProp(String situationProp) {
		this.situationProp = situationProp;
	}
	public Integer getIdProp() {
		return idProp;
	}
	public void setIdProp(Integer idProp) {
		this.idProp = idProp;
	}
	public Integer getYearProp() {
		return yearProp;
	}
	public void setYearProp(Integer yearProp) {
		this.yearProp = yearProp;
	}
	public String getMenuProp() {
		return menuProp;
	}
	public void setMenuProp(String menuProp) {
		this.menuProp = menuProp;
	}
	public String getAuthorProp() {
		return authorProp;
	}
	public void setAuthorProp(String authorProp) {
		this.authorProp = authorProp;
	}
	public String getAccProp() {
		return accProp;
	}
	public void setAccProp(String accProp) {
		this.accProp = accProp;
	}
	public String getNumProp() {
		return numProp;
	}
	public void setNumProp(String numProp) {
		this.numProp = numProp;
	}
}
