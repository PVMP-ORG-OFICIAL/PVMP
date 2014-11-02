/**
* @file FeedBackFragment.java
* @fbrief 
*/
package com.pvmp.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvmp.R;

/**
* @class
* @brief
*/
public class FeedBackFragment extends Fragment 
{
	public FeedBackFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState)
	{
		View rootView = _inflater.inflate(R.layout.fragment_home, _container, false);
    
		return rootView;
  }
}
