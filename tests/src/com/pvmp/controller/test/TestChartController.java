package com.pvmp.controller.test;

import java.util.ArrayList;

import junit.framework.Assert;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.pvmp.controller.ChartController;

import com.pvmp.model.Deputy;
import com.pvmp.model.Party;
import com.pvmp.model.Proposition;
import com.pvmp.model.Vote;
import com.pvmp.model.Voting;

public class TestChartController extends AndroidTestCase 
{	
	private RenamingDelegatingContext context;
	private ChartController chartController;
	private ArrayList<Deputy> deputies;
	private ArrayList<Vote> votesTest, votesYes, votesNo;
	private Vote voteYes, voteNo;
	
	private Vote vote1, vote2;
	private Deputy deputy1, deputy2;
	private Voting voting1, voting2;
	private Party party1, party2;
	private Proposition proposition1, proposition2;

	protected void setUp() throws Exception
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();

		this.votesTest = new ArrayList<Vote>();
		this.votesYes = new ArrayList<Vote>();
		this.votesNo = new ArrayList<Vote>();
		this.chartController = new ChartController(this.context);
		this.voteYes = new Vote();
		this.voteNo = new Vote();
		
		voteYes.setResult(ChartController.YES_VOTES);		
		voteNo.setResult(ChartController.NO_VOTES);
		
		
		deputies = new ArrayList<Deputy>();
		vote1 = new Vote();
		deputy1 = new Deputy();
		voting1 = new Voting();
		party1 = new Party();
		proposition1 = new Proposition();
		
		vote2 = new Vote();
		deputy2 = new Deputy();
		voting2 = new Voting();
		party2 = new Party();
		proposition2 = new Proposition();
		
		vote1.setResult("Sim");
		vote1.setDeputy(deputy1);
		vote1.setVoting(voting1);

		vote2.setResult("Não");
		vote2.setDeputy(deputy2);
		vote2.setVoting(voting2);
		
		deputy1.setName("Valdir Colatto");
		deputy1.setFederativeUnit("SC");
		deputy1.setIdRegistration(74010);
		deputy1.setParty(party1);
		deputy1.setVotes(votesTest);

		deputy2.setName("Thiago Peixoto");
		deputy2.setFederativeUnit("GO");
		deputy2.setIdRegistration(160607);
		deputy2.setParty(party2);
		deputy2.setVotes(votesTest);
		
		voting1.setProposition(proposition1);
		voting1.setSummary("Rejeitado o Requerimento. Sim: 34; não: 228; abstenção: 1; total: 263;");
		voting1.setDate("20/02/2013");
		voting1.setVotes(votesTest);

		voting2.setProposition(proposition2);
		voting2.setSummary("Aprovada a Emenda nº 1. Sim: 192; não: 179; abstenção: 1; total: 372;");
		voting2.setDate("20/03/2013");
		voting2.setVotes(votesTest);
		
		party1.setNumber(15);
		party1.setAcronym("PMDB");
		party1.setDeputies(deputies);

		party2.setNumber(55);
		party2.setAcronym("PSD");
		party2.setDeputies(deputies);
		
		proposition1.setId(5563);
		proposition1.setYear("2012");
		proposition1.setAcronym("MPV");
		proposition1.setAuthor("Poder Executivo");
		proposition1.setSituation("Urgência");
		proposition1.setNumber(582);
		proposition1.setCategory("Tributação");
		proposition1.setVoting(voting1);

		proposition2.setId(564113);
		proposition2.setYear("2012");
		proposition2.setAcronym("PL");
		proposition2.setAuthor("Poder Executivo");
		proposition2.setSituation("Transformado em Norma Jurídica");
		proposition2.setNumber(4904);
		proposition2.setCategory("Administração Pública");
		proposition2.setVoting(voting2);
		
		votesTest.add(vote1);
		votesTest.add(vote2);
		
		votesYes.add(vote1);
		votesNo.add(vote2);
		
		deputies.add(deputy1);
		deputies.add(deputy2);
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	public void testChartController() 
	{
		assertNotNull(chartController);
	}

	public void testSelectVotesByType() 
	{
		votesTest.add(voteYes);
		votesTest.add(voteNo);
		
		assertEquals(2, (chartController.selectVotesByType(votesTest, ChartController.YES_VOTES)).size());
	}

	public void testCalculateVotesResultPercentage() 
	{
		float percent = 50f;
		
		votesTest.add(voteYes);
		votesTest.add(voteNo);
		
		assertEquals(percent, (chartController.calculateVotesResultPercentage(votesTest)).get(0));
	}
	
	public void testCalulateVotesResultPercentageException()
	{
		try
		{
			ArrayList<Vote> nullValue = null;
			chartController.calculateVotesResultPercentage(nullValue);
			Assert.fail("Null pointer at PVMPController.calculateVotes().");
		}
		catch (NullPointerException npE)
		{
			// passed
		}
	}

	public void testCalculatePartiesResultPercentage() 
	{
		float percent = 100f;
		
		assertEquals(percent, (chartController.calculatePartiesResultPercentage(votesYes, ChartController.YES_VOTES).get(0)));
	}

	/*
	public void testPrepareGraphicData() 
	{
		fail("Not yet implemented");
	}
	*/
}
