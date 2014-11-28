package com.pvmp.controller.test;

import java.util.ArrayList;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.pvmp.controller.VotingController;
import com.pvmp.model.Proposition;

public class TestVotingController extends AndroidTestCase {
	private VotingController votingController;
	private RenamingDelegatingContext context;
	private ArrayList<Proposition> propositions;
	
	public void setUp() throws Exception 
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.votingController = new VotingController(this.context);
		
		this.propositions = new ArrayList<Proposition>();
	}
	
	public void testGetPropositionsVotings () {
		Proposition p1 = new Proposition();
		p1.setId(14245);
		this.propositions.add(p1);
		Proposition p2 = new Proposition();
		p2.setId(19319);
		this.propositions.add(p2);
		Proposition p3 = new Proposition();
		p3.setId(28376);
		this.propositions.add(p3);
		
		this.votingController.getPropositionsVotings(propositions);
		
		for (int i = 0; i < propositions.size(); i++) {
			assertNotNull(propositions.get(i).getVoting());
		}
	}
	
	public void testIfGetPropositionsVotingsThrowsException () {
		try 
		{
			this.votingController.getPropositionsVotings(null);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
}
