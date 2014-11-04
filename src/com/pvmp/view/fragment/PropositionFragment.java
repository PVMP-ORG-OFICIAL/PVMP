package com.pvmp.view.fragment;

import com.pvmp.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PropositionFragment extends FragmentView 
{

	private TextView categoryName;

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.proposition_fragment, _container, false);
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
		categoryName.setText("Nome da Categoria");
	}
}
