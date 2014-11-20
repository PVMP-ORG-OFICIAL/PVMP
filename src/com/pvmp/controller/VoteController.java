package com.pvmp.controller;

import java.util.ArrayList;

import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.models.Vote;
import com.pvmp.models.Voting;

public class VoteController extends AbstractController
{
	public VoteController (Context _context) {
		super(_context);
	}
	
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
}
