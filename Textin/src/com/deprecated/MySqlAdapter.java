package com.deprecated;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.Textin.Activities.Inbox;
import com.example.Textin.Extras.MessageObject;

public class MySqlAdapter {

	static ArrayList<MessageObject> NewMessages = new ArrayList<MessageObject>();

	public static void fetch() {

		final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("no", "+" + Inbox.myNo));

		JSONParser1 jj = new JSONParser1(
				"http://efa.net78.net/test/get_all_messages.php", "POST",
				nameValuePairs);
		// Log.d("dd", "finish2");
		try {

			jj.makeHttpRequest();
			// Log.d("dd", "finish2");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void fetchIt(){
		
		
		
	}
	
	
	



}
