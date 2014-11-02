/**
* @frile HomeFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvmp.controller.PVMPController;
import com.pvmp.util.Util;
import com.pvmp.view.PVMPView;
import com.pvmp.R;

public class HomeFragment extends Fragment 
{
	private PVMPView mainActivity;
	private PVMPController controller;
	private Context context;
	
	public HomeFragment()
	{}
	
	@Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState)
		{
				Util.debug("HomeFragment: Begin");
			
				this.mainActivity = (PVMPView) getActivity();
				this.context = mainActivity.getApplicationContext();
				this.controller = new PVMPController(context);
				controller.setView(HomeFragment.this.mainActivity);
				
				this.mainActivity.updateUser(controller.openSession()); 
        View rootView = _inflater.inflate(R.layout.fragment_home, _container, false);
        mainActivity.enableDrawer(true);
		mainActivity.enableScreenInteraction(true);
        
        
        return rootView;
    }
}
