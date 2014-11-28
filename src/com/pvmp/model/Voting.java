package com.pvmp.model;

import java.util.ArrayList;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Voting extends DAOAbstract
{
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_ID_VOTING = "id_voting";
	public static final String COLUMN_CODE_SESSION = "cod_session";
	public static final String COLUMN_SUMMARY = "summary";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_ID_PROP = "id_prop";
	
	// !--- OTHER ATTRIBUTES ---! \\
	private Integer idVoting;
	private Integer codeSession;
	private Proposition proposition;
	private String summary;
	private String date;
	private ArrayList<Vote> votes;
	

	public Voting() {
		super();
		this.TABLE_NAME = "Voting";
		this.idVoting = null;
		this.codeSession = null;
		this.proposition = null;
		this.summary = null;
		this.date = null;
		this.votes = new ArrayList<Vote>();
	}
	
	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition _proposition) {
		this.proposition = _proposition;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String _summary) {
		this.summary = _summary;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String _date) {
		this.date = _date;
	}

	public ArrayList<Vote> getVotes() {
		return votes;
	}

	public void setVotes(ArrayList<Vote> _votes) {
		this.votes = _votes;
	}
	
	public Integer getIdVoting() {
		return idVoting;
	}

	public void setIdVoting(Integer idVoting) {
		this.idVoting = idVoting;
	}
	
	public Integer getCodeSession() {
		return this.codeSession;
	}

	public void setCodeSession(Integer codeSession) {
		this.codeSession = codeSession;
	}

	@Override
	public ContentValues generateContentValues() {
		return null;
	}

	@Override
	public Voting contentValuesToModel(ContentValues _contentValues) 
	{
		Voting voting = new Voting();
		Proposition proposition = new Proposition();
		
		voting.setIdVoting(_contentValues.getAsInteger(COLUMN_ID_VOTING));
		voting.setCodeSession(_contentValues.getAsInteger(COLUMN_CODE_SESSION));
		voting.setSummary(_contentValues.getAsString(COLUMN_SUMMARY));
		voting.setDate(_contentValues.getAsString(COLUMN_DATE));
		proposition.setId(_contentValues.getAsInteger(COLUMN_ID_PROP));
		voting.setProposition(proposition);
		
		return voting;
	}
}
