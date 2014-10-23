/**
* @file EditSettingsFragment.java
* @brief
*/
package com.pvmp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.pvmp.controller.PVMPController;
import com.pvmp.dao.UserDAO;
import com.pvmp.models.User;
import com.pvmp.view.PVMPView;
import com.pvmp.view.ErrorHandlingUtil;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.R;

/**
* @class
* @brief
*/
public class EditSettingsFragment extends Fragment
{
	private EditText name;
	private EditText userEmail;
	private EditText userAge;
	private RadioGroup education;
	private RadioGroup sex;
	private EditText userName;
	private EditText oldPassword;
	private EditText newPassword;
	private Button buttonSave;
	private static User loggedUser;
	private PVMPView mainActivity; /**<*/
	private Context context; /**<*/
	
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
		
		this.controller = new PVMPController(context);
		controller.setView(EditSettingsFragment.this.mainActivity);
		
		loggedUser = this.controller.openSession();
		
		takeDataFromView(rootView);
		
		
		return rootView;
  }
	
	public void takeDataFromView (View _view){
		this.name = (EditText) _view.findViewById(R.id.editText_editName);
		this.userEmail = (EditText) _view.findViewById(R.id.editText_editEmail);
		this.userAge = (EditText) _view.findViewById(R.id.editText_editAge);
		this.education = (RadioGroup) _view.findViewById(R.id.radioGroup_editEducation);
		this.oldPassword = (EditText) _view.findViewById(R.id.editText_oldPassword);
		this.newPassword = (EditText) _view.findViewById(R.id.editText_newPassword);
		this.buttonSave = (Button) _view.findViewById(R.id.button_save);
		
		this.buttonSave.setOnClickListener(new HandleSave());
	}
	
	private class HandleSave implements View.OnClickListener
	{
		@Override
		public void onClick(View _view)
		{
			saveUserEdition();
			
			boolean passVerification = passwordsVerification();
			int validationResult = User.validationResult(loggedUser, context);
			
			if (validationResult > 0 && validationResult < 6) {
				ErrorHandlingUtil.displayEditError(userEmail, userName,
												  newPassword, userAge,
												   validationResult, context);
			}
			else if (passVerification) {
				if (!newPassword.getText().toString().equals("")) {
					loggedUser.setPassword(newPassword.getText().toString());
					ErrorHandlingUtil.showToast("Senha alterada com sucesso.", context);
				}
				
				controller.editUser(loggedUser);
				mainActivity.displayFragment(ViewObserverInterface.SETTING);
			}
		}
		
		
	
}
	
	public void saveUserEdition () {
		loggedUser.setName(this.name.getText().toString());
		loggedUser.setEmail(this.userEmail.getText().toString());
		if(this.userAge.getText().toString().equals(""))
			loggedUser.setAge(0);
		else
			loggedUser.setAge(Integer.parseInt(this.userAge.getText().toString()));		
		
		String education_ = null;
		
		switch (this.education.getCheckedRadioButtonId()) {
			case R.id.radio_editElementarySchool:
				education_ = "Fundamental";
				break;
			case R.id.radio_editHighSchool:
				education_ = "Medio";
				break;
			case R.id.radio_editGraduated:
				education_ = "Superior";
				break;	
		}
		
		loggedUser.setEducation(education_);
	}
	
	public boolean passwordsVerification () {
		String oldP = oldPassword.getText().toString();
		String newP = newPassword.getText().toString();
		
		if (!oldP.equals("")) {
			if (oldP.equals(loggedUser.getPassword())) {
				if(User.validatePasswordSize(newP)) {
					if (User.validatePasswordFormat(newP))
						return true;
					else {
						ErrorHandlingUtil.genericError(newPassword, "Sua senha deve ser formada apenas por letras e números.", context);
						return false;
					}
					
				}
				else {
					ErrorHandlingUtil.genericError(newPassword, "Sua senha deve ter de 6 a 15 caracteres.", context);
					return false;
				}
			}
			else {
				ErrorHandlingUtil.genericError(oldPassword, "Senha antiga não correspondente.", context);
				return false;
			}
		}
		else {
			if(!newP.equals("")) {
				ErrorHandlingUtil.genericError(oldPassword, "Senha antiga não correspondente.", context);
				return false;
			}		
		}
		return true;
	}
}



