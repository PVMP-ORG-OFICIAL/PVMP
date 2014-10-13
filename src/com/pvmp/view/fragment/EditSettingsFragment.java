/**
* @file EditSettingsFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;

import com.pvmp.dao.UserDAO;
import com.pvmp.models.User;
import com.pvmp.R;

/**
* @class
* @brief
*/
public class EditSettingsFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.fragment_home, _container, false);
         
		return rootView;
  }
}
