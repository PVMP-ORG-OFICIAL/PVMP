/**
* @file LoginFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.pvmp.models.User;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.view.PVMPView;
import com.pvmp.util.Util;

import com.pvmp.R;

/**
* @class LoginFragment
* @brief
*/
public class LoginFragment extends Fragment
{

	private EditText userName;
	private EditText password;
	private TextView errorLogin;
	private PVMPView mainActivity;
	//private User userToBeLogged;
	private Context context;
	private Button buttonLogin;
	private Button buttonRegister;
	private Button buttonGuest;

	public LoginFragment()
	{
		//Please, never add any code here. 		
	}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _containter, Bundle _savedInstanceState)
	{
		super.onCreate(_savedInstanceState);
		//Preparing the fragment
		Util.debug("LoginFragment: onCreateView");
		View rootView = _inflater.inflate(R.layout.login_fragment, _containter, false);
		mainActivity = (PVMPView) getActivity();
		context = mainActivity.getApplicationContext();
		//Initialize the elements
		this.initializeDataField(rootView);

		return rootView;
	}
	
	private void initializeDataField (View _view) 
	{
		Util.debug("LoginFragment: Start initialize");
		this.userName = (EditText) _view.findViewById(R.id.name);
		this.password = (EditText) _view.findViewById(R.id.password);
		//this.errorLogin 	= (TextView) _view.findViewById(R.id.textView_errorLogin);
		this.buttonLogin 	= (Button) _view.findViewById(R.id.button_confirm);
		this.buttonGuest 	= (Button) _view.findViewById(R.id.button_entry_guest);
		this.buttonRegister = (Button) _view.findViewById(R.id.button_register);
		Util.debug("LoginFragment: initializeDataField");

		this.buttonLogin.setOnClickListener(new HandleLogin());
		this.buttonRegister.setOnClickListener(new HandleRegister());
		this.buttonGuest.setOnClickListener(new HandleGuest());
		
		return;
	}
	
	private class HandleLogin implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			Util.debug("ENTRAR PUSHED!!!"); 
		}
	}

	private class HandleRegister implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			mainActivity.displayFragment(ViewObserverInterface.REGISTER);
		}
	}

	private class HandleGuest implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			Util.debug("VISISTANTE PUSHED!!!");
		}
	}
}
