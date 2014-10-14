/**
 * @file Filter.java
 * @brief 
 * */
package com.pvmp.dao;

import java.util.Locale;

/**
 *  @class Filter
 *  @brief
 * */
public final class Filter extends Expression 
{
	private String variable;
	private String operator;
	private String value;
	
	public Filter(String _variable, String _operator, String _value) 
	{
		this.variable = _variable;
		this.operator = _operator;		
		this.value = this.transform(_value);
	}
	
	//public Filter(int )

	@Override
	public String dumpExpression() 
	{
		return null;
	}
	
	/**
	 * @param _value
	 * @brief
	 * */
	public String transform(String[] _value)
	{
		return null;		
	}
	
	public String transform(int[] _value)
	{
		return null;	
	}
	
	public String transform(Object _value)
	{
		if (_value == null)
		{
			return null;
		}
		
		if (((String)_value).toLowerCase(Locale.US).trim().equals("null"))
		{
			return "NULL";
		}
		else if (_value instanceof Integer)
		{
			int value = (int)_value;
			return Integer.toString(value);
		}
		else if (_value instanceof Boolean)
		{
			boolean value = (boolean)_value;
			return (value == true) ? "TRUE" : "FALSE";
		}
		else if (_value instanceof String)
		{
			//ATTENTION!!! YOU MUST TO CHECK THE CHAR '' INSIDE OF THE STRING
			return "'" + (String)_value + "'";
		}
		return null;
	}
}
