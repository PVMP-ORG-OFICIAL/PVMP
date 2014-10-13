/**
* @file Util.java
* @brief
*/
package com.pvmp.util;

import android.util.Log;

/**
* @class
* @brief
*/
public class Util
{
	private static boolean DEBUG = true;
	private static String TAG = "PVMP";

	/**
	* @param _message
	* @brief 
	*/
	public static void debug(String _message)
	{
		if (DEBUG)
		{
			Log.d(TAG, _message);
		}
		return;
	}

}
