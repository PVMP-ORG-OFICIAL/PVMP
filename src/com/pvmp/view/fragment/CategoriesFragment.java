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

import com.pvmp.main.R;
import com.pvmp.controller.PropositionController;
import com.pvmp.controller.VotingController;
import com.pvmp.model.Proposition;
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
	// --- CATEGORIES NAMES ATTRIBUTES --- \\
	private final static String PUBLIC_ADMINISTRATION = "Administração Pública";
	private final static String HUMAN_RIGHTS = "Direitos Humanos";
	private final static String EDUCATION = "Educação";
	private final static String PUBLIC_FINANCES = "Finanças Públicas";
	private final static String ADMINISTRATIVE_ORGANIZATION = "Organização Administrativa";
	private final static String SOCIAL_FORESIGHT = "Previdência e Assistência Social";
	private final static String INTERNATIONAL_RELATIONS = "Relações Internacionais";
	private final static String TAXATION = "Tributação";
	private final static String ALL = "Todas";
	
	// --- OTHER ATTRIBUTES --- \\
	private ListView categoriesList;
	private ArrayList<AbstractDrawerItem> categoriesDrawerItems;
	private CategoryDrawerAdapter adapter;
	private PVMPView mainActivity;
	private TypedArray navigationMenuIcons;
	private PropositionController propositionController;
	private VotingController votingController;
	
	public CategoriesFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.categories_fragment, _container, false);
		
		this.mainActivity = (PVMPView) getActivity();
		this.propositionController = new PropositionController(this.mainActivity.getApplicationContext());
		this.votingController = new VotingController(this.mainActivity.getApplicationContext());
		
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
		//Adding category drawer items to array
		//Public administration
		this.categoriesDrawerItems.add(new CategoryDrawerItem(PUBLIC_ADMINISTRATION, 
			this.navigationMenuIcons.getResourceId(0, -1)));
		//Human rights
		this.categoriesDrawerItems.add(new CategoryDrawerItem(HUMAN_RIGHTS, 
			this.navigationMenuIcons.getResourceId(1, -1)));
		//Education
		this.categoriesDrawerItems.add(new CategoryDrawerItem(EDUCATION, 
			this.navigationMenuIcons.getResourceId(2, -1)));
		//Public Finances
		this.categoriesDrawerItems.add(new CategoryDrawerItem(PUBLIC_FINANCES, 
			this.navigationMenuIcons.getResourceId(3, -1)));
		//Administrative Organization
		this.categoriesDrawerItems.add(new CategoryDrawerItem(ADMINISTRATIVE_ORGANIZATION, 
			this.navigationMenuIcons.getResourceId(4, -1)));
		//Social Foresight
		this.categoriesDrawerItems.add(new CategoryDrawerItem(SOCIAL_FORESIGHT, 
			this.navigationMenuIcons.getResourceId(5, -1)));
		//International Relations
		this.categoriesDrawerItems.add(new CategoryDrawerItem(INTERNATIONAL_RELATIONS, 
			this.navigationMenuIcons.getResourceId(6, -1)));
		//Taxation
		this.categoriesDrawerItems.add(new CategoryDrawerItem(TAXATION, 
			this.navigationMenuIcons.getResourceId(7, -1)));
		//ALL
		this.categoriesDrawerItems.add(new CategoryDrawerItem(ALL, 
			this.navigationMenuIcons.getResourceId(8, -1)));

		return;
	}
	
	private class CategoriesClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id)
		{
			PropositionController propositionController = CategoriesFragment.this.propositionController;
			VotingController votingController = CategoriesFragment.this.votingController;
			String title = null;
			
			if ( _position != 8)
			{
				title = CategoriesFragment.this.categoriesDrawerItems.get(_position).getTitle();
			}
			
			ArrayList<Proposition> propositions = propositionController.getPropositions("Category", title);
			
			votingController.getPropositionsVotings(propositions);
			PVMPView.propositions = propositions;
			
			mainActivity.displayFragment(ViewObserverInterface.PROPOSITION);
			return;
		}
	}
}

