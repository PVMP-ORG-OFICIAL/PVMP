/**
* @file ViewObserverInterface.java
* @brief 
*/
package com.pvmp.view;

/**
*	@class ViewObserverInterface
*	@brief 
*/
public interface ViewObserverInterface
{
	public static final int CATEGORY = 0;
	public static final int SETTING = 1;
	public static final int ABOUT = 2;
	public static final int LOGOUT = 3;
	public static final int LOGIN = 4;
	public static final int REGISTER = 5;
	public static final int VISITOR = 6;
	public static final int HOME = 7;
	public static final int EDIT = 8;
	public static final int PROPOSITION = 9;

	/**
	* @param _codeFragment
	* @brief 
	*/
	public void displayFragment(int _codeFragment);

	/**
	* @param _enable
	* @brief 
	*/
	public void enableDrawer(boolean _enable);

	/**
	* @param
	* @brief 
	*/
	public void enableScreenInteraction(boolean _enable);
}
