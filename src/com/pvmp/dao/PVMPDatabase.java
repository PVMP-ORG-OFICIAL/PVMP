/**
* @file PVMPDatabase.java
 * @brief 
 * */
package com.pvmp.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * @class PVMPDatabase
 * @brief Responsible for handling "heavy" database operations.
 * */
public class PVMPDatabase 
{
	private static SQLiteDatabase database = null;
	
	private PVMPDatabase() 	
	{}
	
	/**
	 * @param _context
	 * @brief Constructor of the class. Need a context to start a PersistenceHelper
	 * 		  instance.
	 **/
	private PVMPDatabase(Context _context) 
	{
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(_context);
		database = persistenceHelper.getWritableDatabase();
	}
	
	/**
	 * @param _tableName
	 * @param _values
	 * @param _context
	 * @throws Exception 
	 * @brief Insert a row on the Database. Already initiate a WritableDatabase inside.
	 * */
	public static void insertDB(String _tableName, ContentValues _values, Context _context)
	{
		if (_tableName == null || _values == null || _context == null)
		{
			throw new NullPointerException("Null values at PVMPDatabase.insertDB()");
		}
		
		database = getWritablePVMP(_context);		
		try 
		{
			database.insertOrThrow(_tableName, null, _values);
		}
		catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
			throw new SQLiteException();
		}
	}
	
	/**
	 * @param _tableName
	 * @param _values
	 * @param _whereClause
	 * @param _context
	 * @brief Update a row on the Database. Already initiate a WritableDatabase inside.
	 **/
	public static void updateDB(String _tableName, ContentValues _values, String _whereClause,
								Context _context)
	{
		if (_tableName == null || _values == null || _whereClause == null
			|| _context == null)
		{
			throw new NullPointerException("Null value at PVMPDatabase.updateDB()");
		}
		
		database = getWritablePVMP(_context);
		database.update(_tableName, _values, _whereClause, null);
	}
	
	/**
	 * @param _tableName 
	 * @param _columnName
	 * @param _context
	 * @brief Update a row on the Database. Already initiate a WritableDatabase inside.
	 * */
	public static void deleteDB(String _tableName, String _whereClause, Context _context)
	{
		if (_tableName == null || _whereClause == null || _context == null)
		{
			throw new NullPointerException("Null value at PVMPDatabase.deleteDB()");
		}
		
		database = getWritablePVMP(_context);
		database.delete(_tableName, _whereClause, null);
	}
	
	/**
	 * @param _queryExpression 
	 * @param _context
	 * @brief Update a row on the Database. Already initiate a ReadableDatabase inside.
	 * */
	public static ArrayList<ContentValues> selectDB(SqlSelect _queryExpression, Context _context)
	{	
		if (_queryExpression == null || _context == null)
		{
			throw new NullPointerException("Null value at PVMPDatabase.selectDB()");
		}
		
		ContentValues contentValues;
		ArrayList<ContentValues> arrayContentValues = new ArrayList<ContentValues>();
		database = getReadablePVMP(_context);
		
		Cursor cursor = database.rawQuery(_queryExpression.getInstruction(), null);

        if (cursor.moveToFirst()) 
        {
        	do 
        	{	
        		contentValues = new ContentValues();
        		DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
        		arrayContentValues.add(contentValues);
        	}while (cursor.moveToNext());
        	
        	cursor.close();
        }
        
		return arrayContentValues;
	}
	/**
	 * @param _context
	 * @brief Returns a Persistence Helper Readable Database.
	 * */
	private static SQLiteDatabase getReadablePVMP(Context _context)
	{
		SQLiteDatabase databaseToBeReturned = null;
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(_context);
		try 
		{
			databaseToBeReturned = persistenceHelper.getReadableDatabase();
		}
		catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
		}
		return databaseToBeReturned;
	}
	
	/**
	 * @param _context
	 * @brief Returns a Persistence Helper Writable Database.
	 * */
	private static SQLiteDatabase getWritablePVMP(Context _context)
	{
		SQLiteDatabase databaseToBeReturned = null;
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(_context);
		try 
		{
			databaseToBeReturned = persistenceHelper.getWritableDatabase();
		}
		catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
		}
		return databaseToBeReturned;
	}
}
