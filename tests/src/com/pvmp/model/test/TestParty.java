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
		assertEquals("PRB", Party.parseNumber(10));
		assertEquals("PP", Party.parseNumber(11));
		assertEquals("PDT", Party.parseNumber(12));
		assertEquals("PT", Party.parseNumber(13));
		assertEquals("PTB", Party.parseNumber(14));
		assertEquals("PMDB", Party.parseNumber(15));
		assertEquals("PSTU", Party.parseNumber(16));
		assertEquals("PSL", Party.parseNumber(17));
		assertEquals("PTN", Party.parseNumber(19));
		assertEquals("PSC", Party.parseNumber(20));
		assertEquals("PCB", Party.parseNumber(21));
		assertEquals("PR", Party.parseNumber(22));
		assertEquals("PPS", Party.parseNumber(23));
		assertEquals("DEM", Party.parseNumber(25));
		assertEquals("PSDC", Party.parseNumber(27));
		assertEquals("PRTB", Party.parseNumber(28));
		assertEquals("PCO", Party.parseNumber(29));
		assertEquals("PHS", Party.parseNumber(31));
		assertEquals("PMN", Party.parseNumber(33));
		assertEquals("PTC", Party.parseNumber(36));
		assertEquals("PSB", Party.parseNumber(40));
		assertEquals("PV", Party.parseNumber(43));
		assertEquals("PRP", Party.parseNumber(44));
		assertEquals("PSDB", Party.parseNumber(45));
		assertEquals("PSOL", Party.parseNumber(50));
		assertEquals("PPL", Party.parseNumber(54));
		assertEquals("PSD", Party.parseNumber(55));
		assertEquals("PCdoB", Party.parseNumber(65));
		assertEquals("PTdoB", Party.parseNumber(70));
		assertEquals("SDD", Party.parseNumber(77));
		assertEquals("PROS", Party.parseNumber(90));		
	}

}
