/**
* @file PVMPModel.java
* @brief
*/
package com.pvmp.models;

import java.util.ArrayList;

import android.content.Context;
import android.database.SQLException;

import com.pvmp.dao.Criteria;
import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Expression;
import com.pvmp.dao.Filter;
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
		//PersistenceHelper.getInstance(this.context);
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
		if (_attribute == null || _columnName == null)
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
	
	/**
	 * @param _columnName
	 * @param _value
	 * @brief Return an ArrayList of propositions based on a column of the Database and
	 * 		  its respective "finder" (a.k.a _value).
	 * */
	public ArrayList<Proposition> getPropositions(String _columnName, String _value)
	{
		//_value might be null
		if (_columnName == null)
		{
			throw new NullPointerException("Null value at PVMPmodel.getPropositions()");
		}
		
		SqlSelect selectExpression = new SqlSelect();
		ArrayList<DAOAbstract> abstractPropositions = new ArrayList<DAOAbstract>();
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		Criteria filterConcatenator = new Criteria();
		Proposition proposition = new Proposition();
		
		selectExpression.setEntity("Proposition");
		
		if (_value != null) 
		{
			Filter propositionsFilterFullName = new Filter(_columnName, "=");
			propositionsFilterFullName.setValue(_value);
			
			//Find propositions that have more than one category (based on one category)
			Filter propositionsFilterLikeName = new Filter(_columnName, "LIKE");
			propositionsFilterLikeName.setValue("%"+_value+"%");
		
			filterConcatenator.add(propositionsFilterFullName, Expression.OR_OPERATOR);
			filterConcatenator.add(propositionsFilterLikeName, Expression.OR_OPERATOR);
			
			selectExpression.setExpression(filterConcatenator);
		}

		abstractPropositions =  proposition.selectDB(selectExpression, this.context);

		int i = 0;
		while(i < abstractPropositions.size()){
			propositions.add((Proposition) abstractPropositions.get(i));
			i++;
		}

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
