package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class RegisterUserTest extends UiAutomatorTestCase
{
	public void testRegister() throws UiObjectNotFoundException
	{
	
		UiObject pvmp = new UiObject(new UiSelector().text("PVMP"));
		pvmp.clickAndWaitForNewWindow();
		
		UiObject registerButton = new UiObject(new UiSelector().index(6).text("Cadastrar"));
		registerButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject name = new UiObject(new UiSelector().index(2).text("Inserir o seu nome"));
		name.click();
		name.setText("ana");
		
		UiObject email = new UiObject(new UiSelector().index(4).text("Inserir o seu email"));
		email.click();
		email.setText("a@email.com");
		
		UiObject age = new UiObject(new UiSelector().index(6).text("Insira sua idade"));
		age.click();
		age.setText("28");
		
		UiObject education = new UiObject(new UiSelector().index(0).text("Fundamental"));
		education.click();
		
		UiObject sex = new UiObject(new UiSelector().index(1).text("Feminino"));
		sex.click();
		
		UiObject userName = new UiObject(new UiSelector().index(9).text("Nome de usuário"));
		userName.click();
		userName.setText("ana");
		
		UiObject password = new UiObject(new UiSelector().index(11));
		password.click();
		password.setText("ana123");
		
		UiObject confirmPassword = new UiObject(new UiSelector().index(12));
		confirmPassword.click();
		confirmPassword.setText("ana123");
		
		UiObject registerButton1 = new UiObject(new UiSelector().index(13).text("Cadastrar"));
		registerButton1.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
	}

}
