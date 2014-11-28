/**
* @file EditSettingsFragment.java
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
import com.pvmp.view.PVMPView;
import com.pvmp.view.ViewObserverInterface;

/**
* @class
* @brief
*/
public class EditSettingsFragment extends FragmentView
{
	private EditText name;
	private EditText userEmail;
	private EditText userAge;
	private RadioGroup education;
	private RadioGroup sex;
	private EditText oldPassword;
	private EditText newPassword;
	private Button buttonSave;
	private User loggedUser;
	private PVMPView mainActivity; /**<*/
	private Context context; /**<*/
	private InputMethodManager imm;
	private UserController userController;
	
	private PVMPController controller;
	
	public EditSettingsFragment()
	{}

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
            Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.edit_settings_fragment, _container, false);
		
		this.mainActivity = (PVMPView) getActivity();
		this.context = mainActivity.getApplicationContext();
		
		this.controller = new PVMPController(this.context);
		this.controller.setView(EditSettingsFragment.this.mainActivity);
		this.userController = new UserController(this.context);
		
		this.loggedUser = PVMPView.user;
		
		this.buildScreenComponent(rootView);
		this.initialUpdateScreenComponent();
		
		imm = (InputMethodManager)mainActivity.getSystemService(
         		 Context.INPUT_METHOD_SERVICE);
		
		return rootView;
  }
	
	@Override
	public void buildScreenComponent (View _view)
	{
		this.name = (EditText) _view.findViewById(R.id.editText_editName);
		this.userEmail = (EditText) _view.findViewById(R.id.editText_editEmail);
		this.userAge = (EditText) _view.findViewById(R.id.editText_editAge);
		this.education = (RadioGroup) _view.findViewById(R.id.radioGroup_editEducation);
		this.sex = (RadioGroup) _view.findViewById(R.id.radiogroup_editSex);
		this.oldPassword = (EditText) _view.findViewById(R.id.editText_oldPassword);
		this.newPassword = (EditText) _view.findViewById(R.id.editText_newPassword);
		this.buttonSave = (Button) _view.findViewById(R.id.button_save);
		
		this.buttonSave.setOnClickListener(new HandleSave());
		
		return;
	}
	
	public void initialUpdateScreenComponent()
	{
		this.name.setText(loggedUser.getName());
		this.userEmail.setText(loggedUser.getEmail());
		this.userAge.setText(Integer.toString(loggedUser.getAge()));
		
		char education;
		if(loggedUser.getEducation().equals("Fundamental"))
			education = 'F';
		else if(loggedUser.getEducation().equals("Ensino Medio"))
			education = 'M';
		else
			education = 'S';
		
		switch(education)
		{
			case 'F':
				this.education.check(R.id.radio_editElementarySchool);
				break;
			case 'M':
				this.education.check(R.id.radio_editHighSchool);
				break;
			case 'S':
				this.education.check(R.id.radio_editGraduated);
				break;
		}
		
		char sex;
		if(loggedUser.getSex().equals("Masculino"))
			sex = 'M';
		else
			sex = 'F';
		
		switch (sex)
		{
			case 'M':
				this.sex.check(R.id.radio_editMale);
				break;
			case 'F':
				this.sex.check(R.id.radio_editFemale);
				break;
		}
		
		return;
	}
	
	private class HandleSave implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			updateScreenComponent();
			
			boolean passVerification = passwordsVerification();
			int validationResult = loggedUser.validationResult(loggedUser, context);
			
			if (validationResult > 0 && validationResult <= 6)
			{
				MessageHandling.displayEditError(userEmail, name,
												  newPassword, userAge,
												   validationResult, context);
			}
			else if (passVerification)
			{
				if (!newPassword.getText().toString().equals(""))
				{
					loggedUser.setPassword(newPassword.getText().toString());
					MessageHandling.showToast(MessageHandling.PASSWORD_SUCCESSFUL_CHANGE, context);
				}
				
				imm.hideSoftInputFromWindow(newPassword.getWindowToken(), 0);
				userController.editUser(loggedUser);
				mainActivity.displayFragment(ViewObserverInterface.SETTING);
			}
		}
	}
	
	public void updateScreenComponent () 
	{
		loggedUser.setName(this.name.getText().toString());
		loggedUser.setEmail(this.userEmail.getText().toString());
		if(this.userAge.getText().toString().equals(""))
			loggedUser.setAge(0);
		else
			loggedUser.setAge(Integer.parseInt(this.userAge.getText().toString()));		
		
		String education_ = null;
		
		switch (this.education.getCheckedRadioButtonId()) 
		{
			case R.id.radio_editElementarySchool:
				education_ = "Fundamental";
				break;
			case R.id.radio_editHighSchool:
				education_ = "Ensino Medio";
				break;
			case R.id.radio_editGraduated:
				education_ = "Superior";
				break;	
		}
		
		loggedUser.setEducation(education_);
		
		String sex_ = null;
		
		switch(this.sex.getCheckedRadioButtonId()) 
		{
			case R.id.radio_editMale:
				sex_ = "Masculino";
				break;
			case R.id.radio_editFemale:
				sex_ = "Feminino";
				break;
		}
		
		loggedUser.setSex(sex_);
	}
	
	public boolean passwordsVerification ()
	{
		String oldP = oldPassword.getText().toString();
		String newP = newPassword.getText().toString();
		
		if (!oldP.equals(""))
		{
			if (oldP.equals(loggedUser.getPassword()))
			{
				if(User.validatePasswordSize(newP))
				{
					if (User.validatePasswordFormat(newP))
						return true;
					else 
					{
						MessageHandling.genericError(newPassword, MessageHandling.PASSWORD_FORMAT, context);
						return false;
					}
					
				}
				else
				{
					MessageHandling.genericError(newPassword, MessageHandling.PASSWORD_LENGHT, context);
					return false;
				}
			}
			else 
			{
				MessageHandling.genericError(oldPassword, MessageHandling.PASSWORD_NOT_MATCH, context);
				return false;
			}
		}
		else 
		{
			if(!newP.equals(""))
			{
				MessageHandling.genericError(oldPassword, MessageHandling.PASSWORD_NOT_MATCH, context);
				return false;
			}		
		}
		return true;
	}
}



