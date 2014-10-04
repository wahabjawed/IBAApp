package com.example.Textin.NetworkTask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {

	// static Context context;
	static ConnectivityManager connManager;

	static NetworkInfo mobile;
	static NetworkInfo mWifi;

	public static void Setup(Context context) {
		connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	}

	public static boolean IsConnected() {
		// boolean isConnect = false;
		if (mWifi.isConnected() || mobile.isConnected()) {
			return true;
		}
		return false;
	}

}
