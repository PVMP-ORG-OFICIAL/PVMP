package com.pvmp.controller;

import android.content.Context;

public abstract class AbstractController 
{
	protected Context context;
	
	public AbstractController (Context _context) 
	{
		this.context = _context;
	}
}
