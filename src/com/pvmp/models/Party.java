package com.pvmp.models;

import java.util.ArrayList;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Party extends DAOAbstract
{
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_NUMBER_PARTY = "number_party";
	public static final String COLUMN_ACRONYM = "acronym";

	// !--- OTHER ATTRIBUTES ---! \\
	private Integer number;
	private String acronym;
	private ArrayList<Deputy> deputies;

	public Party() {
		super();
		this.TABLE_NAME = "Party";
		this.number = null;
		this.acronym = null;
		this.deputies = null;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer _number) {
		this.number = _number;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String _acronym) {
		this.acronym = _acronym;
	}
	
	public ArrayList<Deputy> getDeputies() {
		return this.deputies;
	}

	public void setDeputies(ArrayList<Deputy> _deputies) {
		this.deputies = _deputies;
	}

	@Override
	public ContentValues generateContentValues() {
		return null;
	}

	@Override
	public Party contentValuesToModel(ContentValues _contentValues) 
	{
		Party party = new Party();
		
		party.setNumber(_contentValues.getAsInteger(COLUMN_NUMBER_PARTY));
		party.setAcronym(_contentValues.getAsString(COLUMN_ACRONYM));
		
		return party;
	}

}
