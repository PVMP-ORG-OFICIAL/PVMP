package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class DeleteUserTest extends UiAutomatorTestCase
{
	public void testDeletUser() throws UiObjectNotFoundException
	{

		UiObject donc = new UiObject(new UiSelector().text("De Olho na Câmara"));
		donc.clickAndWaitForNewWindow();
		
		UiObject userField = new UiObject (new UiSelector().index(2).text("Nome de usuário"));
		userField.click();
		userField.setText("ana");
		
		UiObject passwordField = new UiObject(new UiSelector().index(4).className("android.widget.EditText"));
		passwordField.click();
		passwordField.setText("ana123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		sleep(500);
		
		UiObject image = new UiObject(new UiSelector().index(1).className("android.widget.ImageView"));
		image.click();
		
		UiObject settingsButton = new UiObject(new UiSelector().index(1).text("Configurações de Conta")
				.className("android.widget.TextView"));
		settingsButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject deleteButton = new UiObject(new UiSelector().index(13).text("Excluir"));
		deleteButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject insertPassword  = new UiObject(new UiSelector().index(14).className("android.widget.EditText"));
		insertPassword.click();
		insertPassword.setText("ana123");
		
		UiObject deleteButtonConfirm = new UiObject(new UiSelector().index(13).text("Excluir"));
		deleteButtonConfirm.clickAndWaitForNewWindow();
		sleep(200);
		
	}
	
	public void testTryToLoginAfterDeleteUser() throws UiObjectNotFoundException
	{
		UiObject userField = new UiObject (new UiSelector().index(2).text("Nome de usuário"));
		userField.click();
		userField.setText("ana");
		
		UiObject passwordField = new UiObject(new UiSelector().index(4).className("android.widget.EditText"));
		passwordField.click();
		passwordField.setText("ana123");
		
		UiObject enterButton = new UiObject(new UiSelector().index(5).text("Entrar"));
		enterButton.clickAndWaitForNewWindow();
		sleep(500);
	}

}
