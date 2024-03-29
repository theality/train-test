/************************************************************************************

Filename    :   MainActivity.java
Content     :   
Created     :   
Authors     :   

Copyright   :   Copyright 2014 Oculus VR, LLC. All Rights reserved.

*************************************************************************************/
package com.oculus.vrcubeworld;

import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import com.oculus.vrappframework.VrActivity;

public class MainActivity extends VrActivity {

	public static final String TAG = "VrCubeWorld";
	
	/** Load jni .so on initialization */
	static {
		Log.d( TAG, "LoadLibrary" );
		System.loadLibrary( "vrcubeworld" );
	}

	public static native long nativeSetAppInterface( VrActivity act, String fromPackageNameString, String commandString, String uriString );

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d( TAG, "onCreate" );
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String commandString = VrActivity.getCommandStringFromIntent( intent );
		String fromPackageNameString = VrActivity.getPackageStringFromIntent( intent );
		String uriString = VrActivity.getUriStringFromIntent( intent );

		setAppPtr( nativeSetAppInterface( this, fromPackageNameString, commandString, uriString ) );
	}
}
