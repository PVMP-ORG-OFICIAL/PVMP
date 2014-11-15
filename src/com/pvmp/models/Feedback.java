package com.pvmp.models;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Feedback extends DAOAbstract 
{
	// !--- DATABASE ATTRIBUTES ---! \\
	public static final String COLUMN_USERNAME = "user_name";
	public static final String COLUMN_PROPOSITION = "id_prop";
	public static final String COLUMN_PARTY = "number_party";
	public static final String COLUMN_OPINION = "opinion"; //Maybe change
	
	// !--- OTHER ATTRIBUTES ---! \\
	private String opinion;
	private User user;
	private int target;
	
	public Feedback () {
		this.TABLE_NAME = "PropositionFeedback"; //might be temporary
		this.opinion = null;
		this.user = null;
		this.target = 0;
	}
	
	public Feedback (String _opinion, User _user, int _target) {
		this.TABLE_NAME = "PropositionFeedback"; //might be temporary
		this.opinion = _opinion;
		this.user = _user;
		this.target = _target;
	}
	public String getOpinion() 
	{
		return opinion;
	}

	public void setOpinion(String _opinion) 
	{
		this.opinion = _opinion;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User _user) {
		this.user = _user;
	}

	public int getTarget() 
	{
		return target;
	}

	public void setTarget(int _target) 
	{
		this.target = _target;
	}
	

	@Override
	public ContentValues generateContentValues() 
	{
		ContentValues contentValues = new ContentValues();
		/*(this.target instanceof Proposition)
		{
			Proposition proposition = new Proposition();
			proposition = (Proposition) this.target;
			
		}*/
		contentValues.put(COLUMN_PROPOSITION, this.target);
		contentValues.put(COLUMN_USERNAME, this.user.getUsername());
		contentValues.put(COLUMN_OPINION, this.opinion);
		
		return contentValues;
	}

	@Override
	public DAOAbstract contentValuesToModel(ContentValues _contentValues) 
	{
		Feedback feedback = new Feedback ();
		
		feedback.setOpinion(_contentValues.getAsString(COLUMN_OPINION));
		feedback.setTarget(_contentValues.getAsInteger(COLUMN_PROPOSITION));
		
		return feedback;
	}
	
}
