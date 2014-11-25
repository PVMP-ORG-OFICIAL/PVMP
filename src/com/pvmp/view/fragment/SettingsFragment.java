/**
* @file SettingsFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.pvmp.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.controller.UserController;
import com.pvmp.model.User;
import com.pvmp.util.MessageHandling;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;

/**
*	@class SettingsFragment
* @brief
*/
public class SettingsFragment extends FragmentView
{
	private TextView name;
	private TextView userEmail;
	private TextView userAge;
	private TextView education;
	private TextView sex;
	private TextView userName;
	private EditText passwordConfirm;
	private Button buttonEdit;
	private Button buttonDelete;
	private User loggedUser;
	private PVMPView mainActivity; /**<*/
	private Context context; /**<*/
	private boolean firstTime = true;
	private UserController userController;
	
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
		
		this.controller = new PVMPController(this.context);
		this.controller.setView(SettingsFragment.this.mainActivity);
		this.userController = new UserController(this.context);
		
		this.loggedUser = PVMPView.user;
		
		this.buildScreenComponent(rootView);
		this.updateScreenComponent();
         
        return rootView;
    }
	
	public void buildScreenComponent (View _view)
	{
		this.name = (TextView) _view.findViewById(R.id.textView_showName);
		this.userEmail = (TextView) _view.findViewById(R.id.textView_showEmail);
		this.userAge = (TextView) _view.findViewById(R.id.textView_showAge);
		this.education = (TextView) _view.findViewById(R.id.textView_showEducation);
		this.sex = (TextView) _view.findViewById(R.id.textView_showSex);
		this.userName = (TextView) _view.findViewById(R.id.textView_showUsername);
		this.buttonEdit = (Button) _view.findViewById(R.id.button_edit);
		this.buttonDelete = (Button) _view.findViewById(R.id.button_delete);
		this.passwordConfirm = (EditText) _view.findViewById(R.id.passwordVerify);
		
		this.buttonEdit.setOnClickListener(new HandleEdit());
		this.buttonDelete.setOnClickListener(new HandleDelete());
	}
	
	public void updateScreenComponent()
	{
		this.name.setText(loggedUser.getName());
		this.userEmail.setText(loggedUser.getEmail());
		this.userAge.setText(Integer.toString((loggedUser.getAge())));
		this.education.setText(loggedUser.getEducation());
		this.sex.setText(loggedUser.getSex());
		this.userName.setText(loggedUser.getUsername());
	}
	
	private class HandleEdit implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			SettingsFragment.this.mainActivity.displayFragment(ViewObserverInterface.EDIT);
		}
	}
	
	private class HandleDelete implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			String password = SettingsFragment.this.passwordConfirm.getText().toString();
			
			if(firstTime){
				SettingsFragment.this.passwordConfirm.setVisibility(1);
				MessageHandling.showToast(MessageHandling.PASSWORD_CONFIRM_TO_DELETE, context);
				SettingsFragment.this.firstTime = false;
			}
			else if(SettingsFragment.this.loggedUser.getPassword().equals(password))
			{
				SettingsFragment.this.userController.removeUser(loggedUser);
				MessageHandling.showToast(MessageHandling.SUCCESSFUL_DELETE, context);
				SettingsFragment.this.mainActivity.displayFragment(ViewObserverInterface.LOGIN);
			}
			else
			{
				MessageHandling.showToast(MessageHandling.PASSWORD_NOT_MATCH, context);
				MessageHandling.requestAttention(SettingsFragment.this.passwordConfirm);
			}
		}
	}
	
}
