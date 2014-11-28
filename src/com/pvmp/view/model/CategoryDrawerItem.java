package com.pvmp.view.model;

public class CategoryDrawerItem extends AbstractDrawerItem {
	
	private int arrowIcon;
	
	public CategoryDrawerItem(String _title, int _icon)
	{
		super(_title, _icon);
	}

	public CategoryDrawerItem(String _title, int _icon, boolean _isCounterVisible, int _arrowIcon)
	{
		super(_title, _icon, _isCounterVisible);
		this.arrowIcon = _arrowIcon;
	}
	
	public int getArrowIcon()
	{
		return this.arrowIcon;
	}

    public void setArrowIcon(int _arrowIcon)
	{
    	this.arrowIcon = _arrowIcon;
	}

}
