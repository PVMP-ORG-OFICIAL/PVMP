/**
* @file SettingsFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pvmp.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.models.User;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;

/**
*	@class SettingsFragment
* @brief
*/
public class SettingsFragment extends Fragment 
{
	private TextView name;
	private TextView userEmail;
	private TextView userAge;
	private TextView education;
	private TextView sex;
	private TextView userName;
	private Button buttonEdit;
	private Button buttonDelete;
	private static User loggedUser;
	private PVMPView mainActivity; /**<*/
	private Context context; /**<*/
	
	private PVMPController controller;
	
	
	public SettingsFragment()
	{}

	@Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState) 
    {
        View rootView = _inflater.inflate(R.layout.account_settings_fragment, _container, false);
        
        this.mainActivity = (PVMPView) getActivity();
		this.context = mainActivity.getApplicationContext();
		
		this.controller = new PVMPController(context);
		controller.setView(SettingsFragment.this.mainActivity);
		
		loggedUser = this.controller.openSession();
		
		takeDataFromView(rootView);
		setScreenData();
         
        return rootView;
    }
	
	public void takeDataFromView (View _view)
	{
		this.name = (TextView) _view.findViewById(R.id.textView_showName);
		this.userEmail = (TextView) _view.findViewById(R.id.textView_showEmail);
		this.userAge = (TextView) _view.findViewById(R.id.textView_showAge);
		this.education = (TextView) _view.findViewById(R.id.textView_showEducation);
		this.sex = (TextView) _view.findViewById(R.id.textView_showSex);
		this.userName = (TextView) _view.findViewById(R.id.textView_showUsername);
		this.buttonEdit = (Button) _view.findViewById(R.id.button_edit);
		this.buttonDelete = (Button) _view.findViewById(R.id.button_delete);
		
		this.buttonEdit.setOnClickListener(new HandleEdit());
	}
	
	public void setScreenData()
	{
		
		name.setText(loggedUser.getName());
		userEmail.setText(loggedUser.getEmail());
		userAge.setText(Integer.toString((loggedUser.getAge())));
		education.setText(loggedUser.getEducation());
		sex.setText(loggedUser.getSex());
		userName.setText(loggedUser.getUsername());
	}
	
	private class HandleEdit implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			mainActivity.displayFragment(ViewObserverInterface.EDIT);
		}
	}
	
	private class HandleDelete implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			
		}
	}
	
}
