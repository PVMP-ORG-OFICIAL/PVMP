package com.pvmp.controller;

import java.io.Serializable;

import com.pvmp.models.User;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.models.ModelSubjectInterface;

public interface ControllerInterface extends Serializable
{
	public void openApplication();
	public void displayListProposition();
	public void registerUser(User _user);
	public User openSession();
	public void setModel(ModelSubjectInterface _model);
	public void setView(ViewObserverInterface _view);
}
