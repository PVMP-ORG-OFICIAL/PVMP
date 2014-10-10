package com.pvmp.models;

import java.util.Date;
import java.util.List;

public class Proposition {
	
	private String name;
	private String proposer;
	private String situation;
	private Date propositionDate;
	private String summary;
	private List <Vote> votes;
	
	public Proposition (){
		this.name=null;
		this.proposer=null;
		this.situation=null;
		this.propositionDate=null;
		this.summary=null;
		this.votes=null;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Date getPropositionDate() {
		return propositionDate;
	}

	public void setPropositionDate(Date propositionDate) {
		this.propositionDate = propositionDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	
	

}
