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
	public static final int ABOUT = 1;
	public static final int FEEDBACK = 2;
	public static final int SETTING = 3;
	public static final int LOGOUT = 4;
	public static final int LOGIN = 5;
	public static final int REGISTER = 6;
	public static final int VISITOR = 7;
	public static final int HOME = 8;
	public static final int EDIT = 9;
	public static final int PROPOSITION = 10;

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
