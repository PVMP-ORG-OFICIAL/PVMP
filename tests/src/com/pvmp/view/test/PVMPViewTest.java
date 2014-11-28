package com.pvmp.view.test;

import com.pvmp.view.PVMPView;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

public class PVMPViewTest extends ActivityInstrumentationTestCase2<PVMPView> {
	
	private PVMPView mActivity;
	
    public PVMPViewTest() {
    	super(PVMPView.class);
	}
    
    @Override
    protected void setUp() throws Exception
    {
    	super.setUp();
    	
    	setActivityInitialTouchMode(false);
    	
    	this.mActivity = getActivity();
    }
    
    @SmallTest
    public void testPreconditions()
    {
    	assertNotNull(this.mActivity);
    }
    
    @Override
    protected void tearDown() throws Exception
    {
    	super.tearDown();
    }

}
