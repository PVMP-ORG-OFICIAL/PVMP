package com.pvmp.dao;

import junit.framework.TestCase;

public class SqlSelectTest extends TestCase {
	
	private Criteria c1;
	private Criteria c2;
	private Filter f1;
	private Filter f2;
	private Filter f3;
	private SqlSelect sqlSelect;
	
	@Override 
	public void setUp () 
	{
		this.c1 = new Criteria();
		this.c2 = new Criteria();
		
		this.f1 = new Filter("IDADE", ">");
		this.f1.setValue(35);
		
		this.f2 = new Filter("IDADE", "<");
		this.f2.setValue(60);
		
		this.f3 = new Filter("ESCOLARIDADE", "=");
		this.f3.setValue("Superior");
		
		this.c1.add(this.f1, Expression.AND_OPERATOR);
		this.c1.add(this.f2, Expression.AND_OPERATOR);
		
		this.c2.add(this.c1, Expression.OR_OPERATOR);
		this.c2.add(this.f3, Expression.OR_OPERATOR);
		
		this.sqlSelect = new SqlSelect();
		
		this.sqlSelect.setEntity("USUARIO");
		this.sqlSelect.addColumn("nome");
		this.sqlSelect.addColumn("email");
		this.sqlSelect.setCriteria(this.c2);
	}
	
	public void testGetInstruction ()
	{
		assertEquals("SELECT nome, email FROM USUARIO WHERE ((IDADE > 35 AND IDADE < 60) OR ESCOLARIDADE = 'Superior')",
					  this.sqlSelect.getInstruction());
	}
}
