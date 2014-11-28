package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class EditUserTest extends UiAutomatorTestCase
{
	
	public void testEditUser() throws UiObjectNotFoundException
	{
		UiObject donc = new UiObject(new UiSelector().text("De Olho na Câmara"));
		donc.clickAndWaitForNewWindow();
		
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
		
		UiObject settingsButton = new UiObject(new UiSelector().index(1).text("Configurações de Conta")
				.className("android.widget.TextView"));
		settingsButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject editButton = new UiObject(new UiSelector().index(12).text("Editar"));
		editButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject education = new UiObject(new UiSelector().index(1).text("Médio"));
		education.click();
		
		UiObject saveButton = new UiObject(new UiSelector().index(9).text("Salvar"));
		saveButton.clickAndWaitForNewWindow();
		sleep(100);
		
		UiObject image1 = new UiObject(new UiSelector().index(1));
		image1.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
	}
}
