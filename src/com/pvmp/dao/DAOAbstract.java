/**
 * @file DAOAbstract.java
 * */
package com.pvmp.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;

/**
 * @class DAOAbstract
 * @brief An abstract class responsible for defining what methods and how them should
 * 		  be implemented on every PVMPModel class.
 * */
public abstract class DAOAbstract 
{
	public String TABLE_NAME;
	
	/**
	 * @param _context
	 * @brief Template method for Database Inserting of every class that will
	 *        extends from this abstract.
	 * */
	protected final void insertDB(Context _context) 
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
	protected final void updateDB(Expression _whereExpression, Context _context) 
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
	protected final void deleteDB(Expression _whereExpression, Context _context) 
	{	
		if (_whereExpression == null || _context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.deleteDB()");
		}
		
		PVMPDatabase.deleteDB(this.TABLE_NAME, _whereExpression.dumpExpression(), _context);
	}
	
	protected final ArrayList<DAOAbstract> selectDB(SqlSelect _queryExpression, Context _context) 
	{
		if (_queryExpression == null || _context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.selectDB()");
		}
		
		ArrayList<ContentValues> arrayContentValues = new ArrayList<ContentValues>();
		ArrayList<DAOAbstract> arrayModels = new ArrayList<DAOAbstract>();
		
		arrayContentValues = PVMPDatabase.selectDB(_queryExpression, _context);
		
		for (int i = 0; i < arrayContentValues.size(); i++) 
		{
			arrayModels.add(this.contentValuesToModel(arrayContentValues.get(i)));
		}
		
		return arrayModels;
	}
	
	/**
	 * @brief Returns a ContentValues object based on the current instance that calls it.
	 * */
	protected abstract ContentValues generateContentValues();
	
	/**
	 * @param _contentValues
	 * @brief Returns an object of the class that calls it, based on some ContentValues.
	 * */
	protected abstract DAOAbstract contentValuesToModel(ContentValues _contentValues);
}
