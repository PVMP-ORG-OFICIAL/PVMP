package com.pvmp.model.test;

import android.content.ContentValues;

import com.pvmp.models.Proposition;

import junit.framework.TestCase;

public class TestProposition extends TestCase {
	
	private Proposition propositionEmpty;
	private Proposition propositionFull;

	public TestProposition(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		propositionEmpty = new Proposition();
		propositionFull = new Proposition(10, "01/01/2014", "Da providências sobre...",
				"Projeto de Lei", "João Pereira", "Votada", 204, "Saúde");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerateContentValues() {
		assertNull(propositionEmpty.generateContentValues());
	}

	public void testPropositionEmpty() {
		assertNotNull(propositionEmpty);
	}

	public void testPropositionFull() {
		assertNotNull(propositionFull);
	}
	
	public void testPropositionConstructors() {
		assertNotSame(propositionFull, propositionEmpty);
	}
	
	public void testSetAuthor() {
		propositionEmpty.setAuthor("Luiz Gustavo");
		
		assertEquals(propositionEmpty.getAuthor(), "Luiz Gustavo");
	}

	public void testGetAuthor() {
		assertEquals(propositionFull.getAuthor(), "João Pereira");
	}
	
	public void testSetId() {
		propositionEmpty.setId(30);
		
		assertEquals(propositionEmpty.getId(), (Integer) 30);
	}

	public void testGetId() {
		assertEquals(propositionFull.getId(), (Integer) 10);
	}
	
	public void testSetYear() {
		propositionEmpty.setYear("01/01/1990");
		
		assertEquals(propositionEmpty.getYear(), "01/01/1990");
	}

	public void testGetYear() {
		assertEquals(propositionFull.getYear(), "01/01/2014");
	}
	
	public void testSetMenu() {
		propositionEmpty.setMenu("Fala sobre a segurança pública...");
		
		assertEquals(propositionEmpty.getMenu(), "Fala sobre a segurança pública...");
	}

	public void testGetMenu() {
		assertEquals(propositionFull.getMenu(), "Da providências sobre...");
	}
	
	public void testSetAcronym() {
		propositionEmpty.setAcronym("Emenda à constituição");
		
		assertEquals(propositionEmpty.getAcronym(), "Emenda à constituição");
	}

	public void testGetAcronym() {
		assertEquals(propositionFull.getAcronym(), "Projeto de Lei");
	}
	
	public void testSetSituation() {
		propositionEmpty.setSituation("Em votação");
		
		assertEquals(propositionEmpty.getSituation(), "Em votação");
	}

	public void testGetSituation() {
		assertEquals(propositionFull.getSituation(), "Votada");
	}
	
	public void testSetNumber() {
		propositionEmpty.setNumber(666);
		
		assertEquals(propositionEmpty.getNumber(), (Integer) 666);
	}

	public void testGetNumber() {
		assertEquals(propositionFull.getNumber(), (Integer) 204);
	}
	
	public void testSetCategory() {
		propositionEmpty.setCategory("Previdência");
		
		assertEquals(propositionEmpty.getCategory(), "Previdência");
	}

	public void testGetCategory() {
		assertEquals(propositionFull.getCategory(), "Saúde");
	}

	public void testContentValuesToModel() {
		ContentValues cValues = new ContentValues();
		
		cValues.put(Proposition.COLUMN_ID_PROP, propositionFull.getId());
		cValues.put(Proposition.COLUMN_YEAR, propositionFull.getYear());
		cValues.put(Proposition.COLUMN_MENU, propositionFull.getMenu());
		cValues.put(Proposition.COLUMN_AUTHOR, propositionFull.getAuthor());
		cValues.put(Proposition.COLUMN_ACRONYM, propositionFull.getAcronym());
		cValues.put(Proposition.COLUMN_SITUATION, propositionFull.getSituation());
		cValues.put(Proposition.COLUMN_NUMBER, propositionFull.getNumber());
		cValues.put(Proposition.COLUMN_CATEGORY, propositionFull.getCategory());
		
		assertNotNull(propositionFull.contentValuesToModel(cValues));
	}

}
