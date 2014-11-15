package com.pvmp.controller;

import java.util.ArrayList;

import com.pvmp.dao.Criteria;
import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Expression;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.models.Feedback;
import com.pvmp.models.User;
import com.pvmp.util.MessageHandling;
import com.pvmp.util.Util;

import android.content.Context;
import android.database.sqlite.SQLiteException;

public class FeedbackController {
	private Context context;
	
	public FeedbackController (Context _context) 
	{
		this.context = _context;
	}
	
	/**
	 * @param _opinion
	 * @param _user
	 * @param _target
	 * @brief Save a feedback (liked, disliked or clownery), the user who gave it and the target
	 * 		  (a Proposition or a Party).
	 * */
	public void saveFeedback (String _opinion, User _user, int _target) 
	{
		if (_opinion == null || _user == null) {
			throw new NullPointerException("Null pointer at FeedbackController.saveFeedback");
		}
		Feedback feedbackToBeSaved = new Feedback(_opinion, _user, _target);
		
		try 
		{
			feedbackToBeSaved.insertDB(this.context);
		}
		catch (SQLiteException sqlE) {
			Util.debug("SQLiteException em FeedbackController.saveFeedback");
			MessageHandling.showToast("Erro ao salvar o feedback no banco de dados!", this.context);
		}
	}
	
	/**
	 * @param _opinion
	 * @param _user
	 * @param _target
	 * @brief Edit (or Update, whatever) an already existing feedback.
	 * */
	public void editFeedback (String _opinion, User _user, int _target) 
	{
		if (_opinion == null || _user == null) 
		{
			throw new NullPointerException("Null pointer at FeedbackController.editFeedback");
		}
		Feedback feedbackToBeEdited = new Feedback(_opinion, _user, _target);

		Criteria criteriaExpression = new Criteria();
		criteriaExpression = generateFeedbackDatabaseIdentifier(_user, _target);
		
		try 
		{
			feedbackToBeEdited.updateDB(criteriaExpression, this.context);
		}
		catch (SQLiteException sqlE) {
			Util.debug("SQLiteException em FeedbackController.editFeedback");
			MessageHandling.showToast("Erro ao editar o feedback no banco de dados!", this.context);
		}
	}
	
	/**
	 * @param _user
	 * @param _target
	 * @brief Delete a feedback based on its primary key (_user's username and _target).
	 * */
	public void deleteFeedback (User _user, int _target) 
	{
		if (_user == null) 
		{
			throw new NullPointerException("Null pointer at FeedbackController.deleteFeedback");
		}
		Feedback feedbackToBeDeleted = new Feedback();
		
		Criteria criteriaExpression = new Criteria();
		criteriaExpression = generateFeedbackDatabaseIdentifier(_user, _target);
		
		try 
		{
			feedbackToBeDeleted.deleteDB(criteriaExpression, this.context);
		}
		catch (SQLiteException sqlE) {
			Util.debug("SQLiteException em FeedbackController.deleteFeedback");
			MessageHandling.showToast("Erro ao deletar o feedback do banco de dados!", this.context);
		}
	}
	
	/**
	 * @param _user
	 * @param _target
	 * @brief Generate a default Criteria that identifies uniquely a Feedback at database searches.
	 * */
	public Criteria generateFeedbackDatabaseIdentifier (User _user, int _target) {
		if (_user == null)
		{
			throw new NullPointerException("Null pointer at FeedbackController.deleteFeedback");
		}
		
		Filter userFilter = new Filter("user_name", "=");
		userFilter.setValue(_user.getUsername());
		
		Filter targetFilter = new Filter("id_prop", "=");
		targetFilter.setValue(_target);
		
		Criteria filtersJoiner = new Criteria();
		filtersJoiner.add(userFilter, Expression.AND_OPERATOR);
		filtersJoiner.add(targetFilter, Expression.AND_OPERATOR);
		
		return filtersJoiner;
	}
	
	public Feedback selectFeedback(User _user, int _target)
	{
		
		if (_user == null)
		{
			throw new NullPointerException("Null pointer at FeedbackController.selectFeedback");
		}
		ArrayList<DAOAbstract> feedbacks = new ArrayList<DAOAbstract>();
		Feedback feedback = new Feedback();
		
		Criteria criteria = new Criteria();
		criteria = this.generateFeedbackDatabaseIdentifier(_user, _target);
		
		SqlSelect expression = new SqlSelect();
		expression.addColumn(Feedback.COLUMN_OPINION);
		expression.addEntity(feedback.TABLE_NAME);
		expression.setExpression(criteria);
		
		feedbacks = feedback.selectDB(expression, this.context);
		if (feedbacks.size() == 0)
		{
			return null;
		}

		feedback = (Feedback) feedbacks.get(0);
		
		return feedback;
	}
	
}
