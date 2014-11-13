package com.pvmp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class PropositionWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Proposition> propositions;
	
	public PropositionWrapper(ArrayList<Proposition> data){
		this.propositions = data;
	}
	
	public ArrayList<Proposition> getPropositions(){
		return this.propositions;
	}

}
