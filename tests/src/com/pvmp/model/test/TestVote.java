package com.pvmp.model.test;

import junit.framework.TestCase;

import android.content.ContentValues;

import com.pvmp.model.Deputy;
import com.pvmp.model.Proposition;
import com.pvmp.model.Vote;
import com.pvmp.model.Voting;

public class TestVote extends TestCase {
	
	private Vote vote;
	private Deputy deputy;
	private Voting voting;
	private Proposition proposition;

	public TestVote(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		proposition = new Proposition();
		deputy = new Deputy();
		voting = new Voting();
		vote = new Vote();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerateContentValues() {
		assertNull(vote.generateContentValues());
	}

	public void testVote() {
		assertNotNull(vote);		
	}
	
	public void testSetResult() {
		vote.setResult("Aprovada");
		
		assertEquals(vote.getResult(), "Aprovada");
	}

	public void testGetResult() {
		vote.setResult("Rejeitada");
		
		assertEquals(vote.getResult(), "Rejeitada");
	}
	
	public void testSetDeputy() {
		deputy.setName("David");
		vote.setDeputy(deputy);
		
		assertEquals(vote.getDeputy().getName(), "David");
	}

	public void testGetDeputy() {
		deputy.setName("Rodrigo");
		vote.setDeputy(deputy);
		
		assertEquals(vote.getDeputy().getName(), "Rodrigo");
	}
	
	public void testSetVoting() {
		proposition.setId(99);
		voting.setProposition(proposition);
		vote.setVoting(voting);
		
		assertEquals(vote.getVoting().getProposition().getId(), (Integer) 99);
	}

	public void testGetVoting() {
		proposition.setAcronym("Emenda à Constituição");
		voting.setProposition(proposition);
		vote.setVoting(voting);
		
		assertEquals(vote.getVoting().getProposition().getAcronym(), "Emenda à Constituição");
	}

	public void testContentValuesToModelContentValues() {
		vote.setResult("Resultado");
		vote.setDeputy(deputy);
		vote.setVoting(voting);
		
		ContentValues cValues = new ContentValues();
		
		cValues.put(Vote.COLUMN_VOTE_RESULT, vote.getResult());
		cValues.put(Vote.COLUMN_ID_REGISTRATION, vote.getDeputy().getIdRegistration());
		cValues.put(Vote.COLUMN_CODE_SESSION, vote.getVoting().getCodeSession());
		
		assertNotNull(vote.contentValuesToModel(cValues));
	}

}
