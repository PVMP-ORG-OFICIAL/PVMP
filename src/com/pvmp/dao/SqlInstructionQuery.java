/**
 * @file SqlInstructionQuery.java
 * @brief
 * */
package com.pvmp.dao;

import java.util.ArrayList;

/**
 * @class SqlInstructionQuery
 * @brief Abstract class that holds common methods for different SQL instructions
 * 		  (SELECT, INSERT, DELETE, UPDATE).
 * */
public abstract class SqlInstructionQuery 
{
	
	protected String finalExpression;
	protected Expression expression;
	protected ArrayList<String> entities;
	protected String auxiliarCondition;
	
	public void setExpression (Expression _expression)
	{
		this.expression = _expression;
	}
	
	public final void setEntity(ArrayList<String> _entities) 
	{
		this.entities = _entities;
	}
	
	public final void setAuxiliarCondition(String _auxiliarCondition)
	{
		this.auxiliarCondition = _auxiliarCondition;
	}
	
	public final ArrayList<String> getEntities()
	{
		return this.entities;
	}
	
	/**
	 * @brief Method that will generate the instruction based from which "child class" it 
	 * is called. For example, if it is being called by SqlSelect class, a full SQL Select
	 * instruction will be generated based on expression(s) and entities.
	 * */
	public abstract String getInstruction();

}
