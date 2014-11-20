package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class GiveFeedbackTest extends UiAutomatorTestCase
{
	
	public void testGiveFeedback () throws UiObjectNotFoundException 
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
		
		UiObject selectCategory = new UiObject(new UiSelector().index(0));
		selectCategory.clickAndWaitForNewWindow();
		sleep(500);
		
		UiObject likeButton = new UiObject(new UiSelector().className("android.widget.ToggleButton").
				childSelector(new UiSelector().index(0)));
		likeButton.click();
		sleep(500);
		
		UiObject nextButton = new UiObject(new UiSelector().index(2).text("Próxima"));
		nextButton.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject didntLikeButton = new UiObject(new UiSelector().index(1));
		didntLikeButton.click();
		sleep(1000);
		
		getUiDevice().pressBack();
		
		UiObject selectCategory1 = new UiObject(new UiSelector().index(8));
		selectCategory1.clickAndWaitForNewWindow();
		
		UiObject clownButton = new UiObject(new UiSelector().index(2));
		clownButton.click();
		
		getUiDevice().pressBack();
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
	}

}
