package com.example.Textin.NetworkTask;

import java.util.ArrayList;

import android.util.Log;

import com.example.Textin.Activities.Inbox;

public class NetworkTaskManager {

	static ArrayList<NetworkTaskObject> TaskQueue = new ArrayList<NetworkTaskObject>();

	public static ArrayList<NetworkTaskObject> getTaskQueue() {
		return TaskQueue;
	}

	public static void AddTask(NetworkTask task) {
		TaskQueue.add(new NetworkTaskObject(task));
	}

	public void Update() {

		
		
		for (int i=0;i<TaskQueue.size();i++) {
			NetworkTaskObject task=TaskQueue.get(i);
			
			if (task.Task.Success ==1) {
				TaskQueue.remove(i);

			} else if (!task.IsRunning) {
				task.IsRunning = true;
				task.Task.PerformTask();
				Log.d("dd","work");
			}
			
		}
	}

	public static class NetworkTaskObject {
		NetworkTask Task;
		boolean IsRunning;
		int Time;

		public NetworkTaskObject(NetworkTask task) {
			// TODO Auto-generated constructor stub
			Task = task;
			IsRunning = false;
			Time = 0;
		}
	}
}
