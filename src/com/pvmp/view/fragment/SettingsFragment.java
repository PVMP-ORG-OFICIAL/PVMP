/**
* @file SettingsFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvmp.R;

/**
*	@class SettingsFragment
* @brief
*/
public class SettingsFragment extends Fragment 
{
	public SettingsFragment()
	{}

	@Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState) 
    {
        View rootView = _inflater.inflate(R.layout.fragment_home, _container, false);
         
        return rootView;
    }
}
