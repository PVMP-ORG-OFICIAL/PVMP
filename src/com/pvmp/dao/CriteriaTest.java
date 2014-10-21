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
	private Criteria c4;
	private Criteria c5;
	private Filter f1;
	private Filter f2;
	private Filter f3;
	private Filter f4;
	private Filter f5;
	
	@Override
	public void setUp () throws Exception 
	{
		this.c1 = new Criteria();
		this.c2 = new Criteria();
		this.c3 = new Criteria();
		this.c4 = new Criteria();
		this.c5 = new Criteria();
		
		this.f1 = new Filter("SEXO", "=");
		this.f1.setValue("F");
		
		this.f2 = new Filter("IDADE", ">");
		this.f2.setValue(18);
		
		this.f3 = new Filter("SEXO", "=");
		this.f3.setValue("M");
		
		this.f4 = new Filter("IDADE", "<");
		this.f4.setValue(16);
		
		this.f5 = new Filter("IDADE", ">");
		this.f5.setValue(60);
		
		this.c1.add(this.f1, Expression.AND_OPERATOR);
		this.c1.add(this.f2, Expression.AND_OPERATOR);
		
		this.c2.add(this.f3, Expression.AND_OPERATOR);
		this.c2.add(this.f4, Expression.AND_OPERATOR);
		
		this.c3.add(this.c1, Expression.OR_OPERATOR);
		this.c3.add(this.c2, Expression.OR_OPERATOR);
		
		this.c4.add(f4, Expression.OR_OPERATOR);
		this.c4.add(f5, Expression.OR_OPERATOR);
		
		this.c5.add(c3, Expression.AND_OPERATOR);
		this.c5.add(c4, Expression.AND_OPERATOR);
		
	}
	
	public void testDumpExpression() 
	{
		try 
		{
			assertEquals("((SEXO = 'F' AND IDADE > 18) OR (SEXO = 'M' AND IDADE < 16))", this.c3.dumpExpression());
			assertEquals("(IDADE < 16 OR IDADE > 60)", this.c4.dumpExpression());
			assertEquals("(((SEXO = 'F' AND IDADE > 18) OR (SEXO = 'M' AND IDADE < 16)) AND (IDADE < 16 OR IDADE > 60))",
					     this.c5.dumpExpression());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
