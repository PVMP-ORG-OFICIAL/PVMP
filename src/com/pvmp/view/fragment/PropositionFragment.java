/**
* @file PropositionFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pvmp.R;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.view.adapter.NavigationDrawerAdapter;
import com.pvmp.view.model.NavigationDrawerItem;

/**
*	@class PropositionFragment
* @brief
*/
public class PropositionFragment extends Fragment 
{

	private ListView categoriesList;
	private ArrayList<NavigationDrawerItem> categoriesDrawerItems;
	private NavigationDrawerAdapter adapter;
	private PVMPView mainActivity;
	private TypedArray navigationMenuIcons;

	public PropositionFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.proposition_fragment, _container, false);

		mainActivity = (PVMPView) getActivity();
		this.buildCategoriesNavigation(rootView);
		this.buildListItemNavigation();

		this.navigationMenuIcons.recycle();
		this.categoriesList.setOnItemClickListener(new CategoriesClickListener());

		this.adapter = new NavigationDrawerAdapter(mainActivity.getApplicationContext(), this.categoriesDrawerItems);
		this.categoriesList.setAdapter(this.adapter);


		return rootView;
	}

	private void buildCategoriesNavigation(View _view)
	{
		this.navigationMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		this.categoriesList = (ListView) _view.findViewById(R.id.list_categories);
		this.categoriesDrawerItems = new ArrayList<NavigationDrawerItem>();
	}

	private void buildListItemNavigation()
	{
		//Adding navigation drawer items to array
		//Proposition
		this.categoriesDrawerItems.add(new NavigationDrawerItem("Saude",
		this.navigationMenuIcons.getResourceId(ViewObserverInterface.PROPOSITION, -1)));
		//Party
		this.categoriesDrawerItems.add(new NavigationDrawerItem("Educacao",
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.PARTY, -1)));
		//Feedback
		this.categoriesDrawerItems.add(new NavigationDrawerItem("Tecnologia",
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.FEEDBACK, -1)));
		//Settings
		this.categoriesDrawerItems.add(new NavigationDrawerItem("Cultura",
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.SETTING, -1)));
		//Logout
		this.categoriesDrawerItems.add(new NavigationDrawerItem("Transporte",
			this.navigationMenuIcons.getResourceId(ViewObserverInterface.LOGOUT, -1)));

		return;
	}
	private class CategoriesClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id)
		{
			//Display view for selected navigation item
			categoriesList.setItemChecked(_position, true);
			categoriesList.setSelection(_position);
			mainActivity.displayFragment(ViewObserverInterface.FEEDBACK);

			return;
		}
	}
}

