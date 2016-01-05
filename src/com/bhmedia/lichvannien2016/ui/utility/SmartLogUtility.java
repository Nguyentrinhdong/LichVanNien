package com.bhmedia.lichvannien2016.ui.utility;

import android.util.Log;

/**
 * 
 * @author Administrator
 * 
 *         buid the log method
 * 
 */
public class SmartLogUtility {
	// ================Log.d=======================//

	public static void logD( String message) {

		Log.d("test", message);

	}

	// ================Log.e=======================//

	public static void logE(String TAG, String message) {

		Log.e(TAG, message);

	}

	// ================Log.i=======================//

	public static void logI(String TAG, String message) {
		
		Log.i(TAG, message);
		
	}
}
