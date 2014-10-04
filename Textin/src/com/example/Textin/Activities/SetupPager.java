package com.example.Textin.Activities;

import com.example.Textin.Adapters.ViewPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class SetupPager extends FragmentActivity {
	ViewPager _mViewPager;
	ViewPagerAdapter _adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);
		setUpView();
	}

	private void setUpView() {

		_mViewPager = (ViewPager) findViewById(R.id.viewPager);
		_adapter = new ViewPagerAdapter(getApplicationContext(),
				getSupportFragmentManager());
		_mViewPager.setAdapter(_adapter);
		_mViewPager.setCurrentItem(1);
	}

}