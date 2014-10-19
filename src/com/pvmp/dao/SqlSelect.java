/**
 * @file SqlSelect.java
 * @brief
 * */
package com.pvmp.dao;

import java.util.ArrayList;

/**
 * @class SqlSelect
 * @brief Class responsible for organizing the Select SQL instructions that will
 * 		  be made along the application.
 * */
public final class SqlSelect extends SqlInstructionQuery 
{
	public ArrayList<String> columns;
	
	public SqlSelect () 
	{
		this.columns = new ArrayList<String>();
		this.criteria = null;
	}
	
	public void addColumn (String _column) {
		this.columns.add(_column);
	}
	@Override
	public String getInstruction() 
	{
		String expression = "SELECT ";
		
		for (String column : this.columns) 
		{
			expression += column;
			if (!column.equals(this.columns.get(this.columns.size() -1))) 
			{
				expression += ", ";
			}
		}
		expression += " FROM " + this.entity;
		
		if (this.criteria != null) 
		{
			expression += " WHERE " + this.criteria.dumpExpression();
		}
		return expression;
	}
}