package model;

import java.util.ArrayList;

public class Voting {
	
	private Integer codSessionVot;
	/* resumo */
	private String summaryVot;
	private String dateVot;
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
	public Integer getCodSessionVot() {
		return codSessionVot;
	}
	public void setCodSessionVot(Integer codSessionVot) {
		this.codSessionVot = codSessionVot;
	}
	public String getSummaryVot() {
		return summaryVot;
	}
	public void setSummaryVot(String summaryVot) {
		this.summaryVot = summaryVot;
	}
	public String getDateVot() {
		return dateVot;
	}
	public void setDateVot(String dateVot) {
		this.dateVot = dateVot;
	}
}
