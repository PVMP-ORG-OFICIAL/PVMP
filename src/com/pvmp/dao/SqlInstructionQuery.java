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
	
	public void setCriteria (Criteria criteria)
	{
		this.criteria = criteria;
	}
	
	final public void setEntity(String entity) 
	{
		this.entity = entity;
	}
	
	final public String getEntity()
	{
		return this.entity;
	}
	
	/**
	 * Method that will generate the instruction based from which "child class" it 
	 * is called. For example, if it is being called by SqlSelect class, it will
	 * generate a full SQL Select instruction based on criteria(s) and entities.
	 * */
	public abstract String getInstruction();

}
