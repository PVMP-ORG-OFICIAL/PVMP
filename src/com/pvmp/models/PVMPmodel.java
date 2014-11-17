/**
* @file PVMPModel.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.util.Util;

/**
* @class PVMPModel
* @brief 
*/
public class PVMPmodel implements ModelSubjectInterface
{
	private ArrayList<ListenerObserverInterface> observers;
	private Context context;

	/**
	* @param _context
	* @brief 
	*/
	public PVMPmodel(Context _context)
	{
		this.context = _context;
		this.observers = new ArrayList<ListenerObserverInterface>();
	}

	public void setContext(Context _context)
	{
		this.context = _context;
		return;
	}
	
	//This might be changed or removed
	public ArrayList<Vote> getVotingVotes (Voting _voting) 
	{
		if (_voting == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getVotingVotes");
		}
		Vote vote = new Vote();
		
		ArrayList<DAOAbstract> abstractVotes = new ArrayList<DAOAbstract>();
		ArrayList<Vote> votes = new ArrayList<Vote>();
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(vote.TABLE_NAME);
		selectExpression.addColumn(Vote.COLUMN_VOTE_RESULT);
		selectExpression.addColumn(Vote.COLUMN_ID_REGISTRATION);
		
		Filter votingCodeFilter = new Filter("code_session", "=");
		votingCodeFilter.setValue(_voting.getCodeSession());
		
		selectExpression.setExpression(votingCodeFilter);
		
		abstractVotes = vote.selectDB(selectExpression, this.context);
		
		for (int i = 0; i < abstractVotes.size(); i++)
		{
			vote = new Vote();
			vote = (Vote) abstractVotes.get(i);
			vote.setVoting(_voting);
			votes.add(vote);
		}
		_voting.setVotes(votes);
		
		return votes;
	}
	
	public Deputy getDeputyVoteOnSession (Vote _vote) 
	{
		if (_vote == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getVoteDeputy");
		}
		
		Deputy deputy = _vote.getDeputy();
		ArrayList<DAOAbstract> deputies = new ArrayList<DAOAbstract>();
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(deputy.TABLE_NAME);
		selectExpression.addColumn(Deputy.COLUMN_NUMBER_PARTY);
		selectExpression.addColumn(Deputy.COLUMN_NAME);
		
		Util.debug("Deputy ID: " + deputy.getIdRegistration());
		
		Filter deputyIdFilter = new Filter("id_registration", "=");
		deputyIdFilter.setValue(deputy.getIdRegistration());
		
		selectExpression.setExpression(deputyIdFilter);
		
		deputies = deputy.selectDB(selectExpression, this.context); 
		
		if (deputies.size() == 1)
		{
			deputy = (Deputy) deputies.get(0);
		}
		else 
		{
			return null;
			//throw new NoSuchElementException("No value at PVMPmodel.getDeputyVoteOnSession");
		}
		
		return deputy;
	}
	
	public String getPartyAcronym (float partyNumber)
	{
		Party party = new Party();
		
		String acronym = null;
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(party.TABLE_NAME);
		selectExpression.addColumn("acronym");
		
		Filter numberPartyFilter = new Filter("number_party", "=");
		numberPartyFilter.setValue(partyNumber);
		
		selectExpression.setExpression(numberPartyFilter);
		
		ArrayList<DAOAbstract> parties = party.selectDB(selectExpression, this.context);
		
		if (parties.size() == 1)
		{
			party = (Party) parties.get(0);
		}
		else 
		{
			throw new NoSuchElementException("No value at PVMPmodel.getDeputyParty");
		}
		
		acronym = party.getAcronym();
		
		return acronym;
	}
	
	public Party getDeputyParty (Deputy _deputy)
	{
		if (_deputy == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getVoteDeputy");
		}

		Party party = _deputy.getParty();
		ArrayList<DAOAbstract> parties = new ArrayList<DAOAbstract>();
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(party.TABLE_NAME);
		
		Filter numberPartyFilter = new Filter("number_party", "=");
		numberPartyFilter.setValue(party.getNumber());
		
		selectExpression.setExpression(numberPartyFilter);
		
		parties = party.selectDB(selectExpression, this.context); 
		
		if (parties.size() == 1)
		{
			party = (Party) parties.get(0);
		}
		else 
		{
			throw new NoSuchElementException("No value at PVMPmodel.getDeputyParty");
		}
		
		return party;
	}

	/**
	* @param _observer
	* @brief
	*/
	public void registerObserver(ListenerObserverInterface _observer)
	{
		this.observers.add(_observer);

		return;
	}

	/**
	* @param _observer
	* @brief
	*/
	public void removeObserver(ListenerObserverInterface _observer)
	{
		 return;
	}
}
