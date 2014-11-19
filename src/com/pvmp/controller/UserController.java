package com.pvmp.controller;

import java.util.ArrayList;

import android.content.Context;
import android.database.SQLException;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.models.User;

public class UserController extends AbstractController
{
	public UserController (Context _context)
	{
		super(_context);
	}
	
	/**
	* @param _userName
	* @return 
	* @brief 
	*/
	public User getUser(String _columnName, String _attribute)
	{
		if (_attribute == null || _columnName == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getUser()");
		}
		
		SqlSelect selectExpression = new SqlSelect();
		ArrayList<DAOAbstract> users = new ArrayList<DAOAbstract>();
		
		Filter usernameFilter = new Filter(_columnName, "=");
		usernameFilter.setValue(_attribute);
		
		User user = new User();
		
		selectExpression.addEntity(user.TABLE_NAME);
		selectExpression.setExpression(usernameFilter);
		
		users = user.selectDB(selectExpression, this.context);
		
		if(users.size() == 0)
			user = null;
		else
			user = (User) users.get(0);
		
		return user;
	}
	
	/**
	* @param _user
	* @brief
	*/
	public void saveUser(User _user)
	{
		if(_user == null)
		{
			return;
		}
		
		try {
			_user.insertDB(this.context);
		}
		catch (SQLException sqlE) {
			sqlE.printStackTrace();
			throw new SQLException();
		}
		return;
	}

	/**
	* @param _user
	* @brief 
	*/
	public void removeUser(User _user)
	{
		if(_user == null) 
		{
			return;
		}
		
		Filter deleteFilter = new Filter(User.COLUMN_USERNAME, "=");
		deleteFilter.setValue(_user.getUsername());
		
		_user.deleteDB(deleteFilter, this.context);
			
		return;
	}

	/**
	* @param _user
	* @brief
	*/
	public void editUser(User _user)
	{
		if(_user == null)
		{
			return;
		}
		
		Filter editFilter = new Filter(User.COLUMN_USERNAME, "=");
		editFilter.setValue(_user.getUsername());
		
		_user.updateDB(editFilter, this.context);

		return;
	}
	
	public User verifyMatchingUserPassword (String _userName, String _password) 
	{
		User user = this.getUser("user_name", _userName);
		
		if (user != null) {
			if(!_password.equals(user.getPassword())) {
				user = null;
			}
		}
		return user;
	}	
	
	/**
	* @param _email
	* @param _context
	* @return 
	* @brief 
	*/
	public boolean validateExistingEmail (String _email)
	{
		User user = new User();
		user = this.getUser("email", _email);
		
		if (user == null)
		{
			return true;
		}

		return false;
	}
	
	/**
	* @param _username
	* @param _context
	* @return
	* @brief 
	*/
	public boolean validateExistingUser (String _username)
	{
		User user = new User();
		user = this.getUser("user_name", _username);
		
		if (user == null)
		{
			return true;
		}
		
		return false;
	}
}
