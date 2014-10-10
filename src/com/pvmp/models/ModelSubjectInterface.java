package com.pvmp.models;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import com.pvmp.models.User;
import com.pvmp.models.Proposition;

public interface ModelSubjectInterface extends Serializable
{
	public User getDefaultUser();
	public User getUser(String _userName);
	public void saveUser(User _user);
	public void removeUser(User _userName);
	public void editUser(User _user);

	public ArrayList<Proposition> getListPreposition(Date _dataStart, int _totalSearch);

	public void registerObserver(ListenerObserverInterface _observer);
	public void removeObserver(ListenerObserverInterface _observer);
}
