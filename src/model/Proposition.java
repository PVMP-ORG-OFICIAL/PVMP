package model;


public class Proposition {
	
	private Integer idProp;
	private Integer anoProp;
	private String situacaoProp;
	private String ementaProp;
	private String autorProp;
	private String siglaProp;
	private String numeroProp;
	
	
	public String getSiglaProp() {
		return siglaProp;
	}
	public void setSiglaProp(String siglaProp) {
		this.siglaProp = siglaProp;
	}
	public String getNumeroProp() {
		return numeroProp;
	}
	public void setNumeroProp(String numeroProp) {
		this.numeroProp = numeroProp;
	}
	public Integer getIdProp() {
		return idProp;
	}
	public void setIdProp(Integer idProp) {
		this.idProp = idProp;
	}
	public Integer getAnoProp() {
		return anoProp;
	}
	public void setAnoProp(Integer anoProp) {
		this.anoProp = anoProp;
	}
	public String getEmentaProp() {
		return ementaProp;
	}
	public String getSituacaoProp() {
		return situacaoProp;
	}
	public void setSituacaoProp(String situacaoProp) {
		this.situacaoProp = situacaoProp;
	}
	public void setEmentaProp(String ementaProp) {
		this.ementaProp = ementaProp;
	}
	public String getAutorProp() {
		return autorProp;
	}
	public void setAutorProp(String autorProp) {
		this.autorProp = autorProp;
	}
}
