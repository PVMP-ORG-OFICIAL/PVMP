/**
	* @file UserRegisterFragment
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
import android.widget.RadioGroup;

import com.pvmp.main.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.controller.UserController;
import com.pvmp.model.User;
import com.pvmp.util.MessageHandling;
import com.pvmp.util.Util;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;

/**
* @class UserRegisterFragment
* @brief 
*/
public class UserRegisterFragment extends FragmentView
{	
	private EditText name;
	private EditText userEmail;
	private EditText userAge;
	private RadioGroup education;
	private RadioGroup sex;
	private EditText userName;
	private EditText userPassword;
	private EditText userPasswordConfirm;
	private static User userBuild;
	private PVMPView mainActivity; /**<*/
	public Context context; /**<*/
	private Button register;
	private InputMethodManager imm;
	private UserController userController;

	private PVMPController controller;
	
	public UserRegisterFragment()
	{}
	
	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _containter,	Bundle _savedInstanceState) 
	{
		super.onCreate(_savedInstanceState);
		//Preparing the fragment
		View rootView = _inflater.inflate(R.layout.user_register_fragment, _containter, false);
		this.mainActivity = (PVMPView) getActivity();
		this.context = mainActivity.getApplicationContext();
		
		this.controller = new PVMPController(context);
		this.userController = new UserController(context);
		controller.setView(UserRegisterFragment.this.mainActivity);

		userBuild = new User();
		
		imm = (InputMethodManager)mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		buildScreenComponent(rootView);
		return rootView;
	}
	
	@Override
	public void onResume () {
		super.onResume();
		this.mainActivity.enableDrawer(false);
		this.mainActivity.enableScreenInteraction(false);
	}
	
	/**
	* @param _view
	* @brief 
	*/
	public void buildScreenComponent (View _view)
	{
		this.name = (EditText) _view.findViewById(R.id.name);
		this.userEmail = (EditText) _view.findViewById(R.id.email);
		this.userAge = (EditText) _view.findViewById(R.id.age_user);
		this.education = (RadioGroup) _view.findViewById(R.id.radiogroup_education);
		this.sex = (RadioGroup) _view.findViewById(R.id.radiogroup_sex);
		this.userName = (EditText) _view.findViewById(R.id.user_login);
		this.userPassword = (EditText) _view.findViewById(R.id.password);
		this.userPasswordConfirm = (EditText) _view.findViewById(R.id.password_confirm);
		this.register = (Button) _view.findViewById(R.id.confirm);
	
		this.register.setOnClickListener(new HandleRegister());

		return;
	}

	private class HandleRegister implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			updateScreenComponent();
			Util.debug("UserRegisterFragment: Register button clicked!!");
			if(userPassword.getText().toString().equals(userPasswordConfirm.getText().toString()))
			{
				int validationResult = userBuild.validationResult(userBuild, context);

				switch(validationResult) 
				{
					case 0:
						imm.hideSoftInputFromWindow(userPassword.getWindowToken(), 0);
						userBuild.setDefaultUser("S");
						userController.saveUser(userBuild);
						PVMPView.user = userBuild;
						controller.callDisplayFragment(ViewObserverInterface.CATEGORY);
						MessageHandling.showToast(MessageHandling.SUCCESSFUL_REGISTER, context);
						return;
					
					default:
						MessageHandling.displayRegisterError(userName, userEmail, name, userPassword, 
								userAge, validationResult, context);
						break;
				}
			}
			else
			{
				MessageHandling.showToast(MessageHandling.PASSWORD_CONFIRM_NOT_MATCH, context);
				MessageHandling.requestAttention(userPasswordConfirm);
				MessageHandling.requestAttention(userPassword);
			}
		}
	}

	/**
	* @brief
	*/
	public void updateScreenComponent() 
	{
		String education = null;
		String sex = null;
		
		userBuild.setName(this.name.getText().toString());
		userBuild.setEmail(this.userEmail.getText().toString());
		if (this.userAge.getText().toString().equals(""))
		{
			userBuild.setAge(0);
		}
		else
		{
			userBuild.setAge(Integer.parseInt(this.userAge.getText().toString()));
		}
		userBuild.setUsername(this.userName.getText().toString());
		userBuild.setPassword(this.userPassword.getText().toString());
	
		switch (this.education.getCheckedRadioButtonId()) 
		{
			case R.id.radio_elementarySchool:
				education = "Fundamental";
				break;
			case R.id.radio_highSchool:
				education = "Ensino Medio";
				break;
			case R.id.radio_graduated:
				education = "Superior";
				break;
		}
		userBuild.setEducation(education);
		
		switch(this.sex.getCheckedRadioButtonId()) 
		{
			case R.id.radio_male:
				sex = "Masculino";
				break;
			case R.id.radio_female:
				sex = "Feminino";
				break;
		}
		userBuild.setSex(sex);

		return;
	}
}

