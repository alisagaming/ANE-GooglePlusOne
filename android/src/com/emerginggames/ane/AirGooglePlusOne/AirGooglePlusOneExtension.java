package com.emerginggames.ane.AirGooglePlusOne;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AirGooglePlusOneExtension implements FREExtension
{
	public static String TAG = "AirGooglePlusOne";
	
	public static FREContext context;

	public FREContext createContext(String extId)
	{
		return context = new AirGooglePlusOneExtensionContext();
	}

	public void dispose()
	{
		context = null;
	}
	
	public void initialize() {}
	
	public static void log(String message)
	{
		Log.d(TAG, message);
	}
}
