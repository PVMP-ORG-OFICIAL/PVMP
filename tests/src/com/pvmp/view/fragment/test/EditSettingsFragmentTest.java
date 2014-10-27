package com.pvmp.view.fragment.test;

import android.app.Fragment;
import android.test.AndroidTestCase;

import com.pvmp.view.fragment.EditSettingsFragment;

public class EditSettingsFragmentTest extends AndroidTestCase 
{
	@Override
	public void setUp() throws Exception 
	{
		super.setUp();		
	}
	
	public void testGetNewInstanceOfEditSettingsFragment()
	{
		Fragment fragment = new EditSettingsFragment();
		
		EditSettingsFragment newfragment = (EditSettingsFragment) fragment;
		
		assertNotNull(newfragment);
	}

}