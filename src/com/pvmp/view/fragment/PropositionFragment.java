package com.pvmp.view.fragment;

import java.util.ArrayList;

import com.pvmp.R;
import com.pvmp.models.Proposition;
import com.pvmp.view.PVMPView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PropositionFragment extends FragmentView 
{
	private PVMPView view;
	private ArrayList<Proposition> propositions;
	private TextView categoryName;

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.proposition_fragment, _container, false);
		
		this.view = (PVMPView) getActivity();
		this.propositions = PVMPView.propositions;
		
		this.buildScreenComponent(rootView);
		this.updateScreenComponent();
		
		return rootView;
	}

	@Override
	public void buildScreenComponent(View _view) 
	{
		categoryName = (TextView) _view.findViewById(R.id.category);
	}

	public void updateScreenComponent()
	{
		//Testing if the propositions' IDs are correct based on the category clicked.
		String text = "";
		for (int i = 0; i < this.propositions.size(); i++)
		{
			text += propositions.get(i).getId() + ", ";
		}
		categoryName.setText(text);
	}
}
