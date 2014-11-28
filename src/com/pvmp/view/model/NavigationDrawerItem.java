package com.pvmp.view.model;

public class NavigationDrawerItem extends AbstractDrawerItem
{
	private String count = "0";

	public NavigationDrawerItem(String _title, int _icon)
	{
		super(_title, _icon);
	}

	public NavigationDrawerItem(String _title, int _icon, boolean _isCounterVisible, String _count)
	{
		super(_title, _icon, _isCounterVisible);
		this.count = _count;
	}

	public String getCount()
	{
		return this.count;
	}

    public void setCount(String _count)
	{
    	this.count = _count;
	}
}
