package com.emerginggames.ane.AirGooglePlusOne.functions;

import android.content.Intent;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.emerginggames.ane.AirGooglePlusOne.AirGooglePlusOneExtension;

import com.emerginggames.ane.AirGooglePlusOne.PlusOneActivity;

public class PlusOneURLFunction implements FREFunction {

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		
		String title = null;
		String url = null;
		
		try {
			title = arg1[0].getAsString();
			url = arg1[1].getAsString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			e.printStackTrace();
		}
		
		if (title != null && url != null)
		{
			Intent i = new Intent(arg0.getActivity().getApplicationContext(), PlusOneActivity.class);
			i.putExtra("title", title);
			i.putExtra("url", url);
			arg0.getActivity().startActivity(i);
		} else {
			AirGooglePlusOneExtension.log("title/url is null");
		}
		
		return null;
	}

}
