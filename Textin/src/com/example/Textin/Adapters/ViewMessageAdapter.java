package com.example.Textin.Adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Textin.Activities.R;
import com.example.Textin.Activities.IBApp;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewMessageAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;

	// public ImageLoader imageLoader;

	public ViewMessageAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		super.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		super.unregisterDataSetObserver(observer);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.row_message, null);
		}
		HashMap<String, String> messageData = new HashMap<String, String>();
		messageData = data.get(position);

		TextView message = null;
		ImageView sender = null;

		if (messageData.get("sender").equals(IBApp.myNo)) {
			Log.d("dd", "0  " + messageData.get("sender"));

			message = (TextView) vi.findViewById(R.id.TextRight); // title
			message.setVisibility(0);
			sender = (ImageView) vi.findViewById(R.id.imageViewRight);
			sender.setVisibility(0);

		} else {
			Log.d("dd", "1  " + messageData.get("sender"));

			message = (TextView) vi.findViewById(R.id.TextLeft); // title
			message.setVisibility(0);
			sender = (ImageView) vi.findViewById(R.id.imageViewLeft);
			sender.setVisibility(0);
		}

		message.setText(messageData.get("message"));

		return vi;
	}
}
