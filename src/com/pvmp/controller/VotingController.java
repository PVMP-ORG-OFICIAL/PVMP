package com.pvmp.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.model.Proposition;
import com.pvmp.model.Voting;

public class VotingController extends AbstractController
{

	public VotingController(Context _context) 
	{
		super(_context);
	}
	
	//This might be changed or removed
	public void getPropositionsVotings (ArrayList<Proposition> _propositions) 
	{
		if (_propositions == null) {
			throw new NullPointerException("Null value at PVMPmodel.getPropositionVoting");
		}
		
		Voting voting = new Voting();
		ArrayList<DAOAbstract> abstractVotings = new ArrayList<DAOAbstract>();
		ArrayList<Integer> propositionsIds = new ArrayList<Integer>();
		
		for (int i = 0; i < _propositions.size(); i++) { 
			propositionsIds.add(_propositions.get(i).getId());
		}
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(voting.TABLE_NAME);
		selectExpression.addColumn(Voting.COLUMN_CODE_SESSION);
		selectExpression.addColumn(Proposition.COLUMN_ID_PROP);
		
		Filter propositionIdFilter = new Filter("id_prop", "IN");
		propositionIdFilter.setValue(propositionsIds);
		
		selectExpression.setExpression(propositionIdFilter);
		selectExpression.setAuxiliarCondition("ORDER BY " + Proposition.COLUMN_ID_PROP + " ASC");
		
		abstractVotings = voting.selectDB(selectExpression, this.context); 
		
		if (abstractVotings.size() == 0)
		{
			throw new NoSuchElementException("No value at PVMPmodel.getPropositionVoting");
		}
		else 
		{
			for (int i = 0; i < abstractVotings.size(); i++) {
				Voting currentVoting = new Voting();
				currentVoting = (Voting) abstractVotings.get(i);
				
				_propositions.get(i).setVoting(currentVoting);
			}
		}
	}
}
