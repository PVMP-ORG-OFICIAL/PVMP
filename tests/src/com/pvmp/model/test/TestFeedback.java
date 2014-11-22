package com.pvmp.model.test;

import android.content.ContentValues;

import com.pvmp.models.Feedback;
import com.pvmp.models.User;

import junit.framework.TestCase;

public class TestFeedback extends TestCase {
	
	private Feedback feedbackEmpty;
	private Feedback feedbackFull;
	private User user;
	private User user2;
	
	public void setUp() throws Exception {
		super.setUp();
		
		this.user = new User("Joao", "juca123", "senha1234", "email", 19, "Superior", "M", "N");
		this.user2 = new User("Maria", "maria123", "mariasenha", "emailmaria", 21, "Medio", "F", "N");
		this.feedbackEmpty = new Feedback();
		this.feedbackFull = new Feedback("d", this.user, 1);
	}

	public void testContentValuesToModel() {
		ContentValues cValues = new ContentValues();
		
		cValues.put(Feedback.COLUMN_OPINION, this.feedbackFull.getOpinion());
		cValues.put(Feedback.COLUMN_USERNAME, this.feedbackFull.getUser().getUsername());
		cValues.put(Feedback.COLUMN_PROPOSITION, this.feedbackFull.getTarget());
		
		assertNotNull(this.feedbackFull.contentValuesToModel(cValues));
	}

	public void testGetOpinion() {
		assertEquals("d", this.feedbackFull.getOpinion());
	}

	public void testSetOpinion() {
		this.feedbackEmpty.setOpinion("c");
		assertEquals("c", this.feedbackEmpty.getOpinion());
	}

	public void testGetUser() {
		assertSame(this.user, this.feedbackFull.getUser());
	}

	public void testSetUser() {
		this.feedbackEmpty.setUser(this.user2);
		assertSame(this.user2, this.feedbackEmpty.getUser());
	}

	public void testGetTarget() {
		assertEquals(1, this.feedbackFull.getTarget());
	}

	public void testSetTarget() {
		this.feedbackEmpty.setTarget(4);
		assertEquals(4, this.feedbackEmpty.getTarget());
	}

}
