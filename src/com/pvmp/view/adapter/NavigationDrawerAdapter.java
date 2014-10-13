/**
* @file NavigationDrawerAdapter.java
* @brief 
*/
package com.pvmp.view.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.pvmp.R;
import com.pvmp.view.model.NavigationDrawerItem;

/**
* @class
* @brief
*/
public class NavigationDrawerAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<NavigationDrawerItem> navigationDrawerItems;

	/**
	* @param _context
	* @param _navigationDrawerItems
	* @brief 
	*/
	public NavigationDrawerAdapter(Context _context, ArrayList<NavigationDrawerItem> _navigationDrawerItems)
	{
		this.context = _context;
		this.navigationDrawerItems = _navigationDrawerItems;
	}

	@Override
	public int getCount()
	{
		return navigationDrawerItems.size();
	}

	@Override
	public Object getItem(int _position)
	{
		return navigationDrawerItems.get(_position);
	}

	@Override
	public long getItemId(int _position)
	{
		return _position;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent)
	{
		if(_convertView == null)
		{
			LayoutInflater myInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			_convertView = myInflater.inflate(R.layout.drawer_list_item, null);
		}

		ImageView imageIcon = (ImageView) _convertView.findViewById(R.id.icon);
		TextView textTitle = (TextView) _convertView.findViewById(R.id.title);
		TextView textCount = (TextView) _convertView.findViewById(R.id.counter);
		imageIcon.setImageResource(this.navigationDrawerItems.get(_position).getIcon());
		textTitle.setText(this.navigationDrawerItems.get(_position).getTitle());

		//Display count, check wheter it is visible or not
		if(this.navigationDrawerItems.get(_position).getCounterVisibility())
		{
			textCount.setText(this.navigationDrawerItems.get(_position).getCount());
		}
		else
		{
			//Hide the counter view
			textCount.setVisibility(View.GONE);
		}

		return _convertView;
	}
}
