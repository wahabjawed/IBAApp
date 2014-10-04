package com.example.Textin.Activities;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.Textin.NetworkTask.FetchMessages;
import com.example.Textin.NetworkTask.NetworkStatus;
import com.example.Textin.NetworkTask.NetworkTaskManager;
import com.example.Textin.NetworkTask.NetworkTaskManager.NetworkTaskObject;

public class NetworkService extends Service {

	ArrayList<NetworkTaskObject> network = NetworkTaskManager.getTaskQueue();

	NetworkTaskManager t = new NetworkTaskManager();
	int InboxUpdate = 0;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		// if (NetworkStatus.IsConnected()) {
		//
		// NetworkTaskManager.AddTask(new FetchMessages());
		// Log.d("service", "" + NetworkTaskManager.getTaskQueue().size());
		// t.Update();
		// // NetworkTaskManager.getTaskQueue().remove(0);
		// Log.d("service", "" + NetworkTaskManager.getTaskQueue().size());
		//
		// // FetchMessages fetch = new FetchMessages();
		// // NetworkTask t = fetch;
		// // FetchMessages f = (FetchMessages) t;
		// // f.PerformTask();
		// }

		final Handler handler = new Handler();
		final Runnable r = new Runnable() {
			public void run() {

				if (NetworkStatus.IsConnected()) {

					if (InboxUpdate == 1 && FetchMessages.noOfRequest < 2) {
						InboxUpdate = 0;
						NetworkTaskManager.AddTask(new FetchMessages());
						FetchMessages.noOfRequest++;
					}
					Log.d("service", ""
							+ NetworkTaskManager.getTaskQueue().size());
					t.Update();
					// NetworkTaskManager.getTaskQueue().remove(0);
					Log.d("service", ""
							+ NetworkTaskManager.getTaskQueue().size());
					InboxUpdate++;
				} else {
					Log.d("dd", "work");
				}
				// code here what ever is required

				handler.postDelayed(this, 1000 * 5);
			}
		};

		handler.postDelayed(r, 1000 * 6);

		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		NetworkStatus.Setup(getApplicationContext());
		Log.d("service", "start");

		super.onCreate();

	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
