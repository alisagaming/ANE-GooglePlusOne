package com.emerginggames.ane.AirGooglePlusOne;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.emerginggames.ane.AirGooglePlusOne.functions.PlusOneURLFunction;

public class AirGooglePlusOneExtensionContext extends FREContext
{
	@Override
	public void dispose()
	{
		AirGooglePlusOneExtension.context = null;
	}

	@Override
	public Map<String, FREFunction> getFunctions()
	{
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		
		functions.put("plusOneURL", new PlusOneURLFunction());
		
		return functions;	
	}
}