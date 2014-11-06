package com.pvmp.models;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Vote extends DAOAbstract
{
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_VOTE_RESULT = "vote_result";
	public static final String COLUMN_CODE_SESSION = "code_session";
	public static final String COLUMN_ID_REGISTRATION = "id_registration";
	
	// !--- OTHER ATTRIBUTES ---! \\
	private Boolean result;
	private Deputy deputy;
	private Voting voting;
	
	public Vote () {
		super();
		this.TABLE_NAME = "Vote";
		this.result = null;
		this.deputy = null;
		this.voting = null;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean _result) {
		this.result = _result;
	}

	public Deputy getDeputy() {
		return deputy;
	}

	public void setDeputy(Deputy deputy) {
		this.deputy = deputy;
	}

	public Voting getVoting() {
		return this.voting;
	}

	public void setProposition(Voting _voting) {
		this.voting = _voting;
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
