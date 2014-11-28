/**
 * @file PVMPDatabaseTest.java
 * */
package com.pvmp.controller.test;

import java.util.ArrayList;

import com.pvmp.controller.UserController;
import com.pvmp.dao.DAOAbstract;
import com.pvmp.dao.Filter;
import com.pvmp.dao.SqlSelect;
import com.pvmp.model.User;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * @class DatabaseTest
 * @brief Responsible for testing the class PVMPDatabase.
 * */
//Might be renamed to DAOAbstract
public class UserControllerTest extends AndroidTestCase
{
	private RenamingDelegatingContext context;
	private User user;
	private User user2;
	private SqlSelect queryExpression;
	private Filter whereFilter;
	private ArrayList<DAOAbstract> users;
	private UserController userController;
	
	@Override
	public void setUp() throws Exception 
	{
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.userController = new UserController(this.context);
		
		this.user = new User("Joao", "juca123", "senha1234", "email", 19, "Superior", "M", "N");
		this.user2 = new User();
		
		
		this.queryExpression = new SqlSelect();
		
		this.whereFilter = new Filter(User.COLUMN_USERNAME, "=");
		this.whereFilter.setValue("juca123");
		
		this.queryExpression.setExpression(this.whereFilter);
		this.queryExpression.addEntity(user.TABLE_NAME);
		
		this.users = new ArrayList<DAOAbstract>();
	}
	
	/**
	 * @brief Tests if a instance is indeed inserted into the PVMPDatabase by the method registerUser()
	 * 		  of UserController.
	 * */
	public void testSaveUser () 
	{
		//insert the user in the DB.
		this.userController.saveUser(this.user);
	}
	
	/**
	 * @brief Tests if a instance is correctly select from the PVMPDatabase. The method selectUser of
	 * 		  UserController returns a user based on a attribute of its choice and the respective
	 * 	      Column Name that it will be selected from. After that, every attribute of user2 is
	 * 		  compared with the this.user's attributes (which was inserted on the test above.
	 * */
	@SmallTest
	public void testGetUser () 
	{
		this.user2 = this.userController.getUser(User.COLUMN_USERNAME, this.user.getUsername());
		
		//Need to find a better way to test it. like testing if the two instances (user and user2) are equal
		assertEquals(user.getUsername(), user2.getUsername());
		assertEquals(user.getName(), user2.getName());
		assertEquals(user.getPassword(), user2.getPassword());
		assertEquals(user.getEmail(), user2.getEmail());
		assertEquals(user.getAge(), user2.getAge());
		assertEquals(user.getEducation(), user2.getEducation());
		assertEquals(user.getSex(), user2.getSex());
	}
	/**
	 * @brief Tests if the Database is updated by the UserController's editUser. After editing 
	 * 		  some attributes of a instance, the method update it on the DB. Then, many asserts
	 *        check if the attributes are the same.
	 * */
	public void testEditUser () 
	{
		this.user.setName("Juca bala");
		this.user.setEducation("Medio");
		this.user.setAge(41);
		this.user.setSex("F");
		
		this.userController.editUser(this.user);
		
		this.users = this.user.selectDB(this.queryExpression, this.context);
		
		this.user2 = (User) users.get(0);
		
		assertEquals("Juca bala", user2.getName());
		assertEquals("Medio", user2.getEducation());
		assertEquals(41, user.getAge());
		assertEquals("F", user.getSex());
	}
	
	/**
	 * @brief Tests if the deleteDB method from DAOAbstract derived classes deletes a instance from the
	 * 		  PVMPDatabase correctly. If the ArrayList returned by selectDB() contains nothing, it's
	 * 		  a signal that the instance was correctly deleted (because it was inserted on the tests above).
	 * */
	public void testRemoveUser () 
	{
		this.userController.removeUser(this.user);
		
		this.users = this.user.selectDB(this.queryExpression, this.context);
		
		assertEquals(0, users.size());
	}
	
	public void testVerifyMatchingUserPassword()
	{
		this.user2 = this.userController.verifyMatchingUserPassword("juca123", "senha1234");
		
		assertNotNull(user2);
		
		this.user2 = this.userController.verifyMatchingUserPassword("joao", "senha");
		assertNull(user2);
	}
	
	public void testValidateExistingEmail()
	{
		assertFalse(this.userController.validateExistingEmail(user.getEmail()));
		assertTrue(this.userController.validateExistingEmail("emailDiferente"));
	}
	
	public void testValidateExistingUser()
	{
		assertFalse(this.userController.validateExistingUser(this.user.getUsername()));
		assertTrue(this.userController.validateExistingUser("nomeUsuarioQualquer"));
	}
	
	public void tearDown() throws Exception 
	{
		super.tearDown();
	}

}
