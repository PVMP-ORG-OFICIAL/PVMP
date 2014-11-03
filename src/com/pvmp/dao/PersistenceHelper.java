package com.pvmp.dao;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;


public class PersistenceHelper extends SQLiteAssetHelper
{

	public static final String DATABASE_NAME = "PVMPdatabase.db";
	public static final int DATABASE_VERSION = 1;
	public static String DATABASE_DIR;
	
	private static PersistenceHelper instance = null;
	
	private PersistenceHelper(Context _context) 
	{
		super(_context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static PersistenceHelper getInstance(Context context) 
	{
		if(instance == null)
		{
			instance = new PersistenceHelper(context.getApplicationContext());
		}
		return instance;
	}
}
