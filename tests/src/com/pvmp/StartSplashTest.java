package com.pvmp;

import android.test.ActivityInstrumentationTestCase2;

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
public class StartSplashTest extends ActivityInstrumentationTestCase2<StartSplash> {

    public StartSplashTest() {
        super("com.pvmp", StartSplash.class);
    }

}
