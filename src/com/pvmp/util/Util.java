package com.pvmp.util;

import android.util.Log;

public class Util
{
	private static boolean DEBUG = true;
	private static String TAG = "PVMP";

	public static void debug(String _message)
	{
		if (DEBUG)
		{
			Log.d(TAG, _message);
		}
		return;
	}

}
