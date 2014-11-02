/**
* @file ControllerInterface.java
* @brief 
*/
package com.pvmp.controller;

import com.pvmp.models.User;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.models.ModelSubjectInterface;

/**
* @class ControllerInterface
* @brief
*/
public interface ControllerInterface
{
	/**
	* @brief 
	*/
	public void openApplication();

	/**
	* @brief 
	*/
	public void displayListProposition();

	/**
	* @param _user
	* @brief 
	*/
	
	public void editUser(User _user);
	
	public void registerUser(User _user);
	
	public void deleteUser(User _user);

	/**
	* @return 
	* @brief 
	*/
	public User openSession();

	/**
	* @return _model
	* @brief 
	*/
	public void setModel(ModelSubjectInterface _model);

	/**
	* @param _view
	* @brief
	*/
	public void setView(ViewObserverInterface _view);
}
