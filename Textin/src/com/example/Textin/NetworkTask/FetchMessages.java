package com.example.Textin.NetworkTask;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.Textin.Activities.IBApp;
import com.example.Textin.Activities.Inbox;

public class FetchMessages extends NetworkTask {

	public static int noOfRequest = 0;

	@Override
	public void PerformTask() {
		// TODO Auto-generated method stub
		new Task().execute();

	}

	class Task extends AsyncTask<Void, Void, JSONObject> {

		JSONArray newMsgArr = null;

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			try {

				JSONObject json_data = result;

				int success = json_data.getInt("success");

				if (success == 1) {
					Success = 1;
					newMsgArr = json_data.getJSONArray("message");

					for (int i = 0; i < newMsgArr.length(); i++) {
						JSONObject c = newMsgArr.getJSONObject(i);

						String from = c.getString("from");
						String message = c.getString("message");
						Log.d("dd", from + " " + Inbox.myNo + " " + message);
						db.execSQL("INSERT INTO Messages(sender, receiver, message) VALUES('"
								+ from
								+ "','"
								+ IBApp.myNo
								+ "','"
								+ message
								+ "');");

					}
					Success = 1;
					noOfRequest--;
				}

			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub

			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("no", Inbox.myNo));
			JSONParser getData = new JSONParser();
			JSONObject result = getData.makeHttpRequest(
					"http://efa.net78.net/test/get_all_messages.php", "POST",
					nameValuePairs);

			return result;

		}
	}

}
