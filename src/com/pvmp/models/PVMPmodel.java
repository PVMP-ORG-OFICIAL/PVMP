/**
* @file PVMPModel.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.SQLException;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.PersistenceHelper;
import com.pvmp.dao.SqlSelect;
import com.pvmp.util.Util;

/**
* @class PVMPModel
* @brief 
*/
public class PVMPmodel implements ModelSubjectInterface
{
	private ArrayList<ListenerObserverInterface> observers;
	private Context context;

	/**
	* @param _context
	* @brief 
	*/
	public PVMPmodel(Context _context)
	{
		this.context = _context;
		//must change to the new DAO
		PersistenceHelper.getInstance(this.context);
		this.observers = new ArrayList<ListenerObserverInterface>();
	}

	public void setContext(Context _context)
	{
		this.context = _context;
		return;
	}

	/**
	* @param _userName
	* @return 
	* @brief 
	*/
	public User getUser(String _columnName, String _attribute)
	{
		if (_attribute == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getUser()");
		}
		
		SqlSelect selectExpression = new SqlSelect();
		ArrayList<DAOAbstract> users = new ArrayList<DAOAbstract>();
		
		Filter usernameFilter = new Filter(_columnName, "=");
		usernameFilter.setValue(_attribute);
		
		User user = new User();
		
		selectExpression.setEntity(user.TABLE_NAME);
		selectExpression.setExpression(usernameFilter);
		
		Util.debug("passou aqui");
		users = user.selectDB(selectExpression, this.context);
		
		if(users.size() == 0)
			user = null;
		else
			user = (User) users.get(0);
		
		return user;
	}
	
	/* Create a generic method to search in database */
	public ArrayList<Proposition> getPropositions(String _columnName, String _value)
	{
		SqlSelect selectExpression = new SqlSelect();
		ArrayList<DAOAbstract> abstractPropositions = new ArrayList<DAOAbstract>();
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();

		Filter propositionsFilter = new Filter("Category", "=");
		propositionsFilter.setValue("Segurança Pública");

		Proposition proposition = new Proposition();

		selectExpression.setEntity("Proposition");
		selectExpression.setExpression(propositionsFilter);

		abstractPropositions =  proposition.selectDB(selectExpression, this.context);

		int i = 0;
		while(i < abstractPropositions.size()){
			propositions.add((Proposition) abstractPropositions.get(i));
			i++;
		}
		Util.debug("Final size:" + propositions.size());
		Proposition tmpProp =  propositions.get(0);
		Proposition tmpProp2 =  propositions.get(1);
		Util.debug("Prop1 :" + tmpProp.getId());
		Util.debug("Prop2 :" + tmpProp2.getId());
		return propositions;
	}
	
	public User verifyMatchingUserPassword (String _userName, String _password) 
	{
		User user = this.getUser("user_name", _userName);
		
		if (user != null) {
			if(!_password.equals(user.getPassword())) {
				user = null;
			}
		}
		return user;
	}

	/**
	* @param _user
	* @brief
	*/
	public void saveUser(User _user)
	{
		if(_user == null)
		{
			return;
		}
		
		try {
			_user.insertDB(this.context);
		}
		catch (SQLException sqlE) {
			sqlE.printStackTrace();
			throw new SQLException();
		}
		return;
	}

	/**
	* @param _user
	* @brief 
	*/
	public void removeUser(User _user)
	{
		if(_user == null) 
		{
			return;
		}
		
		Filter deleteFilter = new Filter(User.COLUMN_USERNAME, "=");
		deleteFilter.setValue(_user.getUsername());
		
		_user.deleteDB(deleteFilter, this.context);
			
		return;
	}

	/**
	* @param _user
	* @brief
	*/
	public void editUser(User _user)
	{
		if(_user == null)
		{
			return;
		}
		
		Filter editFilter = new Filter(User.COLUMN_USERNAME, "=");
		editFilter.setValue(_user.getUsername());
		
		_user.updateDB(editFilter, this.context);

		return;
	}

	/**
	* @param _dataStart
	* @param _totalSearch
	* @return
	* @brief
	*/
	public ArrayList<Proposition> getListPreposition(Date _dataStart, int _totalSearch)
	{
		return null;		
	}

	/**
	* @param _observer
	* @brief
	*/
	public void registerObserver(ListenerObserverInterface _observer)
	{
		this.observers.add(_observer);

		return;
	}

	/**
	* @param _observer
	* @brief
	*/
	public void removeObserver(ListenerObserverInterface _observer)
	{
		 return;
	}
}
