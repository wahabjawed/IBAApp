package com.example.Textin.NetworkTask;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.Textin.Activities.IBApp;

public class SendMessage extends NetworkTask {

	String Receiver;
	String Message;

	public SendMessage(String reciever, String message) {
		Receiver = reciever;
		Message = message;
	}

	@Override
	public void PerformTask() {
		// TODO Auto-generated method stub
		new Task().execute();

	}

	class Task extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			// TODO Auto-generated method stub

			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("to", Receiver));
			nameValuePairs
					.add(new BasicNameValuePair("from",IBApp.myNo));
			nameValuePairs.add(new BasicNameValuePair("message", Message));
			

			JSONParser getData = new JSONParser();
			JSONObject result = getData.makeHttpRequest(
					"http://efa.net78.net/test/send_message.php", "POST",
					nameValuePairs);
			Log.d("dd", "finish2");
			Success=1;
			return result;
		}

	}
}