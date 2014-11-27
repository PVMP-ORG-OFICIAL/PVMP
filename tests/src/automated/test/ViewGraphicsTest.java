package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class ViewGraphicsTest extends UiAutomatorTestCase 
{
	
	public void testViewGraphics() throws UiObjectNotFoundException
	{

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
		sleep(1000);
		
		UiObject selectCategory = new UiObject(new UiSelector().index(0));
		selectCategory.clickAndWaitForNewWindow();
		sleep(200);
		
		UiObject viewFirstGraphic = new UiObject(new UiSelector().index(3).className("android.view.View"));
		viewFirstGraphic.click();
		sleep(500);
		
		UiObject viewSecondGraphic = new UiObject(new UiSelector().index(5).className("android.view.View"));
		viewSecondGraphic.click();
		sleep(500);
		
		//scroll page to view the third graphic.
		new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().index(7)
				.className("android.view.View"));
		new UiObject( new UiSelector().index(7).className("android.view.View")).click();
		
		getUiDevice().pressBack();
		
		UiObject image = new UiObject(new UiSelector().index(1));
		image.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
		getUiDevice().pressBack();
	}
}
