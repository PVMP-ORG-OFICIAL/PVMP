/**
* @file User.java
* @brief 
*/
package com.pvmp.models;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;

import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.UserDAO;

/**
* @class User
* @brief
*/
public class User extends DAOAbstract implements Serializable 
{	
	private static final long serialVersionUID = 1L;
	
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_USERNAME = "USERNAME";
	public static final String COLUMN_PASSWORD = "PASSWORD";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_EMAIL = "EMAIL";
	public static final String COLUMN_AGE = "AGE";
	public static final String COLUMN_EDUCATION = "EDUCATION";
	public static final String COLUMN_SEX = "SEX";
	
	// !--- OTHER ATTRIBUTES ---! \\
	private String username;
	private String password;
	private String name;
	private String email;
	private int age;
	private String education;
	private String sex;
	private static UserDAO userDao;
	private String defaultUser;
	
	public User()
	{
		super();
		this.TABLE_NAME = "USER";
		this.name = null;
		this.username = null;
		this.password = null;
		this.email = null;
		this.age = 0;
		this.education = null;
		this.sex = null;
		this.defaultUser = null;
	}
	
	/**
	* @param _name
	* @param _username
	* @param _password
	* @param _email
	* @param _age
	* @param _education 
	* @param _sex
	* @param _defaultUser
	* @brief
	*/
	public User(String _name, String _username, String _password, String _email, 
		int _age, String _education, String _sex, String _defaultUser)
	{
		super();
		this.name = _name;
		this.username = _username;
		this.password = _password;
		this.email = _email;
		this.age = _age;
		this.education = _education;
		this.sex = _sex;
		this.defaultUser = _defaultUser;
    }
	
	public String getName() 
	{      
		return name;
  }
	
	public String getUsername() 
	{
		return this.username;
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String _password)
	{
		this.password = _password;
		return;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String _email) 
	{
		this.email = _email;
		return;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int _age) 
	{
		this.age = _age;
		return;
	}

	public String getEducation() 
	{
		return education;
	}

	public void setEducation(String _education) 
	{
		this.education = _education;
		return;
	}

	public String getSex() 
	{
		return sex;
	}

	public void setSex(String _sex)
	{
		this.sex = _sex;
		return;
	}

	public void setName(String _name)
	{
		this.name = _name;
		return;
	}

	public void setUsername(String _username)
	{
		this.username = _username;
		return;
	}
	
	/**
	* @param _username
	* @param _password
	* @param _context
	* @brief
	* @return
	*/
	public User verifyExistingUser (String _username, String _password, Context _context) 
	{
		User user = new User();
		userDao = UserDAO.getInstance(_context);
		user = userDao.selectByUsername(_username);
		
		if (user.getUsername() != null && user.getPassword().equals(_password)) 
		{
			return user;
		}
		return null;
	}
	
	/**
	* @param _email
	* @param _context
	* @return 
	* @brief 
	*/
	public static boolean validateExistingEmail (String _email, Context _context)
	{
		User user = new User();
		userDao = UserDAO.getInstance(_context);
		user = userDao.selectByEmail(_email);
		
		if (user.getEmail() == null)
		{
			return true;
		}

		return false;
	}
	
	/**
	* @param _username
	* @param _context
	* @return
	* @brief 
	*/
	public static boolean validateExistingUser (String _username, Context _context)
	{
		User user = new User();
		userDao = UserDAO.getInstance(_context);
		user = userDao.selectByUsername(_username);
		
		if (user.getUsername() == null)
		{
			return true;
		}
		return false;
	}

	/**
	* @param _passowrd
	* @return
	* @brief 
	*/
	public static boolean validatePassword (String _password)
	{
		if(_password != null && _password.length() >= 6 && _password.length() <= 15)
		{
			return true;
		}
		return false;
	}

	/**
	*	@param _name
	* @return
	* @brief 
	*/
	public static boolean validateNameFormat (String _name) 
	{
		if (_name.matches("[a-zA-Z ]+")) 
		{
			for (int i = 0; i < _name.length(); i++) 
			{
				if (_name.charAt(i) == ' ' && i != (_name.length() - 1)) 
				{
					if (_name.charAt(i+1) == ' ')
					{
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	* @param _name
	* @return 
	* @brief
	*/
	public static boolean validateNameSize (String _name)
	{
		if(_name.length() > 2 && _name.length() < 51)
		{
			return true;
		}
		return false;
	}

	/**
	* @param _email
	* @return
	* @brief 
	*/
	public static boolean validateEmailFormat (String _email) 
	{
		if(_email != null && (android.util.Patterns.EMAIL_ADDRESS.matcher(_email).matches()))
		{
			return true;
		}
		return false;
	}
	
	/**
	* @param _email
	* @return 
	* @brief
	*/
	public static boolean validateEmailSize (String _email)
	{
		if (_email.length() < 40)
		{ 
			return true;
		}
		return false;
	}
	
	/**
	* @param _age
	* @return 
	* @brief 
	*/
	public static boolean validateAge (int _age)
	{
		if(_age >= 10 && _age <= 99)
		{
			return true;
		}
		return false;
	}
	
	/**
	* @param _username
	* @return
	* @brief
	*/
	public static boolean validateUsernameSize (String _username) 
	{
		if (_username.length() >= 3 && _username.length() <= 15)
		{
			return true;
		}

		return false;
	}
	
	/**
	* @param _username
	* @return 
	* @brief
	*/
	public static boolean validateUsernameFirstLetter (String _username)
	{
		return (Character.isLetter(_username.charAt(0)));
	}
	
	/**
	* @param _username
	* @return 
	* @brief 
	*/
	public static boolean validateUsernameFormat (String _username) 
	{
		if (_username.matches("[a-zA-Z0-9]+"))
		{ 
			return true;
		}
		return false;
	}

	/**
	* @param _user
	* @param _context
	* @return
	* @brief 
	*/
	public static int validationResult (User _user, Context _context) 
	{
		if (!User.validateNameFormat(_user.getName()))
		{ 
			return 1;
		}
		
		if (!User.validateNameSize(_user.getName()))
		{
			return 2;
		}
		
		if (!User.validateEmailFormat(_user.getEmail()))
		{
			return 3;
		}
		
		if (!User.validateEmailSize(_user.getEmail()))
		{
			return 4;
		}
		
		if (!User.validateAge(_user.getAge()))
		{
			return 5;
		}
		
		if (!User.validatePassword(_user.getPassword()))
		{
			return 6;
		}
		
		if(!User.validateExistingEmail(_user.getEmail(), _context))
		{
			return 7;
		}
		
		if (!User.validateExistingUser(_user.getUsername(), _context))
		{
			return 8;
		}
		
		if (!User.validateUsernameSize(_user.getUsername()))
		{
			return 9;
		}
		
		if (!User.validateUsernameFirstLetter(_user.getUsername()))
		{
			return 10;
		}
		
		if(!User.validateUsernameFormat(_user.getUsername()))
		{
			return 11;
		}
		
		return 0;
	}

	/**
	* @return
	* @brief
	*/
	public String getDefaultUser() 
	{
		return defaultUser;
	}

	/**
	* @param _defaultUser
	* @brief 
	*/
	public void setDefaultUser(String _defaultUser)
	{
		this.defaultUser = _defaultUser;
	}

	@Override
	public ContentValues generateContentValues() 
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_USERNAME, this.username);
		values.put(COLUMN_PASSWORD, this.password);
		values.put(COLUMN_NAME, this.name);
		values.put(COLUMN_EMAIL, this.email);
		values.put(COLUMN_AGE, this.age);
		values.put(COLUMN_EDUCATION, this.education);
		values.put(COLUMN_SEX, this.sex);
		
		return values;
	}

	@Override
	public User contentValuesToModel(ContentValues _contentValues) 
	{
		this.username = _contentValues.getAsString(COLUMN_USERNAME);
        this.password = _contentValues.getAsString(COLUMN_PASSWORD);
        this.name = _contentValues.getAsString(COLUMN_NAME);
        this.email = _contentValues.getAsString(COLUMN_EMAIL);
        this.age = _contentValues.getAsInteger(COLUMN_AGE);
        this.education = _contentValues.getAsString(COLUMN_EDUCATION);
        this.sex = _contentValues.getAsString(COLUMN_SEX);
        
        return this;
	}
}
