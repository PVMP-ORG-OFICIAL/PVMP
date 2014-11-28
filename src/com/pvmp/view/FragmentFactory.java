package com.pvmp.view;

import android.app.Fragment;

import com.pvmp.view.fragment.AboutFragment;
import com.pvmp.view.fragment.CategoriesFragment;
import com.pvmp.view.fragment.EditSettingsFragment;
import com.pvmp.view.fragment.LoginFragment;
import com.pvmp.view.fragment.PropositionFragment;
import com.pvmp.view.fragment.SettingsFragment;
import com.pvmp.view.fragment.UserRegisterFragment;

/**
 * @class FragmentFactory
 * @brief Creates a specific fragment based on a flag.
 * */
public class FragmentFactory {

	public FragmentFactory() {
	}

	public Fragment generateFragment (int _position) 
	{
		switch(_position)
		{
			case PVMPView.CATEGORY:
				return new CategoriesFragment();
			case PVMPView.ABOUT:
				return new AboutFragment();
			case PVMPView.SETTING:
				return new SettingsFragment();
			case PVMPView.LOGOUT:
			case PVMPView.LOGIN:
				return new LoginFragment();
			case PVMPView.REGISTER:
				return new UserRegisterFragment();
			case PVMPView.EDIT:
				return new EditSettingsFragment();
			case PVMPView.PROPOSITION:
				return new PropositionFragment();
		}
		return null;
	}
}
