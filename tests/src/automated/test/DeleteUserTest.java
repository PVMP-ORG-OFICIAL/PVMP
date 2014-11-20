package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class DeleteUserTest extends UiAutomatorTestCase
{
	public void testDeletUser() throws UiObjectNotFoundException
	{
		UiObject appsButton = new UiObject(new UiSelector().index(3).text("Apps"));
		appsButton.clickAndWaitForNewWindow();
		
		UiObject pvmp = new UiObject(new UiSelector().text("PVMP"));
		pvmp.clickAndWaitForNewWindow();
		
		UiObject userField = new UiObject (new UiSelector().index(2).text("Nome de usuário"));
		userField.click();
		userField.setText("ana");
		
		UiObject passwordField = new UiObject(new UiSelector().index(4));
		passwordField.click();
		passwordField.setText("ana123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		sleep(500);
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject settingsButton = new UiObject(new UiSelector().index(3));
		settingsButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject deleteButton = new UiObject(new UiSelector().index(13).text("Excluir"));
		deleteButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject insertPassword  = new UiObject(new UiSelector().index(14));
		insertPassword.click();
		insertPassword.setText("ana123");
		
		UiObject deleteButton1 = new UiObject(new UiSelector().index(13).text("Excluir"));
		deleteButton1.clickAndWaitForNewWindow();
		sleep(200);
		
	}

}
