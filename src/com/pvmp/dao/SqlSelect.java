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
				if (!column.equals(this.columns.get(this.columns.size() -1))) 
				{
					expression += ", ";
				}
			}
		}
		
		expression += " FROM " + this.entity;
		
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
	
	/**
	 * @brief Returns the ArrayList with all the columns that should be selected.
	 * */
	public ArrayList<String> returnColumns () {
		return this.columns;
	}
}