/**
 * @file Criteria.java
 * @brief
 * */
package com.pvmp.dao;

import java.util.ArrayList;

/**
 * @class Criteria
 * @brief Class responsible for creating SQL expressions in a recursive way,
 *        with help of the Filter class; which also extends from Expression.
 * */
public class Criteria extends Expression 
{

	private ArrayList<Expression> expressions;
	private ArrayList<String> operators;
	private ArrayList<String> properties;
	
	public Criteria() 
	{
		expressions = new ArrayList<Expression>();
		operators = new ArrayList<String>();
	}
	
	@Override
	public String dumpExpression() 
	{
		String expression = "(";	
		
		if(this.expressions.size() == 0)
		{
			return null;
		}
		else 
		{
			for (int i = 0; i < this.expressions.size(); i++) 
			{
				expression += this.expressions.get(i).dumpExpression();
				
				if (operators.size() > 1) 
				{
					if (i != (this.expressions.size() - 1))
					{
						expression += " ";
						expression += operators.get(i);
						expression += " ";
					}
				}
			}
		}
		expression += ")";
		
		return expression;
	}
	
	/**
	 * @param _expression
	 * @param _operator
	 * @brief Method responsible for recursively add expressions and operators to arrays
	 * 		  that will be transformed in a SQL command by the method dumpExpression()
	 */
	public void add(Expression _expression, String _operator) 
	{
		this.operators.add(_operator);
		this.expressions.add(_expression);
	}
}
