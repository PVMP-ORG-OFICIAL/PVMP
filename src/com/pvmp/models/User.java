/**
* @file User.java
* @brief 
*/
package com.pvmp.models;
import android.content.ContentValues;
import android.content.Context;

import com.pvmp.controller.UserController;
import com.pvmp.dao.DAOAbstract;

/**
* @class User
* @brief
*/
public class User extends DAOAbstract 
{	
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_USERNAME = "user_name";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_AGE = "age";
	public static final String COLUMN_EDUCATION = "education";
	public static final String COLUMN_SEX = "sex";
	public static final String COLUMN_DEFAULT = "default_user";
	
	// !--- OTHER ATTRIBUTES ---! \\
	private String username;
	private String password;
	private String name;
	private String email;
	private int age;
	private String education;
	private UserController userController;
	private String sex;
	private String defaultUser;
	
	public User()
	{
		super();
		this.TABLE_NAME = "User";
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
		this.TABLE_NAME = "User";
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
	* @param _passowrd
	* @return
	* @brief 
	*/
	public static boolean validatePasswordSize (String password) {
		if(password != null && password.length()>=6 && password.length()<=15)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @brief
	 */
	public static boolean validatePasswordFormat (String password) {
		if(password.matches("[a-zA-Z0-9]+"))
			return true;
		return false;
	}

	/**
	*	@param _name
	* @return
	* @brief 
	*/
	public static boolean validateNameFormat (String _name) 
	{
		if (_name.matches("[a-zA-Zà-úÀ-Ú ]+")) 
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
	public int validationResult (User _user, Context _context) 
	{
		
		userController = new UserController(_context);
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
		
		if (!User.validatePasswordSize(_user.getPassword()))
		{
			return 6;
		}
		
		if(!userController.validateExistingEmail(_user.getEmail()))
		{
			return 7;
		}
		
		if (!userController.validateExistingUser(_user.getUsername()))
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
		if (!User.validatePasswordFormat(_user.getPassword()))
		{
			return 12;
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
		values.put(COLUMN_DEFAULT, this.defaultUser);
		
		return values;
	}

	@Override
	public User contentValuesToModel(ContentValues _contentValues) 
	{
		User user = new User();
		
		user.setUsername(_contentValues.getAsString(COLUMN_USERNAME));
		user.setPassword(_contentValues.getAsString(COLUMN_PASSWORD));
		user.setName(_contentValues.getAsString(COLUMN_NAME));
		user.setEmail(_contentValues.getAsString(COLUMN_EMAIL));
		user.setAge(_contentValues.getAsInteger(COLUMN_AGE));
		user.setEducation(_contentValues.getAsString(COLUMN_EDUCATION));
		user.setSex(_contentValues.getAsString(COLUMN_SEX));
		user.setDefaultUser(_contentValues.getAsString(COLUMN_DEFAULT));
        
        return user;
	}
}
