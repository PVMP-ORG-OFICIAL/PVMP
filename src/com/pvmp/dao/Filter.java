/**
 * @file Filter.java
 * @brief 
 * */
package com.pvmp.dao;

import java.util.Locale;

/**
 *  @class Filter
 *  @brief Class responsible for receiving some commands, transforming them into SQL language
 *  	   expressions and give (or return) a SQL concatenated expression.
 * */
public final class Filter extends Expression 
{
	private String variable;
	private String operator;
	private String value;
	
	public Filter(String _variable, String _operator) 
	{
		this.variable = _variable;
		this.operator = _operator;
	}
	
	@Override
	public String dumpExpression() 
	{
		String expression = null;
		
		expression = this.variable + " " + this.operator + " " + this.value;
		return expression;
	}
	
	/**
	 * @param _value
	 * @brief Method responsible for receiving and identifying a parameter that
	 * 		  will compose the expression as a whole.
	 * */
	
	//Expected form: ('value1', 'value2', 'value3',...)
	private String transform(String[] _value)
	{
		if (_value == null) 
		{
			throw new NullPointerException("Null pointer at Filter.transform()");
		}
		String result = "(";
		
		for (int i = 0; i < _value.length; i++) 
		{
			result += ("'" + _value[i] + "'");
			
			//if will handle which paramater is the actual so the last paramater 
			//wont have a blank space between it and the ).
			if (i != (_value.length -1))
			{
				result += ", ";
			}
		}
		result += ")";

		return result;
	}
	
	//Expected form: (value1, value2, value3,...)
	private String transform(int[] _value)
	{
		if (_value == null) 
		{
			throw new NullPointerException("Null pointer at Filter.transform()");
		}
		
		String result = "(";
		
		for (int i = 0; i < _value.length; i++)
		{
			result += _value[i];
			
			if (i != (_value.length -1))
			{
				result += ", ";
			}
		}
		
		result += ")";
		
		return result;
	}
	
	private String transform(Object _value)
	{
		if (_value == null)
		{
			throw new NullPointerException("Null pointer at Filter.transform()");
		}
		
		if (_value instanceof Integer)
		{
			Integer value = (Integer)_value;
			return Integer.toString(value);
		}
		else if (_value instanceof Float)
		{
			Float value = (Float)_value;
			return Float.toString(value);
		}
		else if (_value instanceof Boolean)
		{
			Boolean value = (Boolean)_value;
			return (value == true) ? "TRUE" : "FALSE";
		}
		else if (_value instanceof String)
		{
			if (((String)_value).toLowerCase(Locale.US).trim().equals("null"))
			{
				return "NULL";
			}
			//ATTENTION!!! YOU MUST TO CHECK THE CHAR '' INSIDE OF THE STRING
			return "'" + (String)_value + "'";
		}
		return null;
	}
	
	/*
	 * Instead of making 3 constructors to handle 3 different types of _value,
	 * 3 setValue were made, with different type parameters. This way, repeating
	 * the set of the variable and the operator wont be needed. The way of thinking
	 * might be changed, it was just a first solution found to solve the problem.
	 */
	public void setValue(Object _value) 
	{
		this.value = this.transform(_value);
	}
	
	public void setValue(int[] _value) 
	{
		this.value = this.transform(_value);
	}
	
	public void setValue(String[] _value) 
	{
		this.value = this.transform(_value);
	}

}
