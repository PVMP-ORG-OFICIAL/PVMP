/**
* @file LoginFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.pvmp.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.controller.UserController;
import com.pvmp.models.User;
import com.pvmp.util.MessageHandling;
import com.pvmp.util.Util;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;

/**
* @class LoginFragment
* @brief
*/
public class LoginFragment extends FragmentView
{

	private EditText editTextUsername;
	private EditText editTextPassword;
	private PVMPView mainActivity;
	private User userToBeLogged;
	private Context context;
	private Button buttonLogin;
	private Button buttonRegister;
	private Button buttonGuest;
	private InputMethodManager imm;

	private PVMPController controller;
	private UserController userController;

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
		this.mainActivity = (PVMPView) getActivity();
		this.context = mainActivity.getApplicationContext();
		this.mainActivity.enableDrawer(false);
		this.mainActivity.enableScreenInteraction(false);
		
		this.userToBeLogged = new User();
		this.controller = new PVMPController(this.context);
		this.controller.setView(LoginFragment.this.mainActivity);
		this.userController = new UserController(this.context);
		//Initialize the elements
		this.buildScreenComponent(rootView);
		
		imm = (InputMethodManager)mainActivity.getSystemService(
          		 Context.INPUT_METHOD_SERVICE);

		return rootView;
	}
	
	@Override
	public void buildScreenComponent (View _view) 
	{
		Util.debug("LoginFragment: Start initialize");
		this.editTextUsername = (EditText) _view.findViewById(R.id.name);
		this.editTextPassword = (EditText) _view.findViewById(R.id.password);
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
			String username = editTextUsername.getText().toString();
			String password = editTextPassword.getText().toString();
			userToBeLogged = LoginFragment.this.userController.verifyMatchingUserPassword(username, password);
			
			if(userToBeLogged != null){
                imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
				userToBeLogged.setDefaultUser("S");
				userController.editUser(userToBeLogged);
				PVMPView.user = userToBeLogged;
				mainActivity.displayFragment(ViewObserverInterface.CATEGORY);
			}
			else{
				userToBeLogged = new User();
				MessageHandling.showToast(MessageHandling.ERROR_LOGIN, context);
				MessageHandling.requestAttention(editTextPassword);
				MessageHandling.requestAttention(editTextUsername);
			}
			
		}
	}
	
	

	private class HandleRegister implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
			mainActivity.displayFragment(ViewObserverInterface.REGISTER);
		}
	}

	private class HandleGuest implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			PVMPView.user = null;
			imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
			Util.debug("VISISTANTE PUSHED!!!");
			mainActivity.displayFragment(ViewObserverInterface.CATEGORY);
		}
	}
	

}
