package com.pvmp.controller.test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import com.pvmp.controller.DeputyController;
import com.pvmp.model.Deputy;
import com.pvmp.model.Vote;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class TestDeputyController extends AndroidTestCase 
{

	private RenamingDelegatingContext context;
	private DeputyController deputyController;
	private Deputy deputy1, deputy2;
	private Vote vote1, vote2;
	private ArrayList<Vote> votes;
	private ArrayList<Deputy> deputies;
	
	@Override
	public void setUp() throws Exception 
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.deputyController = new DeputyController(this.context);
		
		this.startAtributes();
		
	}
	
	public void startAtributes()
	{
		this.vote1 = new Vote();
		this.vote2 = new Vote();
		
		this.deputy1 = new Deputy();
		this.deputy2 = new Deputy();
		
		this.votes = new ArrayList<Vote>();
		
		this.deputy1.setIdRegistration(141417);
		this.deputy2.setIdRegistration(-1);
		
		this.vote1.setResult("Não");
		this.vote1.setDeputy(this.deputy1);
		this.vote2.setDeputy(this.deputy2);
		
		this.votes.add(this.vote1);
	}

	public void testGetDeputiesThatVotedOnSession() 
	{
		this.deputies = this.deputyController.getDeputiesThatVotedOnSession(votes);
		assertEquals(this.deputies.size(), this.votes.size());
		assertEquals(this.deputies.get(0).getIdRegistration(), this.votes.get(0).getDeputy().getIdRegistration());
	}
	
	public void testIfGetDeputiesThrowsException () {
		try 
		{
			ArrayList<Vote> nullValue = null;
			this.deputyController.getDeputiesThatVotedOnSession(nullValue);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NullPointerException npE) 
		{
			//passed
		}
	}
	
	public void testGetDeputiesThrowsNoSuchElementException () {
		try 
		{
			ArrayList<Vote> wrongArray = new ArrayList<Vote>();
			wrongArray.add(this.vote2);
			
			this.deputyController.getDeputiesThatVotedOnSession(wrongArray);
			Assert.fail("A NullPointerException should have been throwed.");
		}
		catch (NoSuchElementException npE) 
		{
			//passed
		}
	}

}
