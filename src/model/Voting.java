package model;

import java.util.ArrayList;

public class Voting {
	
	private Integer codSessaoVot;
	private String resumoVot;
	private String dataVot;
	private ArrayList<Vote> vote;
	private ArrayList<Deputy> deputy ;
	
	
	public ArrayList<Deputy> getDeputy() {
		return deputy;
	}
	public void setDeputy(ArrayList<Deputy> deputy) {
		this.deputy = deputy;
	}
	public ArrayList<Vote> getVote() {
		return vote;
	}
	public void setVote(ArrayList<Vote> vote) {
		this.vote = vote;
	}
	public Integer getCodSessaoVot() {
		return codSessaoVot;
	}
	public void setCodSessaoVot(Integer codSessaoVot) {
		this.codSessaoVot = codSessaoVot;
	}
	public String getResumoVot() {
		return resumoVot;
	}
	public void setResumoVot(String resumoVot) {
		this.resumoVot = resumoVot;
	}
	public String getDataVot() {
		return dataVot;
	}
	public void setDataVot(String dataVot) {
		this.dataVot = dataVot;
	}

}
