package com.pvmp.view;

public interface ViewObserverInterface
{
	//Constants
	public static final int PROPOSITION = 0;
	public static final int PARTY = 1;
	public static final int FEEDBACK = 2;
	public static final int SETTING = 3;
	public static final int LOGOUT = 4;
	public static final int LOGIN = 5;
	public static final int REGISTER = 6;
	public static final int VISITOR = 7;
	public static final int HOME = 8;
	
	public void displayFragment(int _codeFragment);
	public void enableDrawer(boolean _enable);
	public void enableScreenInteraction(boolean _enable);
}
