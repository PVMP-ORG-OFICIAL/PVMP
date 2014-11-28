/**
* @file ControllerInterface.java
* @brief 
*/
package com.pvmp.controller;

import com.pvmp.model.User;
import com.pvmp.view.ViewObserverInterface;

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
	* @return 
	* @brief 
	*/
	public User openSession();

	/**
	* @param _view
	* @brief
	*/
	public void setView(ViewObserverInterface _view);
}
