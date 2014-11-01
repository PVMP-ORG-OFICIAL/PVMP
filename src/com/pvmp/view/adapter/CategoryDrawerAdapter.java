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
import android.graphics.Color;

import com.pvmp.R;
import com.pvmp.view.model.AbstractDrawerItem;
import com.pvmp.view.model.CategoryDrawerItem;

/**
* @class CategoryDrawerAdapter
* @brief
*/
@SuppressLint("InflateParams")
public class CategoryDrawerAdapter extends AbstractDrawerAdapter
{
	public CategoryDrawerAdapter(Context _context, ArrayList<AbstractDrawerItem> _categoryDrawerItems)
	{
		super(_context);
		this.drawerItems = _categoryDrawerItems;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent)
	{
		if(_convertView == null)
		{
			LayoutInflater myInflater = (LayoutInflater ) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			_convertView = myInflater.inflate(R.layout.category_list_item, null);
		}
		
		CategoryDrawerItem actualDrawerItem = (CategoryDrawerItem) this.drawerItems.get(_position);
		
		ImageView imageIcon = (ImageView) _convertView.findViewById(R.id.category_icon);
		TextView textTitle = (TextView) _convertView.findViewById(R.id.category_name);
		//ImageView arrowIcon = (ImageView) _convertView.findViewById(R.id.arrow);
		
		imageIcon.setImageResource(actualDrawerItem.getIcon());
		
		textTitle.setText(actualDrawerItem.getTitle());
		textTitle.setTextColor(Color.WHITE);
		
		if (_position == 0)
			_convertView.setBackgroundColor(Color.parseColor("#33B5E5"));
		else if (_position == 1)
			_convertView.setBackgroundColor(Color.parseColor("#AA66CC"));
		else if (_position == 2)
			_convertView.setBackgroundColor(Color.parseColor("#99CC00"));
		else if (_position == 3)
			_convertView.setBackgroundColor(Color.parseColor("#FFBB33"));
		else if (_position == 4)
			_convertView.setBackgroundColor(Color.parseColor("#FF4444"));

		return _convertView;
	}
}
