package com.example.Textin.Activities;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.Textin.Adapters.InboxAdaptor;
import com.example.Textin.Extras.ContactManager;
import com.example.Textin.Extras.MessageObject;
import com.example.Textin.Extras.SQLHelper;
import com.example.Textin.NetworkTask.FetchMessages;
import com.example.Textin.NetworkTask.NetworkStatus;

public class Inbox extends Fragment {

	Button b1;
	Activity b;
	ListView list;
	InboxAdaptor adapter;
	ArrayList<MessageObject> msgList = new ArrayList<MessageObject>();
	public static String myNo = IBApp.myNo;
	JSONArray newMsgArr = null;

	public Fragment newInstance(Context context) {
		Inbox f = new Inbox();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.inbox, null);

		b1 = (Button) root.findViewById(R.id.button1);
		list = (ListView) root.findViewById(R.id.listView);
		registerForContextMenu(list);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(IBApp.context, Compose.class));
			}
		});

		if (NetworkStatus.IsConnected()) {

			// Inbox();
		} else {

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()
					.getBaseContext());
			builder.setMessage(
					"Internet Access is not enabled, IBApp will run in Offline mode")
					.setPositiveButton("Continue",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});
			// // Create the AlertDialog object and return it
			AlertDialog create = builder.create();
			// create.show();

		}
		//
		b = getActivity();
		//
		Button b2 = (Button) root.findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Reload();

			}
		});
		//
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(IBApp.context, ShowMessages.class);
				intent.putExtra("contact", msgList.get(position).getNo());
				startActivity(intent);
			}

		});
		Reload();

		return root;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflator = getActivity().getMenuInflater();
		inflator.inflate(R.menu.inbox_long_click, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getItemId() == R.id.delete) {

		}
		return super.onContextItemSelected(item);

	}

	public void Reload() {
		

		Cursor c = SQLHelper.getMessages();
		c.moveToFirst();
		Log.d("dd", "" + c.getCount());
		msgList.clear();

		for (int i = 0; i < c.getCount(); i++) {
			// // creating new HashMap
			MessageObject msg = new MessageObject(c.getString(c
					.getColumnIndex("sender")), ContactManager.getName(c
					.getString(c.getColumnIndex("sender"))), c.getString(c
					.getColumnIndex("message")));

			// HashMap<String, String> map = new HashMap<String, String>();
			// map.put("contact", c.getString(c.getColumnIndex("sender")));
			// map.put("message", c.getString(c.getColumnIndex("message")));
			msgList.add(msg);
			c.moveToNext();
		}
		//
		adapter = new InboxAdaptor(b, msgList);
		adapter.notifyDataSetChanged();
		list.setAdapter(adapter);
		 FetchMessages fetch = new FetchMessages();
		 fetch.PerformTask();
	}

}
