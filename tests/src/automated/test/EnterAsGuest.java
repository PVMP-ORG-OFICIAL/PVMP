package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class EnterAsGuest extends UiAutomatorTestCase
{
	
	public void testGuest() throws UiObjectNotFoundException
	{
			
		UiObject donc = new UiObject(new UiSelector().text("De Olho na Câmara"));
		donc.clickAndWaitForNewWindow();
			
		UiObject guestButton = new UiObject(new UiSelector().index(7).text("Entrar como Visitante"));
		guestButton.clickAndWaitForNewWindow();
		sleep(500);
			
		UiObject image = new UiObject(new UiSelector().index(0).className("android.widget.ImageView"));
		image.click();
			
		UiObject settingButton = new UiObject(new UiSelector().index(1).text("Configurações de Conta")
				.className("android.widget.TextView"));
		settingButton.clickAndWaitForNewWindow();
		sleep(500);
			
		UiObject selectCategory = new UiObject(new UiSelector().index(0).className("android.widget.RelativeLayout"));
		selectCategory.clickAndWaitForNewWindow();
		sleep(500);
			
		new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().index(7)
				.className("android.view.View"));
		new UiObject( new UiSelector().index(7).className("android.view.View")).click();
			
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
