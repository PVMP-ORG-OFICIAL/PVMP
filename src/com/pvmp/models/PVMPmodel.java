/**
* @file PVMPModel.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
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
		User user = userDAO.selectByDefault("S");
		Util.debug("PVMPmodel: After access dao");
		return user;
	}

	/**
	* @param _userName
	* @return 
	* @brief 
	*/
	public User getUser(String _userName)
	{
		User user = userDAO.selectByUsername(_userName);
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

			userDAO.save(_user);

			return;
	}

	/**
	* @param _user
	* @brief 
	*/
	public void removeUser(User _user)
	{
		userDAO.delete(_user);
		return;
	}

	/**
	* @param _user
	* @brief
	*/
	public void editUser(User _user)
	{
		userDAO.edit(_user);

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
