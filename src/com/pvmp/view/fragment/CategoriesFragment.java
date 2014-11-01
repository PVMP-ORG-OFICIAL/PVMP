/**
* @file PropositionFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pvmp.R;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.view.adapter.CategoryDrawerAdapter;
import com.pvmp.view.model.AbstractDrawerItem;
import com.pvmp.view.model.CategoryDrawerItem;

/**
*	@class PropositionFragment
* @brief
*/
public class CategoriesFragment extends Fragment 
{
	
	private ListView categoriesList;
	private ArrayList<AbstractDrawerItem> categoriesDrawerItems;
	private CategoryDrawerAdapter adapter;
	private PVMPView mainActivity;
	private TypedArray navigationMenuIcons;
	
	public CategoriesFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.categories_fragment, _container, false);
		
		mainActivity = (PVMPView) getActivity();
		this.buildCategoriesNavigation(rootView);
		this.buildListItemNavigation();
		
		this.navigationMenuIcons.recycle();
		this.categoriesList.setOnItemClickListener(new CategoriesClickListener());
		
		this.adapter = new CategoryDrawerAdapter(mainActivity.getApplicationContext(), this.categoriesDrawerItems);
		
		this.categoriesList.setAdapter(this.adapter);
		this.categoriesList.setBackgroundColor(Color.BLACK);
		
		return rootView;
	}
	
	private void buildCategoriesNavigation(View _view)
	{
		this.navigationMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		this.categoriesList = (ListView) _view.findViewById(R.id.list_categories);
		this.categoriesDrawerItems = new ArrayList<AbstractDrawerItem>();
	}
	
	private void buildListItemNavigation()
	{
		//Adding navigation drawer items to array
		//Proposition
		this.categoriesDrawerItems.add(new CategoryDrawerItem("Saude", 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.CATEGORY, -1)));
		//Party
		this.categoriesDrawerItems.add(new CategoryDrawerItem("Educacao", 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.PARTY, -1)));
		//Feedback
		this.categoriesDrawerItems.add(new CategoryDrawerItem("Tecnologia", 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.FEEDBACK, -1)));
		//Settings
		this.categoriesDrawerItems.add(new CategoryDrawerItem("Cultura", 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.SETTING, -1)));
		//Logout
		this.categoriesDrawerItems.add(new CategoryDrawerItem("Transporte", 
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.LOGOUT, -1)));

		return;
	}
	private class CategoriesClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id)
		{
			mainActivity.displayFragment(ViewObserverInterface.PROPOSITION);
			return;
		}
	}
}

