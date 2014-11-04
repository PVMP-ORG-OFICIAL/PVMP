/**
* @file PropositionFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import java.util.ArrayList;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pvmp.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.models.Proposition;
import com.pvmp.util.Util;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.view.adapter.CategoryDrawerAdapter;
import com.pvmp.view.model.AbstractDrawerItem;
import com.pvmp.view.model.CategoryDrawerItem;

/**
*	@class PropositionFragment
* @brief
*/
public class CategoriesFragment extends FragmentView
{
	// --- CATEGORIES NAMES --- \\
	private final static String PUBLIC_ADMINISTRATION = "Administração Pública";
	private final static String HUMAN_RIGHTS = "Direitos Humanos";
	private final static String EDUCATION = "Educação";
	private final static String PUBLIC_FINANCES = "Finanças Públicas";
	private final static String ADMINISTRATIVE_ORGANIZATION = "Organização Administrativa";
	private final static String SOCIAL_FORESIGHT = "Previdência Social";
	private final static String INTERNATIONAL_RELATIONS = "Relações Internacionais";
	private final static String TAXATION = "Tributação";
	private final static String ALL = "Todas";
	
	private ListView categoriesList;
	private ArrayList<AbstractDrawerItem> categoriesDrawerItems;
	private CategoryDrawerAdapter adapter;
	private PVMPView mainActivity;
	private TypedArray navigationMenuIcons;
	private PVMPController controller;
	
	public CategoriesFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.categories_fragment, _container, false);
		
		this.mainActivity = (PVMPView) getActivity();
		this.controller = new PVMPController(this.mainActivity.getApplicationContext());
		this.buildScreenComponent(rootView);
		this.buildListItemNavigation();
		
		this.navigationMenuIcons.recycle();
		this.categoriesList.setOnItemClickListener(new CategoriesClickListener());
		
		this.adapter = new CategoryDrawerAdapter(mainActivity.getApplicationContext(), this.categoriesDrawerItems);
		
		this.categoriesList.setAdapter(this.adapter);
		this.categoriesList.setBackgroundColor(Color.WHITE);
		
		this.mainActivity.enableDrawer(true);
		this.mainActivity.enableScreenInteraction(true);
		
		return rootView;
	}
	
	@Override
	public void buildScreenComponent(View _view) 
	{
		this.navigationMenuIcons = getResources().obtainTypedArray(R.array.category_drawer_icons);
		this.categoriesList = (ListView) _view.findViewById(R.id.list_categories);
		this.categoriesDrawerItems = new ArrayList<AbstractDrawerItem>();
	}
	
	private void buildListItemNavigation()
	{
		//Adding navigation drawer items to array
		//Proposition
		this.categoriesDrawerItems.add(new CategoryDrawerItem(PUBLIC_ADMINISTRATION, 
			this.navigationMenuIcons.getResourceId(0, -1)));
		//Party
		this.categoriesDrawerItems.add(new CategoryDrawerItem(HUMAN_RIGHTS, 
			this.navigationMenuIcons.getResourceId(1, -1)));
		//
		this.categoriesDrawerItems.add(new CategoryDrawerItem(EDUCATION, 
			this.navigationMenuIcons.getResourceId(2, -1)));
		//Feedback
		this.categoriesDrawerItems.add(new CategoryDrawerItem(PUBLIC_FINANCES, 
			this.navigationMenuIcons.getResourceId(3, -1)));
		//Settings
		this.categoriesDrawerItems.add(new CategoryDrawerItem(ADMINISTRATIVE_ORGANIZATION, 
			this.navigationMenuIcons.getResourceId(4, -1)));
		//Logout
		this.categoriesDrawerItems.add(new CategoryDrawerItem(SOCIAL_FORESIGHT, 
			this.navigationMenuIcons.getResourceId(5, -1)));
		//Proposition
		this.categoriesDrawerItems.add(new CategoryDrawerItem(INTERNATIONAL_RELATIONS, 
			this.navigationMenuIcons.getResourceId(6, -1)));
		//Party
		this.categoriesDrawerItems.add(new CategoryDrawerItem(TAXATION, 
			this.navigationMenuIcons.getResourceId(7, -1)));
		//Feedback
		this.categoriesDrawerItems.add(new CategoryDrawerItem(ALL, 
			this.navigationMenuIcons.getResourceId(8, -1)));

		return;
	}
	private class CategoriesClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id)
		{
			PVMPController controller = CategoriesFragment.this.controller;
			String title = CategoriesFragment.this.categoriesDrawerItems.get(_position).getTitle();
			
			ArrayList<Proposition> propositions = controller.getPropositions("Category", title);
			
			for (int i = 0; i < propositions.size(); i++) {
				Proposition prop = new Proposition();
				prop = propositions.get(i);
				
				Util.debug("Prop"+(i+1)+" :"+ prop.getId());
			}
			
			mainActivity.displayFragment(ViewObserverInterface.PROPOSITION);
			return;
		}
	}
}

