package com.example.Textin.Adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Textin.Activities.R;
import com.example.Textin.Extras.ContactObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<ContactObject> data;
	private static LayoutInflater inflater = null;

	// public ImageLoader imageLoader;

	public ContactAdapter(Activity a, ArrayList<ContactObject> d) {
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
			vi = inflater.inflate(R.layout.row_contact, null);

		TextView Name = (TextView) vi.findViewById(R.id.name); // contact
		TextView Status = (TextView) vi.findViewById(R.id.status); // message
		TextView Type = (TextView) vi.findViewById(R.id.type); // time
		ImageView Contact_Image = (ImageView) vi.findViewById(R.id.list_image); // thumb
																				// image

		ContactObject contact = data.get(position);

		// Setting all values in listview
		Name.setText(contact.getName());
		Status.setText(contact.getStatus());

		// duration.setText(song.get(Inbox.KEY_DURATION));
		// imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL),
		// thumb_image);
		return vi;
	}
}
