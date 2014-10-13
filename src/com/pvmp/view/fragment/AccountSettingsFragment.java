/**
* @file AccountSettingsFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pvmp.models.User;
import com.pvmp.R;

/**
* @class
* @brief
*/
public class AccountSettingsFragment extends Fragment 
{
	public AccountSettingsFragment()
	{}

	@Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
    {
        View rootView = _inflater.inflate(R.layout.fragment_home, _container, false);
         
        return rootView;
    }
}

