package com.pvmp;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.pvmp.StartSplashTest \
 * com.pvmp.tests/android.test.InstrumentationTestRunner
 */
public class StartSplashTest extends ActivityInstrumentationTestCase2<StartSplash> 
{
	private StartSplash mActivity;
	
	public StartSplashTest() 
	{
		super(StartSplash.class);
	}
	
	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		
		setActivityInitialTouchMode(false);

		this.mActivity = getActivity();
	}
	
	@SmallTest
	public void testPreConditions() 
	{
		assertNotNull(this.mActivity);
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

}
