package com.pvmp.dao;

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
	private Filter f1;
	private Filter f2;
	private Filter f3;
	private Filter f4;
	private Filter f5;
	private Filter f6;
	
	@Override
	public void setUp() throws Exception {
		String votes[] = {"Sim", "Nao"};
		int years[] = {2005, 2008, 2013};
		
		this.f1 = new Filter("DATA", "=");
		this.f1.setValue("2007-06-02");
		this.f2 = new Filter("ANO", ">");
		this.f2.setValue(2010);
		this.f3 = new Filter("VOTO", "IN");
		this.f3.setValue(votes);
		this.f4 = new Filter("ESTADO", "=");
		this.f4.setValue(true);
		this.f5 = new Filter("ESTADO", "IS NOT");
		this.f5.setValue("null");
		this.f6 = new Filter("ANO", "IN");
		this.f6.setValue(years);
	}
	
	public void testDumpExpression () {
		try 
		{
			assertEquals(this.f1.dumpExpression(), "DATA = '2007-06-02'");
			assertEquals(this.f2.dumpExpression(), "ANO > 2010");
			assertEquals(this.f3.dumpExpression(), "VOTO IN ('Sim', 'Nao')");
			assertEquals(this.f4.dumpExpression(), "ESTADO = TRUE");
			assertEquals(this.f5.dumpExpression(), "ESTADO IS NOT NULL");
			assertEquals(this.f6.dumpExpression(), "ANO IN (2005, 2008, 2013)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
