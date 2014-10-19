package com.pvmp.dao;

import junit.framework.TestCase;

/**
 * @class CriteriaTest
 * @brief Class responsible for testing the Criteria class, which is one of
 * 		  of the classes that compose the DAO.
 * */
public class CriteriaTest extends TestCase 
{
	
	private Criteria c1;
	private Criteria c2;
	private Criteria c3;
	private Filter f1;
	private Filter f2;
	private Filter f3;
	private Filter f4;
	
	@Override
	public void setUp () throws Exception {
		this.c1 = new Criteria();
		this.c2 = new Criteria();
		this.c3 = new Criteria();
		
		this.f1 = new Filter("SEXO", "=");
		this.f1.setValue("F");
		
		this.f2 = new Filter("IDADE", ">");
		this.f2.setValue(18);
		
		this.f3 = new Filter("SEXO", "=");
		this.f3.setValue("M");
		
		this.f4 = new Filter("IDADE", "<");
		this.f4.setValue(16);
		
		c1.add(f1, Expression.AND_OPERATOR);
		c1.add(f2, Expression.AND_OPERATOR);
		
		c2.add(f3, Expression.AND_OPERATOR);
		c2.add(f4, Expression.AND_OPERATOR);
		
		this.c3.add(c1, Expression.OR_OPERATOR);
		this.c3.add(c2, Expression.OR_OPERATOR);
	}
	
	public void testDumpExpression() {
		try 
		{
			System.out.println(this.c3.dumpExpression());
			assertEquals("((SEXO = 'F' AND IDADE > 18) OR (SEXO = 'M' AND IDADE < 16))", this.c3.dumpExpression());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
