package com.example.Textin.Adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Textin.Activities.R;
import com.example.Textin.Extras.ContactManager;
import com.example.Textin.Extras.MessageObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InboxAdaptor extends BaseAdapter {

	private Activity activity;
	private ArrayList<MessageObject> data;
	private static LayoutInflater inflater = null;

	// public ImageLoader imageLoader;

	public InboxAdaptor(Activity a, ArrayList<MessageObject> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {

		return position;

	}

	public void Notify() {
		notifyDataSetChanged();

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.row_inbox, null);

		TextView Contact = (TextView) vi.findViewById(R.id.contact); // contact
		TextView Message = (TextView) vi.findViewById(R.id.message); // message
		TextView Time = (TextView) vi.findViewById(R.id.time); // time
		ImageView Contact_Image = (ImageView) vi.findViewById(R.id.list_image); // thumb
																				// image

		MessageObject list = data.get(position);

		// Setting all values in listview
		Contact.setText(list.getName());
		Message.setText(list.getMsg());
		// duration.setText(song.get(Inbox.KEY_DURATION));
		// imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL),
		// thumb_image);
		return vi;
	}
}
