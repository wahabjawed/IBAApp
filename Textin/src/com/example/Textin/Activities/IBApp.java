package com.example.Textin.Activities;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.example.Textin.Extras.SQLHelper;
import com.example.Textin.NetworkTask.NetworkStatus;

public class IBApp extends Application {
	public static SQLiteDatabase db;
	public static Context context;
	public static String myNo="";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		// SQLHelper.SetupSQL(getApplicationContext());
		db = openOrCreateDatabase("app", MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS User(phone TEXT, status TEXT);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Contacts(number TEXT,name TEXT,status TEXT,isUser integer);");
		db.execSQL("CREATE TABLE IF NOT EXISTS Messages(sender TEXT,receiver TEXT,message TEXT);");
		myNo = SQLHelper.getUser();
		context = getApplicationContext();
		NetworkStatus.Setup(getApplicationContext());
		if (!myNo.equals("")) {
			startService(new Intent(this, NetworkService.class));
		}
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		//stopService(new Intent(this, NetworkService.class));

	}

}
