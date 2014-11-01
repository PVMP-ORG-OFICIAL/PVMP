/**
* @file NavigationDrawerAdapter.java
* @brief 
*/
package com.pvmp.view.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.pvmp.R;
import com.pvmp.view.model.AbstractDrawerItem;
import com.pvmp.view.model.NavigationDrawerItem;

/**
* @class NavigationDrawerAdapter
* @brief
*/
public class NavigationDrawerAdapter extends AbstractDrawerAdapter
{
	public NavigationDrawerAdapter(Context _context, ArrayList<AbstractDrawerItem> _navigationDrawerItems)
	{
		super(_context);
		this.drawerItems = _navigationDrawerItems;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent)
	{
		if(_convertView == null)
		{
			LayoutInflater myInflater = (LayoutInflater ) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			_convertView = myInflater.inflate(R.layout.drawer_list_item, null);
		}
		
		NavigationDrawerItem actualDrawerItem = (NavigationDrawerItem) this.drawerItems.get(_position);
		
		ImageView imageIcon = (ImageView) _convertView.findViewById(R.id.icon);
		TextView textTitle = (TextView) _convertView.findViewById(R.id.title);
		TextView textCount = (TextView) _convertView.findViewById(R.id.counter);
		imageIcon.setImageResource(actualDrawerItem.getIcon());
		textTitle.setText(actualDrawerItem.getTitle());

		//Display count, check wheter it is visible or not
		if(this.drawerItems.get(_position).getCounterVisibility())
		{
			textCount.setText(actualDrawerItem.getCount());
		}
		else
		{
			//Hide the counter view
			textCount.setVisibility(View.GONE);
		}

		return _convertView;
	}
}
