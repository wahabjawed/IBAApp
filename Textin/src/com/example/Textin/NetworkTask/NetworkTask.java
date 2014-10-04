package com.example.Textin.NetworkTask;

import android.database.sqlite.SQLiteDatabase;

import com.example.Textin.Activities.IBApp;

public abstract class NetworkTask {

	
	SQLiteDatabase db= IBApp.db;
	int Success=0;
	
	public abstract void PerformTask();
	
}
