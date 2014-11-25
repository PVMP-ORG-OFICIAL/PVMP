package com.pvmp.controller.test;


import junit.framework.Assert;

import com.pvmp.controller.PropositionController;

import com.pvmp.model.Proposition;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;


public class PropositionControllerTest extends AndroidTestCase 
{
	private RenamingDelegatingContext context;
	private PropositionController propositionController;
	
	public void setUp() throws Exception 
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.propositionController = new PropositionController(this.context);
		
	}
	
	public void testColumnNameFullAndValueFull()
	{
		propositionController.getPropositions("category", "Administração Pública" );
	}
	
	
	public void testValueNull()
	{
		propositionController.getPropositions("situation", null);
	}
	

	public void testIfExceptionIsThrowed()
	{
		try 
		{
			propositionController.getPropositions(null, null);
			Assert.fail("Should've throwed a NullPointerException.");
		}
		catch (NullPointerException nullPointerException) 
		{
			
		}
	}
}
