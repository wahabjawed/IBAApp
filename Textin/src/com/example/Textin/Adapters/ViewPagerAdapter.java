package com.example.Textin.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.Textin.Activities.ContactList;
import com.example.Textin.Activities.Inbox;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private Context _context;

	public ViewPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		_context = context;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment f = new Fragment();
		switch (position) {
		case 0:
			f = new ContactList().newInstance(_context);
			break;
		case 1:
			f = new Inbox().newInstance(_context);
			break;

		}
		return f;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
