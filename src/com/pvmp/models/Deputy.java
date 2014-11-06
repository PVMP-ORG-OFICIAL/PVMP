package com.pvmp.models;

import java.util.ArrayList;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Deputy extends DAOAbstract
{
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_ID_REGISTRATION = "id_registration";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_FEDERATIVE_UNIT = "uf";
	public static final String COLUMN_NUMBER_PARTY = "number_party";
	
	// !--- OTHER ATTRIBUTES ---! \\
	private String name;
	private String federativeUnit;
	private Party party;
	private ArrayList <Vote> votes;
	
	public Deputy () {
		this.name = null;
		this.federativeUnit = null;
		this.party = null;
		this.votes = new ArrayList<Vote>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFederativeUnit() {
		return federativeUnit;
	}

	public void setFederativeUnit(String _federativeUnit) {
		this.federativeUnit = _federativeUnit;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party _party) {
		this.party = _party;
	}

	public ArrayList<Vote> getVotes() {
		return votes;
	}

	public void setVotes(ArrayList<Vote> _votes) {
		this.votes = _votes;
	}

	@Override
	public ContentValues generateContentValues() {
		return null;
	}

	@Override
	public DAOAbstract contentValuesToModel(ContentValues _contentValues) {
		return null;
	}
}
