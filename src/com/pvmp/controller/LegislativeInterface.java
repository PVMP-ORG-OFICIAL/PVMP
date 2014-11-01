package com.pvmp.controller;

import java.util.ArrayList;

import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.view.ViewObserverInterface;


public interface LegislativeInterface{


  public void setModel(ModelSubjectInterface _model);

  /**
   * @param _view
   * @brief
   */
  public void setView(ViewObserverInterface _view);


  public ArrayList<LegislativeInterface> listAll (LegislativeInterface object, String params);


}

