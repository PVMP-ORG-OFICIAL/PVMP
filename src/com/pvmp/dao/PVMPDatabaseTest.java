/**
 * @file PVMPDatabaseTest.java
 * */
package com.pvmp.dao;

import java.util.ArrayList;

import org.junit.Test;

import com.pvmp.models.User;

//import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * @class PVMPDatabaseTest
 * @brief Responsible for testing the class PVMPDatabase.
 * */
//Might be renamed to DAOAbstract
public class PVMPDatabaseTest extends AndroidTestCase 
{
	//private ContentValues values;
	private RenamingDelegatingContext context;
	//private PVMPDatabase databaseHelper;
	//private PersistenceHelper persistenceHelper;
	private SQLiteDatabase db;
	private User user;
	private SqlSelect queryExpression;
	private Filter whereFilter;
	private ArrayList<DAOAbstract> users;
	
	@Override
	public void setUp() throws Exception 
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test_");
		this.user = new User("Joao", "juca123", "senha1234", "email", 19, "Superior", "M", "S");
		
		//this.values = new ContentValues();
		
		this.queryExpression = new SqlSelect();
		
		this.whereFilter = new Filter("NOME", "=");
		this.whereFilter.setValue("'Joao'");
		
		this.users = new ArrayList<DAOAbstract>();
	}
	
	@Test
	public void testSomething () 
	{
		this.queryExpression.setEntity(user.TABLE_NAME);
		this.queryExpression.setExpression(this.whereFilter);
		this.user.insertDB(context);
		this.users = this.user.selectDB(this.queryExpression, context);
		
		this.user = new User();
		
		this.user = (User) this.users.get(0);
		
		assertEquals("Joao", this.user.getName());
	}
	
	public void tearDown() throws Exception 
	{
		db.close();
		super.tearDown();
	}

}
