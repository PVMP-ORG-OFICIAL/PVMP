package com.pvmp.model.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import android.content.ContentValues;

import com.pvmp.models.Deputy;
import com.pvmp.models.Party;

public class TestParty extends TestCase {
	
	private Deputy deputy;
	private Deputy deputy2;
	private Deputy deputy3;
	private Party party;
	private ArrayList<Deputy> deputies;

	public TestParty(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		deputy = new Deputy();
		deputy2 = new Deputy();
		deputy3 = new Deputy();
		party = new Party();
		deputies = new ArrayList<Deputy>();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerateContentValues() {
		assertNull(party.generateContentValues());
	}

	public void testParty() {
		
		deputy.setName("Lucas");
		deputy2.setName("Iago");
		deputies.add(deputy);
		deputies.add(deputy2);
		
		party.setNumber(10);
		party.setAcronym("PSDB");
		party.setDeputies(deputies);
		
		assertNotNull(party);
		assertEquals(party.getNumber(), (Integer) 10);
		assertEquals(party.getAcronym(), "PSDB");
		assertEquals(party.getDeputies().get(0).getName(), "Lucas");
		assertEquals(party.getDeputies().get(1).getName(), "Iago");
		assertNotSame(party.getDeputies().get(0), party.getDeputies().get(1));
	}

	public void testSetNumber() {
		party.setNumber(23);
		
		assertEquals(party.getNumber(), (Integer) 23);
	}
	
	public void testGetNumber() {
		party.setNumber(30);
		
		assertEquals(party.getNumber(), (Integer) 30);
	}
	
	public void testSetAcronym() {
		party.setAcronym("PSOL");
		
		assertEquals(party.getAcronym(), "PSOL");
	}
	
	public void testGetAcronym() {
		party.setAcronym("PBS");
		
		assertEquals(party.getAcronym(), "PBS");
	}
	
	public void testSetDeputies() {		
		deputies.add(deputy);
		deputies.add(deputy2);
		deputies.add(deputy3);
		
		assertEquals(deputies.size(), 3);
		assertNotSame(deputy, deputy2);
	}

	public void testGetDeputies() {
		deputy.setName("João");
		deputy2.setName("João");
		deputies.add(deputy);
		deputies.add(deputy2);
		party.setDeputies(deputies);
		
		assertEquals(party.getDeputies().get(0).getName(), party.getDeputies().get(1).getName());
		assertNotSame(party.getDeputies().get(0), party.getDeputies().get(1));
	}

	public void testContentValuesToModelContentValues() {
		party.setNumber(5);
		party.setAcronym("PBL");
		party.setDeputies(deputies);
		
		ContentValues cValues = new ContentValues();
		
		cValues.put(Party.COLUMN_NUMBER_PARTY, party.getNumber());
		cValues.put(Party.COLUMN_ACRONYM, party.getAcronym());
		
		assertNotNull(party.contentValuesToModel(cValues));
	}
	
	public void testParseNumber() {
		assertEquals("PRB", Party.parseNumber(10f));
		assertEquals("PP", Party.parseNumber(11f));
		assertEquals("PDT", Party.parseNumber(12f));
		assertEquals("PT", Party.parseNumber(13f));
		assertEquals("PTB", Party.parseNumber(14f));
		assertEquals("PMDB", Party.parseNumber(15f));
		assertEquals("PSTU", Party.parseNumber(16f));
		assertEquals("PSL", Party.parseNumber(17f));
		assertEquals("PTN", Party.parseNumber(19f));
		assertEquals("PSC", Party.parseNumber(20f));
		assertEquals("PCB", Party.parseNumber(21f));
		assertEquals("PR", Party.parseNumber(22f));
		assertEquals("PPS", Party.parseNumber(23f));
		assertEquals("DEM", Party.parseNumber(25f));
		assertEquals("PSDC", Party.parseNumber(27f));
		assertEquals("PRTB", Party.parseNumber(28f));
		assertEquals("PCO", Party.parseNumber(29f));
		assertEquals("PHS", Party.parseNumber(31f));
		assertEquals("PMN", Party.parseNumber(33f));
		assertEquals("PTC", Party.parseNumber(36f));
		assertEquals("PSB", Party.parseNumber(40f));
		assertEquals("PV", Party.parseNumber(43f));
		assertEquals("PRP", Party.parseNumber(44f));
		assertEquals("PSDB", Party.parseNumber(45f));
		assertEquals("PSOL", Party.parseNumber(50f));
		assertEquals("PPL", Party.parseNumber(54f));
		assertEquals("PSD", Party.parseNumber(55f));
		assertEquals("PCdoB", Party.parseNumber(65f));
		assertEquals("PTdoB", Party.parseNumber(70f));
		assertEquals("SDD", Party.parseNumber(77f));
		assertEquals("PROS", Party.parseNumber(90f));
		assertNull(Party.parseNumber(100f));
	}

}
