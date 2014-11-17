/**
* @file PVMPController.java
* @brief
*/
package com.pvmp.controller;

import android.content.Context;

import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.User;
import com.pvmp.util.Util;
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
		Util.debug("PVMPController: start openApplication.");

		//Verify if has user default
		if (user != null)
		{
			Util.debug("PVMPController: go to HOME");
			this.view.displayFragment(ViewObserverInterface.CATEGORY);
			return;
		}
		
		Util.debug("PVMPController: go to LOGIN");
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
		Util.debug("PVMPController: User saved on database, change view now");
		this.view.displayFragment(fragmentIndex);
		Util.debug("PVMPController: finish register");
	}
	
	@Override
	public User openSession()
	{
		Util.debug("PVMPController: openSession");
		
		User user = this.userController.getUser("default_user","S");
		
		if(user == null)
		{
			Util.debug("PVMPController: getDefaultUser Problem");
		}

		return user;
	}
}
