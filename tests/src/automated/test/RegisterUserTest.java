package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class RegisterUserTest extends UiAutomatorTestCase
{
	public void testIfEmailIsValid() throws UiObjectNotFoundException
	{
		
		UiObject donc = new UiObject(new UiSelector().text("De Olho na Câmara"));
		donc.clickAndWaitForNewWindow();
		
		UiObject registerButton = new UiObject(new UiSelector().index(6).text("Cadastrar"));
		registerButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject name = new UiObject(new UiSelector().index(2).text("Inserir o seu nome"));
		name.click();
		name.setText("ana");
		
		UiObject invalidEmail = new UiObject(new UiSelector().index(4).text("Inserir o seu email"));
		invalidEmail.click();
		invalidEmail.setText("a@email");
		
		new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().index(13)
				.text("Cadastrar").className("android.widget.Button"));
		new UiObject( new UiSelector().index(13).text("Cadastrar")
				.className("android.widget.Button")).click();
		
		UiObject registerButton1 = new UiObject(new UiSelector().index(13).text("Cadastrar"));
		registerButton1.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject validEmail = new UiObject(new UiSelector().index(4).text("Inserir o seu email"));
		validEmail.click();
		validEmail.setText("a@email.com");
		
	}
	
	public void testIfUsernameIsValid() throws UiObjectNotFoundException
	{
		
		UiObject age = new UiObject(new UiSelector().index(6).text("Insira sua idade"));
		age.click();
		age.setText("28");
		
		UiObject education = new UiObject(new UiSelector().index(0).text("Fundamental"));
		education.click();
		
		UiObject sex = new UiObject(new UiSelector().index(1).text("Feminino"));
		sex.click();
		
		new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().index(9)
				.text("Nome de usuário").className("android.widget.EditText"));
		new UiObject( new UiSelector().index(9).text("Nome de usuário")
				.className("android.widget.EditText")).click();
		
		UiObject invalidUsername = new UiObject(new UiSelector().index(9).text("Nome de usuário"));
		invalidUsername.click();
		invalidUsername.setText("00ana");
		
		UiObject password = new UiObject(new UiSelector().index(11));
		password.click();
		password.setText("ana123");
		
		UiObject confirmPassword = new UiObject(new UiSelector().index(12));
		confirmPassword.click();
		confirmPassword.setText("ana123");
	
		UiObject confirmRegisterButton = new UiObject(new UiSelector().index(13).text("Cadastrar"));
		confirmRegisterButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject validUsername = new UiObject(new UiSelector().index(9).text("Nome de usuário"));
		validUsername.click();
		validUsername.setText("ana");
	}
	
	public void testPasswordIsRight() throws UiObjectNotFoundException
	{
		
		UiObject password = new UiObject(new UiSelector().index(11));
		password.click();
		password.setText("ana123");
		
		UiObject confirmPassword = new UiObject(new UiSelector().index(12));
		confirmPassword.click();
		confirmPassword.setText("ana12355");
		
		new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().index(13)
				.text("Cadastrar").className("android.widget.Button"));
		new UiObject( new UiSelector().index(13).text("Cadastrar")
				.className("android.widget.Button")).click();
		
		UiObject confirmRegisterButton = new UiObject(new UiSelector().index(13).text("Cadastrar"));
		confirmRegisterButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject password1 = new UiObject(new UiSelector().index(11));
		password1.click();
		password1.setText("ana123");
		
		UiObject rightConfirmPassword = new UiObject(new UiSelector().index(12));
		rightConfirmPassword.click();
		rightConfirmPassword.setText("ana123");
	
	}
	
	public void testValidRegister() throws UiObjectNotFoundException
	{
		UiObject confirmRegisterButton = new UiObject(new UiSelector().index(13).text("Cadastrar"));
		confirmRegisterButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
		getUiDevice().pressBack();
		
	}

}
