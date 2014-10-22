/**
 * @file DAOAbstract.java
 * */
package com.pvmp.dao;

import android.content.ContentValues;
import android.content.Context;

/**
 * @class DAOAbstract
 * @brief An abstract class responsible for defining what methods and how them should
 * 		  be implemented on every PVMPModel class.
 * */
public abstract class DAOAbstract 
{
	private String TABLE_NAME;
	
	/**
	 * @_context
	 * @brief Template method of Database Inserting of every class that will
	 *        extends from this abstract.
	 * */
	public final void insertDB(Context _context) 
	{
		ContentValues _values = this.generateContentValues();
		PVMPDatabase.insertDB(TABLE_NAME, _values, _context);
	}
	/**
	 * @_whereExpression
	 * @_context
	 * @brief Template method of Database Updating of every class that will
	 *        extends from this abstract.
	 * */
	public final void updateDB(Expression _whereExpression, Context _context) 
	{
		ContentValues _values = this.generateContentValues();
		PVMPDatabase.updateDB(this.TABLE_NAME, _values, _whereExpression.dumpExpression(), _context);
	}
	public abstract void deleteDB() 
	{
		
	}
	public abstract void selectDB();
	public abstract ContentValues generateContentValues();
}
