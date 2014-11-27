package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LoginTest extends UiAutomatorTestCase 
{
	public void testLoginEmptyFields() throws UiObjectNotFoundException 
	{
		
		UiObject pvmp = new UiObject(new UiSelector().text("PVMP"));
		pvmp.clickAndWaitForNewWindow();
		
		UiObject userNameIsEmpty = new UiObject(new UiSelector().index(2).text("Nome de usuário")
				.className("android.widget.EditText"));
		userNameIsEmpty.click();
		userNameIsEmpty.setText("");
		
		UiObject userPasswordIsEmpty = new UiObject(new UiSelector().index(4));
		userPasswordIsEmpty.click();
		userPasswordIsEmpty.setText("");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		
	}

	public void testLoginNameDontExistPasswordExist() throws UiObjectNotFoundException 
	{
		
		UiObject userNameDontExists = new UiObject(new UiSelector().index(2).text("Nome de usuário")
				.className("android.widget.EditText"));
		userNameDontExists.click();
		userNameDontExists.setText("joão");
		
		UiObject userPasswordExists = new UiObject(new UiSelector().index(4));
		userPasswordExists.click();
		userPasswordExists.setText("ana123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		
	}
	
	public void testLoginNameExistPasswordDont() throws UiObjectNotFoundException 
	{
		
		UiObject userNameExists = new UiObject(new UiSelector().index(2).text("Nome de usuário")
				.className("android.widget.EditText"));
		userNameExists.click();
		userNameExists.setText("ana");
		
		UiObject userPasswordDontExists = new UiObject(new UiSelector().index(4));
		userPasswordDontExists.click();
		userPasswordDontExists.setText("joão123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		
	}	
	
	public void testLoginThatExists() throws UiObjectNotFoundException 
	{
		
		UiObject userName = new UiObject(new UiSelector().index(2).text("Nome de usuário")
				.className("android.widget.EditText"));
		userName.click();
		userName.setText("ana");
		
		UiObject userPassword = new UiObject(new UiSelector().index(4));
		userPassword.click();
		userPassword.setText("ana123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		
	}

	public void testLogout() throws UiObjectNotFoundException
	{
	
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
	}
}
