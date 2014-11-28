/**
 * @file FilterTest.java
 * */
package com.pvmp.dao.test;

import java.util.ArrayList;

import com.pvmp.dao.Filter;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @class FilterTest
 * @brief Class responsible for testing the Filter class, which is one of
 * 		  of the classes that compose the DAO. Basically, it will test if
 *        the parameters passed to a Filter object will be returned, as a
 *        String, in the correct way.
 * */

public class FilterTest extends TestCase 
{
	private Filter filterData;
	private Filter filterYear1;
	private Filter filterVote;
	private Filter filterState1;
	private Filter filterState2;
	private Filter filterYear2;
	
	@Override
	public void setUp() throws Exception {
		String votes[] = {"Sim", "Nao"};
		ArrayList<Integer> years = new ArrayList<Integer>();
		
		years.add(2005); years.add(2008); years.add(2013);
		
		this.filterData = new Filter("DATA", "=");
		this.filterData.setValue("2007-06-02");
		
		this.filterYear1 = new Filter("ANO", ">");
		this.filterYear1.setValue(2010);
		
		this.filterVote = new Filter("VOTO", "IN");
		this.filterVote.setValue(votes);
		
		this.filterState1 = new Filter("ESTADO", "=");
		this.filterState1.setValue(true);
		
		this.filterState2 = new Filter("ESTADO", "IS NOT");
		this.filterState2.setValue("null");
		
		this.filterYear2 = new Filter("ANO", "IN");
		this.filterYear2.setValue(years);
	}
	
	/**
	 * @brief Test if the method dumpExpression, of many Filter objects
	 * 		  instantiated on this.setUp(), will correctly return the desired
	 * 		  sentence.
	 * */
	public void testDumpExpression() {
		assertEquals(this.filterData.dumpExpression(), "DATA = '2007-06-02'");
		assertEquals(this.filterYear1.dumpExpression(), "ANO > 2010");
		assertEquals(this.filterVote.dumpExpression(), "VOTO IN ('Sim', 'Nao')");
		assertEquals(this.filterState1.dumpExpression(), "ESTADO = TRUE");
		assertEquals(this.filterState2.dumpExpression(), "ESTADO IS NOT NULL");
		assertEquals(this.filterYear2.dumpExpression(), "ANO IN (2005, 2008, 2013)");
	}
	
	public void testTransformFloat () {
		Float value = 3f;
		assertEquals("3.0", filterData.transform(value));
	}
	public void testTransformBooleanTrue () {
		Boolean value = true;
		assertEquals("TRUE", filterData.transform(value));
	}
	public void testTransformBooleanFalse () {
		Boolean value = false;
		assertEquals("FALSE", filterData.transform(value));
	}
	public void testTransformNull () {
		assertNull(filterData.transform(filterState1));
	}
	
	public void testIfTransformThrowsException () {
		try 
		{
			String[] nullValue = null;
			filterData.transform(nullValue);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
	public void testIfTransformThrowsException2 () {
		try 
		{
			ArrayList<Integer> nullValue = null;
			filterData.transform(nullValue);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
	public void testIfTransformThrowsException3 () {
		try 
		{
			Float nullValue = null;
			filterData.transform(nullValue);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
}
