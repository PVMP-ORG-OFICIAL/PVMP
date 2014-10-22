/**
* @file PVMPDatabase.java
 * @brief 
 * */
package com.pvmp.dao;

import com.pvmp.database.PersistenceHelper;
import com.pvmp.util.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @class PVMPDatabase
 * @brief Responsible for handling database operations.
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
	 * @brief Insert a row on the Database. Already initiate a WritableDatabase inside.
	 * */
	public static void insertDB(String _tableName, ContentValues _values, Context _context)
	{
		if (_tableName == null || _values == null || _context == null)
		{
			Util.debug("PVMPDatabase: insertDB deu treta.");
			return;
		}
		
		database = getWritePVMP(_context);		
		database.insert(_tableName, null, _values);
		
		return;
	}
	
	/**
	 * @param _tableName
	 * @param _values
	 * @param _whereClause
	 * @param _whereArgs
	 * @param _context
	 * @brief Update a row on the Database. Already initiate a WritableDatabase inside.
	 **/
	public static void updateDB(String _tableName, ContentValues _values, String _whereClause,
								Context _context)
	{
		if (_tableName == null || _values == null || _context == null)
		{
			Util.debug("PVMPDatabase: updateDB deu treta.");
			return;
		}
		
		database = getWritePVMP(_context);
		database.update(_tableName, _values, _whereClause, null);
	}
	
	/**
	 * @param _tableName 
	 * @param _columnName
	 * @param _whereArgs
	 * @param _context
	 * @brief Update a row on the Database. Already initiate a WritableDatabase inside.
	 * */
	public static void deleteDB(String _tableName, String _columnName, 
			String[] _whereArgs, Context _context)
	{
		if (_tableName == null || _columnName == null || _context == null 
				|| _whereArgs == null)
		{
			Util.debug("PVMPDatabase: deleteDB deu treta.");
			return;
		}
		
		database = getWritePVMP(_context);
		database.delete(_tableName, _columnName + " = ?", _whereArgs);
	}
	
	public static Cursor selectDB()
	{
		return null;
	}
	
	private static SQLiteDatabase getReadablePVMP(Context _context)
	{
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(_context);
		return persistenceHelper.getReadableDatabase();
	}
	
	private static SQLiteDatabase getWritePVMP(Context _context)
	{
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(_context);
		return persistenceHelper.getWritableDatabase();
	}
}
