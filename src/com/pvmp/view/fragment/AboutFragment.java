package com.pvmp.view.fragment;

import com.pvmp.main.R;
import com.pvmp.view.PVMPView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class AboutFragment extends FragmentView 
{
	private TextView aboutTab;
	private ToggleButton button_team, button_dadosAbertos, button_icons;
	private int screenNumber;
	private PVMPView view;
	private Context context;
	private ViewFlipper viewFlipper;
	
	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
			Bundle _savedInstanceState) 
	{
		
		View rootView = _inflater.inflate(R.layout.about_fragment,
				_container, false);
		this.view = (PVMPView) getActivity();
		this.context = this.view.getApplicationContext();
		this.buildScreenComponent(rootView);
		
		return rootView;
	}
		
	@Override
	public void buildScreenComponent(View _view) 
	{
		this.aboutTab = (TextView) _view.findViewById(R.id.about_tab);
		this.button_dadosAbertos = (ToggleButton) _view.findViewById(R.id.toggleButton_dadosAbertos);
		this.button_icons = (ToggleButton) _view.findViewById(R.id.toggleButton_icons);
		this.button_team = (ToggleButton) _view.findViewById(R.id.toggleButton_team);
		this.viewFlipper = (ViewFlipper) _view.findViewById(R.id.about_flipper);
		this.screenNumber = 0;
		
		this.button_team.setChecked(true);
		
		this.button_dadosAbertos.setOnClickListener(new HandleOpenData());
		this.button_icons.setOnClickListener(new HandleIcons());
		this.button_team.setOnClickListener(new HandleTeam());
		
	}
	
	private class HandleTeam implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			button_team.setChecked(true);
			button_icons.setChecked(false);
			button_dadosAbertos.setChecked(false);
			if(screenNumber == 1)
			{
				viewFlipper.setInAnimation(context,
						R.anim.in_from_left);
				viewFlipper.setOutAnimation(context,
						R.anim.out_to_right);

				viewFlipper.showPrevious();
			}
			
			if(screenNumber == 2)
			{
				viewFlipper.setInAnimation(context,
						R.anim.in_from_right);
				viewFlipper.setOutAnimation(context,
						R.anim.out_to_left);

				viewFlipper.showNext();
			}
			screenNumber = 0;
			aboutTab.setText("Equipe");
		}
	}
	
	private class HandleOpenData implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			button_team.setChecked(false);
			button_icons.setChecked(false);
			button_dadosAbertos.setChecked(true);
			
			if(screenNumber == 2)
			{
				viewFlipper.setInAnimation(context,
						R.anim.in_from_left);
				viewFlipper.setOutAnimation(context,
						R.anim.out_to_right);

				viewFlipper.showPrevious();
			}
			
			if(screenNumber == 0)
			{
				viewFlipper.setInAnimation(context,
						R.anim.in_from_right);
				viewFlipper.setOutAnimation(context,
						R.anim.out_to_left);

				viewFlipper.showNext();
			}
			screenNumber = 1;
			aboutTab.setText("Dados Abertos.");
		}
	}
	
	private class HandleIcons implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			button_team.setChecked(false);
			button_icons.setChecked(true);
			button_dadosAbertos.setChecked(false);
			
			if(screenNumber == 0)
			{
				viewFlipper.setInAnimation(context,
						R.anim.in_from_left);
				viewFlipper.setOutAnimation(context,
						R.anim.out_to_right);

				viewFlipper.showPrevious();
			}
			
			if(screenNumber == 1)
			{
				viewFlipper.setInAnimation(context,
						R.anim.in_from_right);
				viewFlipper.setOutAnimation(context,
						R.anim.out_to_left);

				viewFlipper.showNext();
			}
			screenNumber = 2;
			aboutTab.setText("Icones");
		}
	}
	
}
