package com.example.Textin.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


import com.example.Textin.NetworkTask.NetworkTaskManager;
import com.example.Textin.NetworkTask.SendMessage;

public class Compose extends AppActivity {

	String[] Contacts = new String[100];
	AutoCompleteTextView mltext;

	EditText message;
	String to;
	String from;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compose);
		mltext = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Contacts);
		mltext.setAdapter(adapter);
		message = (EditText) findViewById(R.id.editText1);

		mltext.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				mltext.performCompletion();
				// TODO Auto-generated method stub

			}

		});
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Log.d("chk", IBApp.myNo + " " + mltext.getText().toString()
						+ " " + message.getText());
				send();
			}
		});

	}

	public void send() {

		getMyApplication().db
				.execSQL("Insert into Messages(sender,receiver,message) Values('"
						+ IBApp.myNo
						+ "','"
						+ mltext.getText().toString()
						+ "','" + message.getText().toString() + "');");

		NetworkTaskManager.AddTask(new SendMessage(mltext.getText().toString(), message
				.getText().toString()));
		//SendMessage send = new SendMessage(mltext.getText().toString(), message
			//	.getText().toString());
		//send.PerformTask();

	}

}
