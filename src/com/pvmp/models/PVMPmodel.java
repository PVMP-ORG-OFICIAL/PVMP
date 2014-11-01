/**
* @file PVMPModel.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.SQLException;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.dao.UserDAO;
import com.pvmp.util.Util;

/**
* @class PVMPModel
* @brief 
*/
public class PVMPmodel implements ModelSubjectInterface
{
	static UserDAO userDAO; /**< */
	ArrayList<ListenerObserverInterface> observers;
	Context context;

	/**
	* @param _context
	* @brief 
	*/
	public PVMPmodel(Context _context)
	{
		this.context = _context;
		//must change to the new DAO
		userDAO = UserDAO.getInstance(this.context);
		this.observers = new ArrayList<ListenerObserverInterface>();
	}

	public void setContext(Context _context)
	{
		this.context = _context;
		return;
	}
	
	/**
	* @return 
	* @brief
	*/
	public User getDefaultUser()
	{
		Util.debug("PVMPmodel: Try to get default user from database");
		//must change to the new DAO
		User user = userDAO.selectByDefault("S");
		Util.debug("PVMPmodel: After access dao");
		return user;
	}

	/**
	* @param _userName
	* @return 
	* @brief 
	*/
	public User getUser(String _columnName, String _userName)
	{
		if (_userName == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getUser()");
		}
		
		SqlSelect selectExpression = new SqlSelect();
		ArrayList<DAOAbstract> users = new ArrayList<DAOAbstract>();
		
		Filter usernameFilter = new Filter(_columnName, "=");
		usernameFilter.setValue(_userName);
		
		User user = new User();
		
		selectExpression.setEntity(user.TABLE_NAME);
		
		users = user.selectDB(selectExpression, this.context);
		
		if (users.size() == 0)
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

	/**
	* @param _dataStart
	* @param _totalSearch
	* @return
	* @brief
	*/
	public ArrayList<Proposition> getListPreposition(Date _dataStart, int _totalSearch)
	{
		return null;		
	}

	/**
	* @param _observer
	* @brief
	*/
	public void registerObserver(ListenerObserverInterface _observer)
	{
		this.observers.add(_observer);

		return;
	}

	/**
	* @param _observer
	* @brief
	*/
	public void removeObserver(ListenerObserverInterface _observer)
	{
		 return;
	}
}
