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
	 * @param _context
	 * @brief Template method for Database Inserting of every class that will
	 *        extends from this abstract.
	 * */
	public final void insertDB(Context _context) 
	{
		if (_context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.insertDB()");
		}
		
		ContentValues _values = this.generateContentValues();
		PVMPDatabase.insertDB(this.TABLE_NAME, _values, _context);
	}
	
	/**
	 * @param _whereExpression
	 * @param _context
	 * @brief Template method for Database Updating of every class that will
	 *        extends from this abstract.
	 * */
	public final void updateDB(Expression _whereExpression, Context _context) 
	{
		if (_whereExpression == null || _context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.updateDB()");
		}
		ContentValues _values = this.generateContentValues();
		PVMPDatabase.updateDB(this.TABLE_NAME, _values, _whereExpression.dumpExpression(), _context);
	}
	
	/**
	 * @param _whereExpression
	 * @param _context
	 * @brief Template method for Database Delete of every class that will
	 *        extends from this abstract.
	 * */
	public final void deleteDB(Expression _whereExpression, Context _context) 
	{	
		if (_whereExpression == null || _context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.deleteDB()");
		}
		
		PVMPDatabase.deleteDB(this.TABLE_NAME, _whereExpression.dumpExpression(), _context);
	}
	
	public final void selectDB() {
		
	}
	
	/**
	 * @brief Returns a ContentValues object based on the current instance that calls it.
	 * */
	public abstract ContentValues generateContentValues();
}
