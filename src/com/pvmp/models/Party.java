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
	
	public static String parseNumber (float _number) 
	{
		switch ((int) _number) 
		{
		case 10:
			return "PRB";
		case 11:
			return "PP";
		case 12:
			return "PDT";
		case 13: 
			return "PT";
		case 14:
			return "PTB";
		case 15:
			return "PMDB";
		case 16:
			return "PSTU";
		case 17:
			return "PSL";
		case 19:
			return "PTN";
		case 20:
			return "PSC";
		case 21:
			return "PCB";
		case 22:
			return "PR";
		case 23:
			return "PPS";
		case 25:
			return "DEM";
		case 27:
		    return "PSDC";
		case 28:
			return "PRTB";
		case 29:
			return "PCO";
		case 31: 
			return "PHS";
		case 33: 
			return "PMN"; 
		case 36:
			return "PTC";
		case 40:
			return "PSB";
		case 43:
			return "PV";
		case 44:
			return "PRP";
		case 45:
			return "PSDB";
		case 50:
			return "PSOL";
		case 54:
			return "PPL";
		case 55:
			return "PSD";
		case 65:
			return "PCdoB";
		case 70:
			return "PTdoB";
		case 77:
			return "SDD";
		case 90:
			return "PROS";
		}
		return null;
	}
}
