package com.pvmp.models;

import java.io.Serializable;

import android.content.Context;

import com.pvmp.dao.UserDAO;

public class User implements Serializable {
	private static final long serialVersionUID = -6329621094685424751L;
	
	private String username;
	private String password;
	private String name;
	private String email;
	private int age;
	private String education;
	private String sex;
	private static UserDAO userDao;
	private String defaultUser;
	
	public User () {
		this.name = null;
		this.username = null;
		this.password = null;
		this.email = null;
		this.age = 0;
		this.education = null;
		this.sex = null;
		this.defaultUser = null;
	}
	
	public User(String name, String username, String password, String email
			, int age, String education, String sex, String defaultUser){
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.education = education;
		this.sex = sex;
		this.defaultUser = defaultUser;
    }
	
	public String getName() {
        
        return name;
    }
	
	public String getUsername() {
        
        return this.username;
    }

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public User verifyExistingUser (String username, String password, Context context) {
		User user = new User();
		userDao = UserDAO.getInstance(context);
		user = userDao.selectByUsername(username);
		
		if (user.getUsername() != null && user.getPassword().equals(password)) 
			return user;
		return null;
	}
	
	public static boolean validateExistingEmail (String email, Context context){
		User user = new User();
		userDao = UserDAO.getInstance(context);
		user = userDao.selectByEmail(email);
		
		if (user.getEmail() == null)
			return true;
		return false;
	}
	
	public static boolean validateExistingUser (String username, Context context){
		User user = new User();
		userDao = UserDAO.getInstance(context);
		user = userDao.selectByUsername(username);
		
		if (user.getUsername() == null)
			return true;
		return false;
	}
	
	public static boolean validatePassword (String password) {
		if(password != null && password.length()>=6 && password.length()<=15)
			return true;
		return false;
	}
	
	public static boolean validateNameFormat (String name) {
		if(name.matches("[a-zA-Z ]+")) {
			for (int i = 0; i < name.length(); i++) {
				if (name.charAt(i) == ' ' && i != (name.length() - 1)) {
					if (name.charAt(i+1) == ' ')
						return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean validateNameSize (String name) {
		if(name.length() > 2 && name.length() < 51)
			return true;
		return false;
	}
	
	public static boolean validateEmailFormat (String email) {
		if(email != null && (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
			return true;	
		return false;
	}
	
	public static boolean validateEmailSize (String email) {
		if (email.length() < 40)
			return true;
		return false;
	}
	
	public static boolean validateAge (int age) {
		if(age >= 10 && age <= 99)
			return true;
		return false;
	}
	
	public static boolean validateUsernameSize (String username) {
		if (username.length() >= 3 && username.length() <= 15)
			return true;
		return false;
	}
	
	public static boolean validateUsernameFirstLetter (String username) {
		return (Character.isLetter(username.charAt(0)));
	}
	
	public static boolean validateUsernameFormat (String username) {
		if (username.matches("[a-zA-Z0-9]+"))
			return true;
		return false;
	}
	
	public static int validationResult (User user, Context context) {		
		if (!User.validateNameFormat(user.getName()))
			return 1;
		
		if (!User.validateNameSize(user.getName()))
			return 2;
		
		if (!User.validateEmailFormat(user.getEmail()))
			return 3;
		
		if (!User.validateEmailSize(user.getEmail()))
			return 4;
		
		if (!User.validateAge(user.getAge()))
			return 5;
		
		if (!User.validatePassword(user.getPassword()))
			return 6;
		
		if(!User.validateExistingEmail(user.getEmail(), context)) 
			return 7;
		
		if (!User.validateExistingUser(user.getUsername(), context))
			return 8;
		
		if (!User.validateUsernameSize(user.getUsername()))
			return 9;
		
		if (!User.validateUsernameFirstLetter(user.getUsername()))
			return 10;
		
		if(!User.validateUsernameFormat(user.getUsername()))
			return 11;
		
		return 0;
	}

	public String getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}
}
