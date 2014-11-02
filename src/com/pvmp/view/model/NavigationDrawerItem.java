package com.pvmp.view.model;

public class NavigationDrawerItem
{
	private String title;
	private int icon;
	private String count = "0";
	//Boolean to set visibility of the counter
	private boolean isCounterVisible = false;

	public NavigationDrawerItem()
	{}

	public NavigationDrawerItem(String _title, int _icon)
	{
		this.title = _title;
		this.icon = _icon;
	}

	public NavigationDrawerItem(String _title, int _icon, boolean _isCounterVisible, String _count)
	{
		this.title = _title;
		this.icon = _icon;
		this.isCounterVisible = _isCounterVisible;
		this.count = _count;
	}

	public String getTitle()
	{
		return this.title;
	}

	public int getIcon()
	{
		return this.icon;
	}

	public String getCount()
	{
		return this.count;
	}

	public boolean getCounterVisibility()
	{
		return this.isCounterVisible;
	}

	public void setTitle(String _title)
	{
		this.title = _title;
	}

	public void setIcon(int _icon)
	{
		this.icon = _icon;
	}

  public void setCount(String _count)
	{
	this.count = _count;
	}

	public void setCounterVisibility(boolean _isCounterVisible)
	{
		this.isCounterVisible = _isCounterVisible;
	}

}
