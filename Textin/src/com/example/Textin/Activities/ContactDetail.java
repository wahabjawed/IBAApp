package com.example.Textin.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_detail);

		TextView name = (TextView) findViewById(R.id.name);
		TextView status = (TextView) findViewById(R.id.status);

		name.setText(getIntent().getExtras().get("name").toString());

		status.setText(getIntent().getExtras().get("status").toString());

	}

}
