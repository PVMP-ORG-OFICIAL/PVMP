/**
 * @file SqlInstructionQuery.java
 * @brief
 * */
package com.pvmp.dao;

/**
 * @class SqlInstructionQuery
 * @brief Abstract class that holds common methods for different SQL instructions
 * 		  (SELECT, INSERT, DELETE, UPDATE).
 * */
public abstract class SqlInstructionQuery 
{
	
	protected String finalExpression;
	protected Criteria criteria;
	protected String entity;
	
	public void setCriteria (Criteria _criteria)
	{
		this.criteria = _criteria;
	}
	
	public final void setEntity(String _entity) 
	{
		this.entity = _entity;
	}
	
	public final String getEntity()
	{
		return this.entity;
	}
	
	/**
	 * @brief Method that will generate the instruction based from which "child class" it 
	 * is called. For example, if it is being called by SqlSelect class, a full SQL Select
	 * instruction will be generated based on criteria(s) and entities.
	 * */
	public abstract String getInstruction();

}
