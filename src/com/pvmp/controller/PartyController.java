package com.pvmp.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.models.Deputy;
import com.pvmp.models.Party;

public class PartyController extends AbstractController 
{
	public PartyController(Context _context)
	{
		super(_context);
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
}
