package com.pvmp.dao.test;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.model.User;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestDAOAbstract extends TestCase 
{
	private DAOAbstract modelObject;
	
	public void setUp() throws Exception {
		modelObject = new User();
	}
	
	public void testIfInsertDBThrowsException () {
		try 
		{
			modelObject.insertDB(null);
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
			modelObject.updateDB(null, null);
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
			modelObject.deleteDB(null, null);
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
			modelObject.selectDB(null, null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
}
