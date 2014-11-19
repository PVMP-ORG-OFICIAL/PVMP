/**
* @file PVMPModel.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;

import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;

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
		selectExpression.setAuxiliarCondition("ORDER BY " + Vote.COLUMN_ID_REGISTRATION + " ASC");
		
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
