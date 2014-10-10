package com.pvmp.controller;

import java.io.Serializable;

import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.User;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.view.PVMPView;
import com.pvmp.util.Util;

public class PVMPController implements ControllerInterface
{
	private static final long serialVersionUID = 1577583808128983752L;
	
	ViewObserverInterface view;
	ModelSubjectInterface model;

	public PVMPController()
	{
	}

	public PVMPController(ModelSubjectInterface _model)
	{
	}

	public void setView(ViewObserverInterface _view)
	{
		this.view = _view;
	}

	public void setModel(ModelSubjectInterface _model)
	{
		this.model = _model;
	}

	@Override
	public void openApplication()
	{
			User user = this.model.getDefaultUser();
			
			//Verify if has user default
			if (user != null)
			{
				Util.debug("PVMPController: go to HOME");
				this.view.displayFragment(ViewObserverInterface.HOME);
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
		this.model.saveUser(_user);
		this.view.enableDrawer(true);
		this.view.enableScreenInteraction(true);
		this.view.displayFragment(ViewObserverInterface.HOME);
	
		return;
	}

	@Override
	public User openSession()
	{
		Util.debug("PVMPController: openSession");
		User user = this.model.getDefaultUser();

		return user;
	}
}
