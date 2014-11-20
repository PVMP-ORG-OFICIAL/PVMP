package com.pvmp.controller.test;


import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.pvmp.controller.FeedbackController;
import com.pvmp.models.Feedback;
import com.pvmp.models.User;

public class FeedbackControllerTest extends AndroidTestCase 
{
	private RenamingDelegatingContext context;
	private User user;
	private FeedbackController feedbackController;
	private Feedback feedback;
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.feedbackController = new FeedbackController(this.context);
		
		this.user = new User("Joao", "juca123", "senha1234", "email", 19, "Superior", "M", "N");
		this.feedback = new Feedback();
	}
	
	public void testSaveFeedback() 
	{
		this.feedbackController.saveFeedback("l", this.user, 1);
	}
	
	public void testSelectFeedback()
	{
		this.feedbackController.saveFeedback("l", this.user, 1);
		this.feedback = this.feedbackController.selectFeedback(this.user, 1);
		
		assertNotNull(this.feedback);
	}

	public void testEditFeedback() {
		this.feedbackController.saveFeedback("l", this.user, 1);
		this.feedbackController.editFeedback("d", this.user, 1);
		this.feedback = this.feedbackController.selectFeedback(this.user, 1);
		assertEquals("d", this.feedback.getOpinion());
	}

	public void testDeleteFeedback() 
	{
		this.feedbackController.deleteFeedback(this.user, 1);
		
		this.feedback = this.feedbackController.selectFeedback(this.user, 1);
		
		assertNull(this.feedback);
	}

}
