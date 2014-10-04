package com.example.Textin.Activities;

import android.app.Activity;

public class AppActivity extends Activity{

	protected IBApp getMyApplication() {
		IBApp tma=(IBApp)getApplication();
		return tma;		
	}
	
}
