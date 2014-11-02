/**
* @file ModelSubjectInterface.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;

import com.pvmp.models.User;
import com.pvmp.models.Proposition;

/**
* @class ModelSubjectInterface
* @brief 
*/
public interface ModelSubjectInterface
{
	/**
	* @return 
	* @brief
	*/
	public User getDefaultUser();

	/**
	* @param _userName
	* @return 
	* @brief
	*/
	public User getUser(String _columnName, String _userName);

	/**
	* @param _user
	* @brief
	*/
	public void saveUser(User _user);

	/**
	* @param _userName
	* @brief
	*/
	public void removeUser(User _userName);

	/**
	* @param _user
	* @brief
	*/
	public void editUser(User _user);

	/**
	* @param _context
	* @brief
	*/
	public void setContext(Context _context);

	/**
	* @param _dataStart
	* @param _totalSearch
	* @return
	* @brief
	*/
	public ArrayList<Proposition> getListPreposition(Date _dataStart, int _totalSearch);

	/**
	* @param _observer
	* @brief
	*/
	public void registerObserver(ListenerObserverInterface _observer);

	/**
	* @param _observer
	* @brief 
	*/
	public void removeObserver(ListenerObserverInterface _observer);
}
