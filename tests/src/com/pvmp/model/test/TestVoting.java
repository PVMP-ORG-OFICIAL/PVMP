package com.pvmp.model.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import android.content.ContentValues;

import com.pvmp.model.Proposition;
import com.pvmp.model.Vote;
import com.pvmp.model.Voting;

public class TestVoting extends TestCase {
	
	private Voting voting;
	private Proposition proposition;
	private Vote vote1;
	private Vote vote2;
	private Vote vote3;
	private ArrayList<Vote> votes;

	public TestVoting(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		votes = new ArrayList<Vote>();
		voting = new Voting();
		proposition = new Proposition();
		vote1 = new Vote();
		vote2 = new Vote();
		vote3 = new Vote();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerateContentValues() {
		assertNull(voting.generateContentValues());
	}

	public void testVoting() {
		assertNotNull(voting);
	}
	
	public void testSetProposition() {
		proposition.setAcronym("Fala de...");
		voting.setProposition(proposition);
		
		assertEquals(voting.getProposition().getAcronym(), "Fala de...");
	}

	public void testGetProposition() {
		proposition.setYear("01/01/1990");
		voting.setProposition(proposition);
		
		assertEquals(voting.getProposition().getYear(), "01/01/1990");
	}
	
	public void testSetSummary() {
		voting.setSummary("Sumário");
		
		assertEquals(voting.getSummary(), "Sumário");
	}

	public void testGetSummary() {
		voting.setSummary("Sumário da votação");
		
		assertEquals(voting.getSummary(), "Sumário da votação");
	}
	
	public void testSetDate() {
		voting.setDate("02/02/2000");
		
		assertEquals(voting.getDate(), "02/02/2000");
	}

	public void testGetDate() {
		voting.setDate("01/01/2010");
		
		assertEquals(voting.getDate(), "01/01/2010");
	}

	public void testSetVotes() {
		votes.add(vote1);
		votes.add(vote2);
		votes.add(vote3);
		
		voting.setVotes(votes);
		
		assertNotNull(voting.getVotes());
		assertEquals(voting.getVotes().size(), 3);
		assertNotSame(voting.getVotes().get(0), voting.getVotes().get(1));
	}

	public void testGetVotes() {
		votes.add(vote1);
		votes.add(vote2);
		votes.add(vote3);
		
		voting.setVotes(votes);
		
		assertNotNull(voting.getVotes());
		assertEquals(voting.getVotes().size(), 3);
		assertNotSame(voting.getVotes().get(0), voting.getVotes().get(1));
	}
	
	public void testSetCodeSession() {
		voting.setCodeSession(01);
		
		assertEquals(voting.getCodeSession(), (Integer) 01);
	}

	public void testGetCodeSession() {
		voting.setCodeSession(13);
		
		assertEquals(voting.getCodeSession(), (Integer) 13);
	}

	public void testContentValuesToModel() {
		voting.setProposition(proposition);
		voting.setSummary("Sumário da votação");
		voting.setDate("02/03/2014");
		voting.setVotes(votes);
		
		ContentValues cValues = new ContentValues();
		
		cValues.put(Voting.COLUMN_ID_PROP, voting.getProposition().getId());
		cValues.put(Voting.COLUMN_SUMMARY, voting.getSummary());
		cValues.put(Voting.COLUMN_DATE, voting.getDate());
		cValues.put(Voting.COLUMN_CODE_SESSION, voting.getCodeSession());
		
		assertNotNull(voting.contentValuesToModel(cValues));
	}
}
