package com.pvmp.dao.test;

import com.pvmp.dao.PVMPDatabase;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestPVMPDatabase extends TestCase 
{
	public void setUp() throws Exception {
		super.setUp();
		@SuppressWarnings("unused")
		PVMPDatabase database = new PVMPDatabase();
	}
	
	public void testIfInsertDBThrowsException () {
		try 
		{
			PVMPDatabase.insertDB(null, null, null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
	
	public void testIfUpdateDBThrowsException () {
		try 
		{
			PVMPDatabase.updateDB(null, null, null, null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
	
	public void testIfDeleteDBThrowsException () {
		try 
		{
			PVMPDatabase.deleteDB(null, null, null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
	
	public void testIfSelectDBThrowsException () {
		try 
		{
			PVMPDatabase.selectDB(null, null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
}
