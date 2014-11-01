package com.pvmp.controller;

import java.util.ArrayList;

import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.view.ViewObserverInterface;



public class PropositionController implements LegislativeInterface {


  public PropositionController() 
  {
  }


	/**
	* @return _model
	* @brief 
	*/
	@Override
	public ArrayList<LegislativeInterface> listAll(LegislativeInterface object,
			String params) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setModel(ModelSubjectInterface _model) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setView(ViewObserverInterface _view) {
		// TODO Auto-generated method stub
		
	}
}
