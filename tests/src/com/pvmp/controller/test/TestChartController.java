package com.pvmp.controller.test;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.pvmp.controller.ChartController;
import com.pvmp.model.Vote;

public class TestChartController extends AndroidTestCase 
{	
	private RenamingDelegatingContext context;
	private ChartController chartController;
	private ArrayList<Vote> votes;
	private Vote voteYes;
	private Vote voteNo;

	protected void setUp() throws Exception {
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();

		this.chartController = new ChartController(this.context);
		this.voteYes = new Vote();
		this.voteNo = new Vote();
		
		voteYes.setResult(ChartController.YES_VOTES);
		voteNo.setResult(ChartController.NO_VOTES);
		
		votes.add(voteYes);
		votes.add(voteNo);
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
		votes.add(voteYes);
		votes.add(voteNo);
		
		assertEquals(1, (chartController.selectVotesByType(votes, ChartController.YES_VOTES)).size());
	}

	public void testCalculateVotesResultPercentage() 
	{
		float percent = 50f;
		
		assertEquals(percent, (chartController.calculateVotesResultPercentage(votes)).get(0));
	}

	public void testCalculatePartiesResultPercentage() 
	{
		
	}

	public void testPrepareGraphicData() 
	{
		fail("Not yet implemented");
	}

}
