package com.pvmp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper 
{

	public static final String DATABASE_NAME = "PVMP_DATABASE";
	public static final int VERSION = 2;
	
	private static PersistenceHelper instance = null;
	
	private PersistenceHelper(Context _context) 
	{
		super(_context, DATABASE_NAME, null, VERSION);
	}
	
	public static PersistenceHelper getInstance(Context context) 
	{
		if(instance == null)
		{
			instance = new PersistenceHelper(context.getApplicationContext());
		}
		
		return instance;
	}
	
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(UserDAO.SCRIPT_TABLE_CREATION_USER);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		db.execSQL(UserDAO.SCRIPT_TABLE_DELETION);
		onCreate(db);
	}
}
