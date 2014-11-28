package com.pvmp.view.model;

public abstract class AbstractDrawerItem {

	private String title;
	private int icon;
	//Boolean to set visibility of the counter
	private boolean isCounterVisible = false;

	public AbstractDrawerItem(String _title, int _icon)
	{
		this.title = _title;
		this.icon = _icon;
	}

	public AbstractDrawerItem(String _title, int _icon, boolean _isCounterVisible)
	{
		this.title = _title;
		this.icon = _icon;
		this.isCounterVisible = _isCounterVisible;
	}

	public final String getTitle()
	{
		return this.title;
	}

	public final int getIcon()
	{
		return this.icon;
	}

	public final boolean getCounterVisibility()
	{
		return this.isCounterVisible;
	}

	public final void setTitle(String _title)
	{
		this.title = _title;
	}

	public final void setIcon(int _icon)
	{
		this.icon = _icon;
	}

	public final void setCounterVisibility(boolean _isCounterVisible)
	{
		this.isCounterVisible = _isCounterVisible;
	}

}
