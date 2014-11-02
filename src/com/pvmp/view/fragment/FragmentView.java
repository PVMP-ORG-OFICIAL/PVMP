/**
 * @file FragmentView.java
 * @brief
 */

package com.pvmp.view.fragment;

import android.app.Fragment;
import android.view.View;

/**
 * @class FragmentView
 * @brief
 */
public abstract class FragmentView extends Fragment 
{
	/**
	 * @param _view
	 * @brief
	 */
	public abstract void buildScreenComponent(View _view);
	
	
}
