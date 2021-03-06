package com.grafian.quran;

import com.grafian.quran.R;
import com.grafian.quran.model.Paging;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends BaseActivity {

	final private static String PAGE = "page";

	private ViewPager mPager;
	private PagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);

		mAdapter = new PagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		int page = Paging.SURA;
		if (savedInstanceState != null) {
			page = savedInstanceState.getInt(PAGE);
		}
		mPager.setCurrentItem(page);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(PAGE, mPager.getCurrentItem());
	}

	@Override
	public void onBackPressed() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		};

		new AlertDialog.Builder(this)
				.setMessage(R.string.confirm_quit)
				.setPositiveButton(R.string.quit, listener)
				.setNegativeButton(R.string.cancel, null)
				.show();
	}

	private class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new BookmarkFragment();
			case 1:
				return new SuraFragment();
			case 2:
				return new PageFragment();
			case 3:
				return new JuzFragment();
			case 4:
				return new HizbFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String titles[] = { "Bookmark", "Sura", "Page", "Juz", "Hizb" };
			return titles[position];
		}

	}

}
