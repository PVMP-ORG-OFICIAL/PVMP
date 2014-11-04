/**
 * @file DAOAbstract.java
 * */
package com.pvmp.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;

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
	 * @brief Template method for Database Inserting of every PVMP class that will
	 *        extends from this abstract.
	 * */
	public final void insertDB(Context _context) throws SQLiteException
	{
		if (_context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.insertDB()");
		}
		
		ContentValues values = this.generateContentValues();
		try {
			PVMPDatabase.insertDB(this.TABLE_NAME, values, _context);
		}
		catch(SQLiteException sqlE) {
			sqlE.printStackTrace();
			throw new SQLiteException();
		}
	}
	
	/**
	 * @param _whereExpression
	 * @param _context
	 * @brief Template method for Database Updating of every PVMP class that will
	 *        extends from this abstract.
	 * */
	public final void updateDB(Expression _whereExpression, Context _context) 
	{
		if (_whereExpression == null || _context == null)
		{
			throw new NullPointerException("Null value at DAOAbstract.updateDB()");
		}
		
		ContentValues values = this.generateContentValues();
		PVMPDatabase.updateDB(this.TABLE_NAME, values, _whereExpression.dumpExpression(), _context);
	}
	
	/**
	 * @param _whereExpression
	 * @param _context
	 * @brief Template method for Database Delete of every PVMP class that will
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
	
	/**
	 * @param _queryExpression
	 * @param _context
	 * @throws Throwable 
	 * @brief Template method for Database Select of every PVMP class that will
	 *        extends from this abstract. Returns a ArrayList containing
	 *        every row (might be seen as a Object) that obeys the Query Expression
	 *        (_queryExpression).
	 * */
	//Study a way to make it or contentValuesToModel static, so every object returned won't depend of a single instance.
	public final ArrayList<DAOAbstract> selectDB(SqlSelect _queryExpression, Context _context)  
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
			DAOAbstract legislativeObject = LegislativeFactory.getLegislative(this.getClass().getSimpleName());
			arrayModels.add(legislativeObject.contentValuesToModel(arrayContentValues.get(i)));
		}
		
		return arrayModels;
	}
	
	/**
	 * @brief Returns a ContentValues object based on the current instance that calls it.
	 * */
	public abstract ContentValues generateContentValues();
	
	/**
	 * @param _contentValues
	 * @brief Returns an object of the class that calls it, based on some ContentValues.
	 * */
	public abstract DAOAbstract contentValuesToModel(ContentValues _contentValues);
}
