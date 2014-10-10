package com.pvmp.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pvmp.R;

public class FeedBackFragment extends Fragment 
{
	public FeedBackFragment(){}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }
}
