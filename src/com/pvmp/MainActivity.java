package com.pvmp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;

import com.pvmp.util.Util;
import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.PVMPmodel;
import com.pvmp.controller.ControllerInterface;
import com.pvmp.controller.PVMPController;
import com.pvmp.view.PVMPView;
import android.os.Bundle; 

public class MainActivity extends Activity 
{
	//Controller and Model reference
	private ControllerInterface controller;
	private ModelSubjectInterface model;

	@Override
	public void onCreate(Bundle _savedInstanceState) 
	{
		super.onCreate(_savedInstanceState);
		this.model = new PVMPmodel(getApplicationContext());
		this.controller = new PVMPController();
		
		//Lauch the application
		Intent i  = new Intent();
		i.setClass(this, PVMPView.class);
		i.putExtra("PVMPController", this.controller);
		i.putExtra("PVMPmodel", this.model);
		startActivity(i);
		this.finish();

		return;
	}
}
