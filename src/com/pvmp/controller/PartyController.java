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
	
	public ArrayList<Party> getDeputiesParties (ArrayList<Deputy> _deputies)
	{
		if (_deputies == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getVoteDeputy");
		}

		Party party = new Party();
		ArrayList<DAOAbstract> abstractParties = new ArrayList<DAOAbstract>();
		ArrayList<Party> parties = new ArrayList<Party>();
		ArrayList<Integer> numbersParties = new ArrayList<Integer>();
		
		for (int i = 0; i < _deputies.size(); i++) {
			numbersParties.add(_deputies.get(i).getParty().getNumber());
		}
		
		SqlSelect selectExpression = new SqlSelect();
		selectExpression.addEntity(party.TABLE_NAME);
		
		Filter numberPartyFilter = new Filter("number_party", "IN");
		numberPartyFilter.setValue(numbersParties);
		
		selectExpression.setExpression(numberPartyFilter);
		
		abstractParties = party.selectDB(selectExpression, this.context); 
		
		if (abstractParties.size() == 0)
		{
			throw new NoSuchElementException("No value at PartyController.getDeputyParty()");
		}
		else 
		{
			for (int i = 0; i < abstractParties.size(); i++) {
				Party currentParty = new Party();
				currentParty = (Party) abstractParties.get(i);
				
				parties.add(currentParty);
			}
		}
		
		return parties;
	}
}
