package com.example.Textin.Activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Textin.Extras.ContactManager;
import com.example.Textin.Extras.SQLHelper;

public class Registration extends AppActivity {
	Button login;
	EditText text;
	Spinner networkCode;
	TextView heading1;
	ProgressBar prog1;
	SQLiteDatabase db;
	String number;
	String code[] = { "331", "315", "332", "300", "346", "333","334","336" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = getMyApplication().db;

		Cursor c = db.rawQuery("SELECT * FROM User", null);
		if (c.getCount() > 0) {

			// db.execSQL("DELETE FROM User");
			startActivity(new Intent(this, SetupPager.class));
			this.finish();

			// setContentView(R.layout.activity_main);
		} else {

			setContentView(R.layout.registration);
			text = (EditText) findViewById(R.id.editText1);
			login = (Button) findViewById(R.id.button1);
			prog1 = (ProgressBar) findViewById(R.id.progressBar1);
			heading1 = (TextView) findViewById(R.id.textView1);

			networkCode = (Spinner) findViewById(R.id.spinner1);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, code);
			// // Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			networkCode.setAdapter(adapter);

			login.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (text.getText().length() == 7) {
						number = "+92"
								+ networkCode.getSelectedItem().toString()
								+ text.getText().toString();
						prog1.setVisibility(1);
						heading1.setVisibility(0);
						networkCode.setEnabled(false);
						login.setEnabled(false);
						text.setEnabled(false);

						new sendSMS().execute();
					} else {

						Toast.makeText(getBaseContext(), "Invalid Number",
								Toast.LENGTH_LONG).show();

					}

					// new getContacts().execute();

				}
			});

			// new getContacts().execute();
		}

	}

	private class getContacts extends AsyncTask<Void, Void, Void> {
		ProgressDialog pd = new ProgressDialog(Registration.this);

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			pd.dismiss();
			startService(new Intent(Registration.this, NetworkService.class));

			startActivity(new Intent(Registration.this, SetupPager.class));
			finish();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pd.setCancelable(false);
			pd.setTitle("Importing Contacts...");
			pd.setIndeterminate(true);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			ContactManager.CopyContactList();
			return null;
		}

	}

	private class sendSMS extends AsyncTask<Void, Void, Void> {
		BroadcastReceiver rec;
		String recNo = "";
		String recMsg = "";
		boolean isRec = false;

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			unregisterReceiver(rec);

			if (isRec) {
				Log.d("no", number);
				db.execSQL("INSERT INTO User(phone,status) VALUES('" + number
						+ "','Available');");
				// db.close();
				IBApp.myNo = SQLHelper.getUser();

				heading1.setText("Importing Contacts...");

				new getContacts().execute();
				// ContactManager.CopyContactList();

			} else {
				prog1.setVisibility(4);
				heading1.setVisibility(4);
				networkCode.setEnabled(true);
				login.setEnabled(true);
				text.setEnabled(true);

			}

			// startActivity(new Intent(Registration.this, SetupPager.class));
			// startService(new Intent(Registration.this,
			// NetworkService.class));
			// finish();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try {
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(number, null, "Hello", null, null);

				final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

				rec = new BroadcastReceiver() {

					@Override
					public void onReceive(Context context, Intent intent) {
						// TODO Auto-generated method stub
						Bundle b = intent.getExtras();
						SmsMessage[] msgs = null;

						if (b != null) {
							Object[] pdus = (Object[]) b.get("pdus");
							msgs = new SmsMessage[pdus.length];
							for (int i = 0; i < msgs.length; i++) {
								msgs[i] = SmsMessage
										.createFromPdu((byte[]) pdus[i]);
								if (i == 0) {
									recNo = msgs[i].getOriginatingAddress();

								}
								recMsg = msgs[i].getMessageBody().toString();
							}

							if (recNo.equals(number)) {

								isRec = true;
								this.abortBroadcast();
							}

						}
					}

				};
				IntentFilter recFil = new IntentFilter(SMS_RECEIVED);
				recFil.setPriority(100);
				registerReceiver(rec, recFil);

				for (int i = 0; i < 10; i++) {
					if (isRec) {
						break;
					}
					Thread.sleep(1000);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
