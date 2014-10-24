/**
 * @file PVMPDatabaseTest.java
 * */
package com.pvmp.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * @class PVMPDatabaseTest
 * @brief Responsible for testing the class PVMPDatabase.
 * */
public class PVMPDatabaseTest extends AndroidTestCase 
{
	private ContentValues values;
	private RenamingDelegatingContext context;
	private PVMPDatabase databaseHelper;
	private PersistenceHelper persistenceHelper;
	private SQLiteDatabase db;
	
	@Override
	public void setUp() throws Exception 
	{
		super.setUp();
		
		context = new RenamingDelegatingContext(getContext(), "test_");
		values = null;
		
		databaseHelper.insertDB("USER", values, context);
	}
	
	public void tearDown() throws Exception 
	{
		db.close();
		super.tearDown();
	}

}
