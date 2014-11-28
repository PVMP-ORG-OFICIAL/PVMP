package com.pvmp.controller.test;

import java.util.ArrayList;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.pvmp.controller.VoteController;
import com.pvmp.model.Vote;
import com.pvmp.model.Voting;

public class TestVoteController extends AndroidTestCase 
{
	private VoteController voteController;
	private Voting voting;
	private RenamingDelegatingContext context;
	
	public void setUp() throws Exception 
	{
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.voteController = new VoteController(this.context);
		this.voting = new Voting();
	}
	
	public void testGetVotingVotes () 
	{
		ArrayList<Vote> votes = new ArrayList<Vote>();
		this.voting.setCodeSession(718);
		
		voteController.getVotingVotes(voting);
		votes = voting.getVotes();
		
		assertFalse(votes.size() == 0);
	}
	
	public void testIfGetVotingVotesThrowsException () 
	{
		try 
		{
			voteController.getVotingVotes(null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
}
