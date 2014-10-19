/**
 * @file Expression.java
 * @brief
 * */
package com.pvmp.dao;

/**
 * @class Expression
 * @brief Abstract class responsible for generating SQL expressions
 * */
public abstract class Expression 
{
	public static final String AND_OPERATOR = "AND";
	public static final String OR_OPERATOR = "OR";
	
	public Expression()
    {}
	
	/**
	 * @return String (?)
	 * @brief Method responsible for returning a SQL expression as a String
	 **/
	public abstract String dumpExpression();
}
