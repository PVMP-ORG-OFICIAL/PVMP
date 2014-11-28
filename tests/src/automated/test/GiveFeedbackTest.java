package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class GiveFeedbackTest extends UiAutomatorTestCase
{
	
	public void testGiveFeedback () throws UiObjectNotFoundException 
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
		
		UiObject selectCategory = new UiObject(new UiSelector().index(0));
		selectCategory.clickAndWaitForNewWindow();
		sleep(500);
		
		
		UiObject likeButton = new UiObject(new UiSelector().index(0).className("android.widget.ToggleButton"));
		likeButton.click();
		sleep(1000);
		
		getUiDevice().pressBack();
		
		UiObject selectOtherCategory = new UiObject(new UiSelector().index(1).className("android.widget.RelativeLayout"));
		selectOtherCategory.clickAndWaitForNewWindow();
		sleep(500);
		
		UiObject didntLikeButton = new UiObject(new UiSelector().index(1).className("android.widget.ToggleButton"));
		didntLikeButton.click();
		sleep(1000);
		
		getUiDevice().pressBack();
		
		UiObject selectAnotherCategory = new UiObject(new UiSelector().index(8).className("android.widget.RelativeLayout"));
		selectAnotherCategory.clickAndWaitForNewWindow();
		
		UiObject clownButton = new UiObject(new UiSelector().index(2).className("android.widget.ToggleButton"));
		clownButton.click();
		sleep(1000);
		
		UiObject againClownButton = new UiObject(new UiSelector().index(2).className("android.widget.ToggleButton"));
		againClownButton.click();
		sleep(1000);
		
		getUiDevice().pressBack();
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
		getUiDevice().pressBack();
	}

}
