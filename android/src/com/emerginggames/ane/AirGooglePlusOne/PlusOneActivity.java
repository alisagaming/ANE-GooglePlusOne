package com.emerginggames.ane.AirGooglePlusOne;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.common.*;
import com.google.android.gms.common.GooglePlayServicesClient.*;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.plus.PlusOneButton.OnPlusOneClickListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;

public class PlusOneActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener
{
	private PlusClient mPlusClient;
	private PlusOneButton mPlusOneButton;
	private String mURL;

	// The request code must be 0 or greater.
	private static final int PLUS_ONE_REQUEST_CODE = 0;

	private boolean mHasBeenClicked = false;
	private Button mOkButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		AirGooglePlusOneExtension.log("INFO - PlusOneActivity.onCreate");

		super.onCreate(savedInstanceState);

		// Setup views
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(AirGooglePlusOneExtension.context.getResourceId("layout.googleplusone"));


		mPlusClient = new PlusClient.Builder(this, this, this)
        					.clearScopes()
        					.build();

        mPlusOneButton = (PlusOneButton) findViewById(AirGooglePlusOneExtension.context.getResourceId("id.plus_one_button"));
        mPlusOneButton.setOnPlusOneClickListener(new OnPlusOneClickListener() {
        	@Override
        	public void onPlusOneClick(Intent intent) {
				if (mPlusClient.isConnected()) {
					mHasBeenClicked = true;
					mOkButton.setVisibility(View.VISIBLE);
					startActivityForResult(intent, 0);
				} else {
					mPlusClient.connect();
				}
        	}
        });

        // Get extra values
		Bundle extras = this.getIntent().getExtras();
		mURL = extras.getString("url");

		TextView textView = (TextView) findViewById(AirGooglePlusOneExtension.context.getResourceId("id.plus_one_title"));
		textView.setText(extras.getString("title"));

		mOkButton = (Button) findViewById(AirGooglePlusOneExtension.context.getResourceId("id.ok_button"));
		mOkButton.setText("OK");
		mOkButton.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
        		okAction();
        	}
		});
	}

	public void okAction() {
		finish();
	}

	protected void onResume() {

    	super.onResume();
	    // Refresh the state of the +1 button each time the activity receives focus.
    	mPlusOneButton.initialize(mPlusClient, mURL, PLUS_ONE_REQUEST_CODE);
	}

	@Override
    protected void onStart() {
        super.onStart();
        mPlusClient.connect();
    }

    @Override
    protected void onStop() {
		AirGooglePlusOneExtension.log("INFO - PlusOneActivity.onStop, hasBeenClicked=" + mHasBeenClicked);
		if (mHasBeenClicked) {
			AirGooglePlusOneExtension.context.dispatchStatusEventAsync("GOOGLE_PLUS_ONE_OK", "");
		}
        super.onStop();
        mPlusClient.disconnect();
    }

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		AirGooglePlusOneExtension.log("INFO - PlusOneActivity.onConnectionFailed, result=" + result);
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		AirGooglePlusOneExtension.log("INFO - PlusOneActivity.onConnected");
	}

	@Override
    public void onDisconnected() {
		AirGooglePlusOneExtension.log("INFO - PlusOneActivity.onDisonnected");
    }
}