package com.pvmp.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.models.Proposition;
import com.pvmp.models.Voting;

public class VotingController extends AbstractController
{

	public VotingController(Context _context) 
	{
		super(_context);
	}
	
	//This might be changed or removed
	public Voting getPropositionVoting (Proposition _proposition) 
	{
		if (_proposition == null) {
			throw new NullPointerException("Null value at PVMPmodel.getPropositionVoting");
		}
		
		Voting voting = new Voting();
		ArrayList<DAOAbstract> abstractVotings = new ArrayList<DAOAbstract>();
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(voting.TABLE_NAME);
		selectExpression.addColumn(Voting.COLUMN_CODE_SESSION);
		
		Filter propositionIdFilter = new Filter("id_prop", "=");
		propositionIdFilter.setValue(_proposition.getId());
		
		selectExpression.setExpression(propositionIdFilter);
		
		abstractVotings = voting.selectDB(selectExpression, this.context); 
		
		if (abstractVotings.size() == 1)
		{
			voting = (Voting) abstractVotings.get(0);
			voting.setProposition(_proposition);
		}
		else 
		{
			throw new NoSuchElementException("No value at PVMPmodel.getPropositionVoting");
		}
		
		return voting;
	}
}
