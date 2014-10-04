package com.example.Textin.Extras;

import java.util.ArrayList;

import android.app.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.Textin.Activities.IBApp;
import com.example.Textin.Activities.R;
import com.example.Textin.Activities.SetupPager;

public class NotificationsManager {

	private static int counter = 0;
	private Activity activity = new Activity();

	public void NewMessageNotification(ArrayList<MessageObject> msgList) {

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				IBApp.context)
				.setSmallIcon(android.R.drawable.stat_notify_more)
				.setContentTitle("My notification")
				.setSound(
						Uri.parse("android.resource://com.example.notificationbar/"
								+ R.raw.notificaitonsound))
				.setContentText("Hello World!" + ++counter);

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(IBApp.context, SetupPager.class);

		// The stack builder object will contain an artificial back
		// stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads
		// out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(IBApp.context);
		// Adds the back stack for the Intent (but not the Intent
		// itself)
		stackBuilder.addParentStack(SetupPager.class);
		// Adds the Intent that starts the Activity to the top of the
		// stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		int notifyID = 1;
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) IBApp.context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(notifyID, mBuilder.build());

	}

}
