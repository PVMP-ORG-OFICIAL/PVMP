package com.pvmp.models;

import java.util.List;

public class Deputy {
	private String name;
	private String party;
	List <Vote> votes;
	
	public Deputy () {
		this.name = null;
		this.party = null;
		this.votes = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
}
