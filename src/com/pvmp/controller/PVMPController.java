/**
* @file PVMPController.java
* @brief
*/
package com.pvmp.controller;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.pvmp.dao.PersistenceHelper;
import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.User;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.util.Util;
import com.pvmp.models.PVMPmodel;

/**
* @class PVMPController
* @brief
*/
public class PVMPController implements ControllerInterface
{
	ViewObserverInterface view;
	ModelSubjectInterface model;
	PersistenceHelper persistenceHelper;

	public PVMPController()
	{
	}

	/**
	* @param _context
	* @brief 
	*/
	public PVMPController(Context _context)
	{
		this.model = new PVMPmodel(_context);
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
		this.model = _model;
	}

	@Override
	public void openApplication()
	{
		User user = this.model.getUser("default_user","S");
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
	@Override
	public void registerUser(User _user)
	{
		Util.debug("PVMPController: Prepare for register new user.");
		try {
			this.model.saveUser(_user);
		}catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
		}

		return;
	}
	public void callDisplayFragment (int fragmentIndex)
	{
		Util.debug("PVMPController: User saved on database, change view now");
		this.view.displayFragment(fragmentIndex);
		Util.debug("PVMPController: finish register");
	}
	
	public void editUser(User _user) {
		this.model.editUser(_user);
	}
	
	public void deleteUser(User _user) {
		this.model.removeUser(_user);
	}
	
	@Override
	public User openSession()
	{
		Util.debug("PVMPController: openSession");
		PersistenceHelper.createDatabase();
		User user = this.model.getUser("default_user","S");
		if(user == null)
		{
			Util.debug("PVMPController: getDefaultUser Problem");
		}

		return user;
	}
	
	public User verifyMatchingUserPassword (String _userName, String _password) 
	{
		User user = this.model.verifyMatchingUserPassword(_userName, _password);
		
		return user;
	}
}
