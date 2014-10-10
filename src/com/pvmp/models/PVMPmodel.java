package com.pvmp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import com.pvmp.dao.UserDAO;

public class PVMPmodel implements ModelSubjectInterface
{
	private static final long serialVersionUID = -8129374578555348548L;
	
	static UserDAO userDAO;
	ArrayList<ListenerObserverInterface> observers;

	public PVMPmodel(Context _context)
	{
		this.userDAO = UserDAO.getInstance(_context);
		this.observers = new ArrayList<ListenerObserverInterface>();
	}

	public User getDefaultUser()
	{
		User user = this.userDAO.selectByDefault("S");
		return user;
	}

	public User getUser(String _userName)
	{
		User user = this.userDAO.selectByUsername(_userName);
		return user;
	}

	public void saveUser(User _user)
	{
			if(_user == null)
			{
				return;
			}

			this.userDAO.save(_user);
	}

	public void removeUser(User _user)
	{
		this.userDAO.delete(_user);
		return;
	}

	public void editUser(User _user)
	{
		this.userDAO.edit(_user);
		return;
	}

	public ArrayList<Proposition> getListPreposition(Date _dataStart, int _totalSearch)
	{
		return null;		
	}

	public void registerObserver(ListenerObserverInterface _observer)
	{
		this.observers.add(_observer);

		return;
	}

	public void removeObserver(ListenerObserverInterface _observer)
	{
		 return;
	}
}
