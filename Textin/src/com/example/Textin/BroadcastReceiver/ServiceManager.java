package com.example.Textin.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.example.Textin.Activities.NetworkService;

public class ServiceManager extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, NetworkService.class);
		context.startService(service);
	}

	@Override
	public IBinder peekService(Context myContext, Intent service) {
		// TODO Auto-generated method stub
		return super.peekService(myContext, service);
	}

}
