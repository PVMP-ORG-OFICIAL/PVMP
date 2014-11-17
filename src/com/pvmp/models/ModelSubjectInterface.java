/**
* @file ModelSubjectInterface.java
* @brief
*/
package com.pvmp.models;

import android.content.Context;

import com.pvmp.models.User;

/**
* @class ModelSubjectInterface
* @brief 
*/
public interface ModelSubjectInterface
{
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
