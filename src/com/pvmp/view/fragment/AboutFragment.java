package com.pvmp.view.fragment;

import com.pvmp.main.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
			Bundle _savedInstanceState) 
	{
		
		View rootView = _inflater.inflate(R.layout.about_fragment,
				_container, false);
		
		return rootView;
	}
}
