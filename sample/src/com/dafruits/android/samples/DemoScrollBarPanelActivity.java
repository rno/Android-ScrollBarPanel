package com.dafruits.android.samples;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dafruits.android.library.widgets.ExtendedListView;
import com.dafruits.android.library.widgets.ExtendedListView.OnPositionChangedListener;

public class DemoScrollBarPanelActivity extends Activity implements OnPositionChangedListener {

	private ExtendedListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mListView = (ExtendedListView) findViewById(android.R.id.list);
		mListView.setAdapter(new DummyAdapter());
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setOnPositionChangedListener(this);
	}

	private class DummyAdapter extends BaseAdapter {

		private int mNumDummies = 100;

		@Override
		public int getCount() {
			return mNumDummies;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(DemoScrollBarPanelActivity.this).inflate(R.layout.list_item, parent,
						false);
			}

			TextView textView = (TextView) convertView;
			textView.setText("" + position);

			return convertView;
		}
	}

	@Override
	public void onPositionChanged(ExtendedListView listView, int firstVisiblePosition, View scrollBarPanel) {
		((TextView) scrollBarPanel).setText("Position " + firstVisiblePosition);
	}
}
