/**
* @frile HomeFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvmp.util.Util;
import com.pvmp.R;

public class HomeFragment extends Fragment 
{
	
	public HomeFragment()
	{}
	
	@Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState)
		{
				Util.debug("HomeFragment: Begin");
 
        View rootView = _inflater.inflate(R.layout.fragment_home, _container, false);
         
        return rootView;
    }
}
