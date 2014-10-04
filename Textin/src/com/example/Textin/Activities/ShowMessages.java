package com.example.Textin.Activities;

import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.Textin.Adapters.ViewMessageAdapter;
import com.example.Textin.Extras.SQLHelper;
import com.example.Textin.NetworkTask.NetworkTaskManager;
import com.example.Textin.NetworkTask.SendMessage;

public class ShowMessages extends AppActivity {
	EditText TextMessage;
	ListView list;
	ViewMessageAdapter messageAdapter;
	Button Send;
	String No;
	ArrayList<HashMap<String, String>> messageData = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_message);

		No = getIntent().getExtras().getString("contact").toString();

		list = (ListView) findViewById(R.id.listView);
		TextMessage = (EditText) findViewById(R.id.Message);

		Send = (Button) findViewById(R.id.buttonSend);
		Send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!(TextMessage.getText().toString().equals(""))) {
					sendMessage();
					TextMessage.setText("");
					TextMessage.clearFocus();
					list.requestFocus();
				}
			}
		});

		PopulateListView();
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			
				
			}
		});

	}

	private void PopulateListView() {
		// TODO Auto-generated method stub
		Cursor c = SQLHelper.getMessages(No);
		Log.d("dd", "" + c.getCount());
		c.moveToFirst();
		messageData.clear();

		for (int i = 0; i < c.getCount(); i++) {
			// // creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("sender", c.getString(c.getColumnIndex("sender")));
			map.put("message", c.getString(c.getColumnIndex("message")));
			messageData.add(map);
			c.moveToNext();
		}
		//

		messageAdapter = new ViewMessageAdapter(this, messageData);
		list.setAdapter(messageAdapter);
	}

	public void sendMessage() {

		// Log.d("chk", IBApp.myNo + " " + No
		// + " " + TextMessage.getText().toString());
		//
		getMyApplication().db
				.execSQL("Insert into Messages(sender,receiver,message) Values('"
						+ IBApp.myNo
						+ "','"
						+ No
						+ "','"
						+ TextMessage.getText().toString() + "');");

		// messageAdapter.notifyDataSetChanged();
		PopulateListView();
		NetworkTaskManager.AddTask(new SendMessage(No, TextMessage.getText()
				.toString()));
		// SendMessage send = new SendMessage(No, TextMessage
		// .getText().toString());
		// send.PerformTask();

	}

}
