/**
* @file PVMPController.java
* @brief
*/
package com.pvmp.controller;

import android.content.Context;

import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.User;
import com.pvmp.view.ViewObserverInterface;

/**
* @class PVMPController
* @brief
*/
public class PVMPController implements ControllerInterface
{
	private ViewObserverInterface view;
	private UserController userController;

	public PVMPController()
	{
	}

	/**
	* @param _context
	* @brief 
	*/
	public PVMPController(Context _context)
	{
		this.userController = new UserController(_context);
	}

	/**
	* @param _view
	* @brief 
	*/
	public void setView(ViewObserverInterface _view)
	{
		this.view = _view;
	}

	/**
	* @param _model
	* @brief 
	*/
	public void setModel(ModelSubjectInterface _model)
	{
		//this.model = _model;
	}

	@Override
	public void openApplication()
	{
		User user = this.userController.getUser("default_user","S");

		//Verify if has user default
		if (user != null)
		{
			this.view.displayFragment(ViewObserverInterface.CATEGORY);
			return;
		}
		
		this.view.enableDrawer(false);
		this.view.enableScreenInteraction(false);
		this.view.displayFragment(ViewObserverInterface.LOGIN);
		
		return;
	}

	@Override
	public void displayListProposition()
	{
		return;	
	}

	//public void displayFragment();
	
	public void callDisplayFragment (int fragmentIndex)
	{
		this.view.displayFragment(fragmentIndex);
	}
	
	@Override
	public User openSession()
	{
		
		User user = this.userController.getUser("default_user","S");

		return user;
	}
}
