package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class EnterAsGuest extends UiAutomatorTestCase
{
	
	public void testGuest() throws UiObjectNotFoundException
	{
		
		UiObject appsButton = new UiObject(new UiSelector().index(3).text("Apps"));
		appsButton.clickAndWaitForNewWindow();
		
		UiObject pvmp = new UiObject(new UiSelector().text("PVMP"));
		pvmp.clickAndWaitForNewWindow();
		
		UiObject guestButton = new UiObject(new UiSelector().index(7).text("Entrar como Visitante"));
		guestButton.clickAndWaitForNewWindow();
		sleep(500);
		
		UiObject image = new UiObject(new UiSelector().index(0).className("android.widget.ImageView"));
		image.click();
		
		UiObject seetingButton = new UiObject(new UiSelector().index(1).text("Configurações de Conta")
				.className("android.widget.TextView"));
		seetingButton.clickAndWaitForNewWindow();
		sleep(500);
		
		UiObject selectCategory = new UiObject(new UiSelector().index(0).className("android.widget.RelativeLayout"));
		selectCategory.clickAndWaitForNewWindow();
		sleep(500);
		

		UiObject likeButton = new UiObject(new UiSelector().index(0).className("android.widget.ToggleButton"));
		likeButton.click();
		sleep(1000);
		
		getUiDevice().pressBack();
		
		UiObject imageAgain = new UiObject(new UiSelector().index(0).className("android.widget.ImageView"));
		imageAgain.click();
		
		UiObject exitButton = new UiObject(new UiSelector().index(1).text("Sair"));
		exitButton.clickAndWaitForNewWindow();
		
		getUiDevice().pressBack();
		
	}

}
