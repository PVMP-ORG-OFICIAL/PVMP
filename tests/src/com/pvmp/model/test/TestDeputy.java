package com.pvmp.model.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import android.content.ContentValues;

import com.pvmp.model.Deputy;
import com.pvmp.model.Party;
import com.pvmp.model.Proposition;
import com.pvmp.model.Vote;
import com.pvmp.model.Voting;

public class TestDeputy extends TestCase {
	
	private Proposition proposition;
	private Deputy deputy;
	private Party party;
	private Vote vote;
	private Voting voting;
	private ArrayList<Vote> votes;
	private ArrayList<Deputy> deputies;

	public TestDeputy(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		proposition = new Proposition();
		deputy = new Deputy();
		party = new Party();
		vote = new Vote();
		voting = new Voting();
		deputies = new ArrayList<Deputy>();
		votes = new ArrayList<Vote>();
		
		deputy.setName("Jon Snow");
		deputy.setFederativeUnit("DF");
		deputy.setParty(party);
		deputy.setVotes(votes);
		
		party.setNumber(45);
		party.setAcronym("PSDB");
		party.setDeputies(deputies);
		
		vote.setResult("Aprovada");
		vote.setDeputy(deputy);
		vote.setVoting(voting);
		
		voting.setProposition(proposition);
		voting.setSummary("");
		voting.setDate("01/01/1980");
		voting.setVotes(votes);
		
		proposition.setId(10);
		proposition.setYear("02/02/1990");
		proposition.setMenu("Fala de Saúde...");
		proposition.setAcronym("Projeto de Lei");
		proposition.setAuthor("Pedro Pereira");
		proposition.setSituation("Votada");
		proposition.setNumber(24);
		proposition.setCategory("Saúde");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDeputy() {		
		votes.add(vote);
		deputies.add(deputy);
		
		assertNotNull(deputy);
	}

	public void testGenerateContentValues() {
		assertNull(deputy.generateContentValues());
	}
	
	public void testSetIdRegistration() {
		deputy.setIdRegistration(30);
		
		assertEquals(deputy.getIdRegistration(), (Integer) 30);
	}

	public void testGetIdRegistration() {
		deputy.setIdRegistration(20);
		
		assertEquals(deputy.getIdRegistration(), (Integer) 20);
	}
	
	public void testSetName() {
		deputy.setName("Joao Silva");
		
		assertEquals(deputy.getName(), "Joao Silva");
	}

	public void testGetName() {
		deputy.setName("Joao Pedro");
		
		assertEquals(deputy.getName(), "Joao Pedro");
	}
	
	public void testSetFederativeUnit() {
		deputy.setFederativeUnit("DF");
		
		assertEquals(deputy.getFederativeUnit(), "DF");
	}

	public void testGetFederativeUnit() {
		deputy.setFederativeUnit("MG");
		
		assertEquals(deputy.getFederativeUnit(), "MG");
	}
	
	public void testSetParty() {
		deputy.setParty(party);
		
		assertEquals(deputy.getParty(), party);
	}

	public void testGetParty() {
		deputy.setParty(party);
		
		assertNotNull(deputy.getParty());
	}
	
	public void testSetVotes() {
		Vote vote = new Vote();
		votes.add(vote);
		deputy.setVotes(votes);
		
		assertEquals(deputy.getVotes().get(0), vote);
	}

	public void testGetVotes() {
		Vote vote = new Vote();
		Vote vote2 = new Vote();
		
		votes.add(vote);
		votes.add(vote2);
		deputy.setVotes(votes);
		
		assertSame(deputy.getVotes().get(1), vote2);
	}

	public void testContentValuesToModel() {
		ContentValues cValues = new ContentValues();
		
		cValues.put(Deputy.COLUMN_NAME, deputy.getName());
		cValues.put(Deputy.COLUMN_FEDERATIVE_UNIT, deputy.getFederativeUnit());
		cValues.put(Deputy.COLUMN_NUMBER_PARTY, deputy.getParty().getNumber());
		cValues.put(Deputy.COLUMN_ID_REGISTRATION, deputy.getIdRegistration());
		
		assertNotNull(deputy.contentValuesToModel(cValues));
	}

}
