package com.pvmp.controller;

import java.util.ArrayList;

import android.content.Context;

import com.pvmp.dao.Criteria;
import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Expression;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.model.Proposition;

public class PropositionController extends AbstractController
{

	public PropositionController(Context _context) 
	{
		super(_context);
	}
	
	/**
	 * @param _columnName
	 * @param _value
	 * @brief Return an ArrayList of propositions based on a column of the Database and
	 * 		  its respective "finder" (a.k.a _value).
	 * */
	public ArrayList<Proposition> getPropositions(String _columnName, String _value)
	{
		//_value might be null
		if (_columnName == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getPropositions()");
		}
		
		SqlSelect selectExpression = new SqlSelect();
		ArrayList<DAOAbstract> abstractPropositions = new ArrayList<DAOAbstract>();
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		Criteria filterConcatenator = new Criteria();
		Proposition proposition = new Proposition();
		
		selectExpression.addEntity("Proposition");
		selectExpression.addColumn(Proposition.COLUMN_MENU);
		selectExpression.addColumn(Proposition.COLUMN_ID_PROP);
		selectExpression.addColumn(Proposition.COLUMN_AUTHOR);
		
		if (_value != null) 
		{
			Filter propositionsFilterFullName = new Filter(_columnName, "=");
			propositionsFilterFullName.setValue(_value);
			
			//Find propositions that have more than one category (based on one category)
			Filter propositionsFilterLikeName = new Filter(_columnName, "LIKE");
			propositionsFilterLikeName.setValue("%"+_value+"%");
		
			filterConcatenator.add(propositionsFilterFullName, Expression.OR_OPERATOR);
			filterConcatenator.add(propositionsFilterLikeName, Expression.OR_OPERATOR);
			
			selectExpression.setExpression(filterConcatenator);
		}

		abstractPropositions = proposition.selectDB(selectExpression, this.context);

		int i = 0;
		while(i < abstractPropositions.size()){
			propositions.add((Proposition) abstractPropositions.get(i));
			i++;
		}

		return propositions;
	}
}
