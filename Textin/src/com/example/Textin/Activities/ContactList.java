package com.example.Textin.Activities;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Textin.Adapters.ContactAdapter;
import com.example.Textin.Adapters.InboxAdaptor;
import com.example.Textin.Extras.ContactObject;
import com.example.Textin.Extras.SQLHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ContactList extends Fragment {

	ListView ContactList;
	Activity b;

	ContactAdapter adapter;
	ArrayList<ContactObject> contactList = new ArrayList<ContactObject>();

	public Fragment newInstance(Context context) {
		ContactList f = new ContactList();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.contact_list,
				null);
		ContactList = (ListView) root.findViewById(R.id.contactList);

		Cursor c = SQLHelper.getContacts();
		if (c.getCount() > 0) {
			c.moveToFirst();
			Log.d("dd", "" + c.getCount());
			contactList.clear();

			for (int i = 0; i < c.getCount(); i++) {
				// // creating new HashMap

				// HashMap<String, String> map = new HashMap<String, String>();
				// map.put("name", c.getString(c.getColumnIndex("name")));
				// map.put("status", c.getString(c.getColumnIndex("status")));

				ContactObject contact = new ContactObject(c.getString(c
						.getColumnIndex("name")), c.getString(c
						.getColumnIndex("number")), c.getString(c
						.getColumnIndex("status")), (c.getInt(c
						.getColumnIndex("isUser"))));

				contactList.add(contact);
				c.moveToNext();
			}
			//
			b = getActivity();
			adapter = new ContactAdapter(b, contactList);
			adapter.notifyDataSetChanged();
			ContactList.setAdapter(adapter);

			ContactList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView parentview, View view,
						int position, long id) {
					// TODO Auto-generated method stub

//					Intent contactDetail = new Intent(IBApp.context,
//							ContactDetail.class);
//
//					contactDetail.putExtra("name", contactList.get(position)
//							.getName());
//					contactDetail.putExtra("status", contactList.get(position)
//							.getStatus());
//
//					contactDetail.putExtra("number", contactList.get(position)
//							.getNo());
//
//					startActivity(contactDetail);
				
				
					Intent intent = new Intent(IBApp.context, ShowMessages.class);
					intent.putExtra("contact", contactList.get(position).getNo());
					startActivity(intent);
				
				}
			});
		}
		return root;
	}

}
