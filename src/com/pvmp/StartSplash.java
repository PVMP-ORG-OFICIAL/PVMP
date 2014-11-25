/**
* @file StartSplash.java
* @brief Keep the Activity that lauch the PVMPView.
* @see PVMPview
* @see http://www.devmedia.com.br/android-mvc-criando-um-framework-model-view-controller-para-android/29924
*/
package com.pvmp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

/**
* @class StartSplash
* @brief 
**/
public class StartSplash extends Activity 
{
	@Override
	public void onCreate(Bundle _savedInstanceState) 
	{
		super.onCreate(_savedInstanceState);

		setContentView(R.layout.splash);

		Thread timer = new Thread(new StartThreadHandler());
		
		timer.start();
		return;
	}

	private class StartThreadHandler implements Runnable
	{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException _exception)
				{
					_exception.printStackTrace();
				}
				finally
				{
					Intent intent = new Intent("com.pvmp.view.PVMPView");
					startActivity(intent);
					finish();
				}
			}
	}
}
