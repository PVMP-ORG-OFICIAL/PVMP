package com.pvmp.view.adapter;

import java.util.ArrayList;

import com.pvmp.view.model.AbstractDrawerItem;
import com.pvmp.view.model.NavigationDrawerItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AbstractBaseAdapter extends BaseAdapter {

	protected Context context;
	protected ArrayList<AbstractDrawerItem> drawerItems;
	
	/**
	* @param _context
	* @param _navigationDrawerItems
	* @brief 
	*/
	public AbstractBaseAdapter(Context _context)
	{
		this.context = _context;
	}
	
	@Override
	public final int getCount() 
	{
		return drawerItems.size();
	}

	@Override
	public final Object getItem(int _position) 
	{
		return drawerItems.get(_position);
	}

	@Override
	public final long getItemId(int _position) 
	{
		return _position;
	}

	@Override
	public abstract View getView(int _position, View _convertView, ViewGroup _parent);

}
