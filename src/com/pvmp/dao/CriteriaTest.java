/**
 * @file CriteriaTest.java
 * */
package com.pvmp.dao;

import junit.framework.TestCase;

/**
 * @class CriteriaTest
 * @brief Class responsible for testing the Criteria class, which is one of
 * 		  of the classes that compose the DAO.
 * */
public class CriteriaTest extends TestCase 
{
	private Criteria criteria1;
	private Criteria criteria2;
	private Criteria criteria3;
	private Criteria criteria4;
	private Criteria criteria5;
	private Filter filter1;
	private Filter filter2;
	private Filter filter3;
	private Filter filter4;
	private Filter filter5;
	
	@Override
	public void setUp () 
	{
		this.criteria1 = new Criteria();
		this.criteria2 = new Criteria();
		this.criteria3 = new Criteria();
		this.criteria4 = new Criteria();
		this.criteria5 = new Criteria();
		
		this.filter1 = new Filter("SEXO", "=");
		this.filter1.setValue("F");
		
		this.filter2 = new Filter("IDADE", ">");
		this.filter2.setValue(18);
		
		this.filter3 = new Filter("SEXO", "=");
		this.filter3.setValue("M");
		
		this.filter4 = new Filter("IDADE", "<");
		this.filter4.setValue(16);
		
		this.filter5 = new Filter("IDADE", ">");
		this.filter5.setValue(60);
	}
	
	/**
	 * @brief Test if the method dumpExpression, of many Criteria objects
	 * 		  instantiated on this.setUp(), will correctly return the desired
	 * 		  sentence.
	 * */
	public void testDumpExpression() 
	{
		this.criteria1.add(this.filter1, Expression.AND_OPERATOR);
		this.criteria1.add(this.filter2, Expression.AND_OPERATOR);
		
		this.criteria2.add(this.filter3, Expression.AND_OPERATOR);
		this.criteria2.add(this.filter4, Expression.AND_OPERATOR);
		
		this.criteria3.add(this.criteria1, Expression.OR_OPERATOR);
		this.criteria3.add(this.criteria2, Expression.OR_OPERATOR);
		
		this.criteria4.add(filter4, Expression.OR_OPERATOR);
		this.criteria4.add(filter5, Expression.OR_OPERATOR);
		
		this.criteria5.add(criteria3, Expression.AND_OPERATOR);
		this.criteria5.add(criteria4, Expression.AND_OPERATOR);
		
		assertEquals("((SEXO = 'F' AND IDADE > 18) OR (SEXO = 'M' AND IDADE < 16))", this.criteria3.dumpExpression());
		assertEquals("(IDADE < 16 OR IDADE > 60)", this.criteria4.dumpExpression());
		assertEquals("(((SEXO = 'F' AND IDADE > 18) OR (SEXO = 'M' AND IDADE < 16)) AND (IDADE < 16 OR IDADE > 60))",
				     this.criteria5.dumpExpression());
	}
}
