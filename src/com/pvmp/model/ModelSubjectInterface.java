/**
* @file ModelSubjectInterface.java
* @brief
*/
package com.pvmp.model;

import android.content.Context;

/**
* @class ModelSubjectInterface
* @brief 
*/
public interface ModelSubjectInterface
{
	/**
	* @param _context
	* @brief
	*/
	public void setContext(Context _context);

	/**
	* @param _observer
	* @brief
	*/
	public void registerObserver(ListenerObserverInterface _observer);

	/**
	* @param _observer
	* @brief 
	*/
	public void removeObserver(ListenerObserverInterface _observer);
	
	
}
