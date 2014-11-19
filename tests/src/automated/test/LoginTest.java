package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LoginTest extends UiAutomatorTestCase 
{
	
	public void testLogin() throws UiObjectNotFoundException 
	{
		
		UiObject appsButton = new UiObject(new UiSelector().index(3).text("Apps"));
		appsButton.clickAndWaitForNewWindow();
		
		UiObject pvmp = new UiObject(new UiSelector().text("PVMP"));
		pvmp.clickAndWaitForNewWindow();
		
		UiObject userName = new UiObject(new UiSelector().index(2).text("Nome de usuário"));
		userName.click();
		userName.setText("ana");
		
		UiObject userPassword = new UiObject(new UiSelector().index(4));
		userPassword.click();
		userPassword.setText("ana123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
	}
}
