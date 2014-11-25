package com.pvmp.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.models.Deputy;
import com.pvmp.models.Vote;

import android.content.Context;

public class DeputyController extends AbstractController
{
	public DeputyController(Context _context) {
		super(_context);
	}
	
	public ArrayList<Deputy> getDeputiesThatVotedOnSession (ArrayList<Vote> _votes) 
	{
		if (_votes == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getVoteDeputy");
		}
		
		Deputy deputy = new Deputy();
		ArrayList<DAOAbstract> abstractDeputies = new ArrayList<DAOAbstract>();
		ArrayList<Deputy> deputies = new ArrayList<Deputy>();
		ArrayList<Integer> deputiesRegistrations = new ArrayList<Integer>();
		
		for (int i = 0; i < _votes.size(); i++) {
			deputiesRegistrations.add(_votes.get(i).getDeputy().getIdRegistration());
		}
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(deputy.TABLE_NAME);
		selectExpression.addColumn(Deputy.COLUMN_NUMBER_PARTY);
		selectExpression.addColumn(Deputy.COLUMN_NAME);
		selectExpression.addColumn(Deputy.COLUMN_ID_REGISTRATION);
		
		Filter deputyIdFilter = new Filter("id_registration", "IN");
		deputyIdFilter.setValue(deputiesRegistrations);
		
		selectExpression.setExpression(deputyIdFilter);
		selectExpression.setAuxiliarCondition("ORDER BY " + Deputy.COLUMN_ID_REGISTRATION + " ASC");
		
		abstractDeputies = deputy.selectDB(selectExpression, this.context); 
		
		if (abstractDeputies.size() == 0)
		{
			throw new NoSuchElementException("No value at PVMPmodel.getDeputyVoteOnSession");
		}
		else 
		{
			for (int i = 0; i < abstractDeputies.size(); i++) {
				deputy = new Deputy();
				deputy = (Deputy) abstractDeputies.get(i);
				
				deputies.add(deputy);
			}
		}
		
		return deputies;
	}
}
