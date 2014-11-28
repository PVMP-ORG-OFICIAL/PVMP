package automated.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestLoginFragment extends UiAutomatorTestCase {

	public TestLoginFragment() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	protected void testDemo() throws UiObjectNotFoundException 
	{
		getUiDevice().pressHome();
		
		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
		
		allAppsButton.clickAndWaitForNewWindow();
		
		UiObject downloadsTab = new UiObject(new UiSelector().text("Downloads"));
		
		downloadsTab.click();
		
		UiScrollable downloadViews = new UiScrollable(new UiSelector().scrollable(true));
		
		downloadViews.setAsHorizontalList();
		
		UiObject pvmpApp = downloadViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "PVMP");
		
		pvmpApp.clickAndWaitForNewWindow();
		
		UiObject pvmpValidation = new UiObject(new UiSelector().packageName("com.pvmp"));
		
		assertTrue("Unable to detect PVMP", pvmpValidation.exists());
	}

}
