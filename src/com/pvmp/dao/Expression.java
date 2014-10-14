/**
 * @file Expression.java
 * @brief
 * */
package com.pvmp.dao;

/**
 * @class Expression
 * @brief 
 * */
public abstract class Expression 
{
	public static final String AND_OPERATOR = "AND";
	public static final String OR_OPERATOR = "OR";
	
	public Expression()
	{}
	
	/**
	 * @return 
	 * @brief 
	 **/
	public abstract String dumpExpression();
}
