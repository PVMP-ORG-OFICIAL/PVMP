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
		this.expression = null;
		this.entities = new ArrayList<String>();
	}
	
	@Override
	public String getInstruction() 
	{
		String expression = "SELECT ";
		
		if (this.columns.size() == 0)
		{
			expression += "*";
		}
		else 
		{
			for (String column : this.columns) 
			{
				expression += column;
				if (this.columns.size() > 1)
				{
					if (!column.equals(this.columns.get(this.columns.size() -1))) 
					{
						expression += ", ";
					}
				}
			}
		}
		
		expression += " FROM ";
		
		for (String entity : this.entities)
		{
			expression += entity;
			if (this.entities.size() > 1) 
			{
				if (!entity.equals(this.columns.get(this.entities.size() -1))) 
				{
					expression += ", ";
				}
			}
		}
		
		if (this.expression != null) 
		{
			expression += " WHERE " + this.expression.dumpExpression();
		}
		
		return expression;
	}
	
	/**
	 * @param _column
	 * @brief Add a column to the ArrayList that manages which columns should be selected on
	 *        a Database Select action. If all columns should be selected, you never call this method,
	 *        because getInstruction will verify and add a "*" symbol to the expression.
	 * */
	public void addColumn (String _column) {
		this.columns.add(_column);
	}
	
	public void addEntity (String _entity) {
		this.entities.add(_entity);
	}
	
	/**
	 * @brief Returns the ArrayList with all the columns that should be selected.
	 * */
	public ArrayList<String> returnColumns () {
		return this.columns;
	}
}