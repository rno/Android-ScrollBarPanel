package com.dafruits.android.samples;

import com.dafruits.android.library.widgets.ExtendedListView;
import com.dafruits.android.samples.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;

public class DemoScrollBarPanelActivity extends Activity {
	
	private ExtendedListView mListView;
	private TextView mScrollBarPanel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		mListView = (ExtendedListView) findViewById(android.R.id.list);
		mScrollBarPanel = (TextView) LayoutInflater.from(this).inflate(R.layout.scrollbarpanel, mListView, false);
		mListView.setScrollBarPanel(mScrollBarPanel);
		mListView.setAdapter(new DummyAdapter());
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				mScrollBarPanel.setText("Position " + firstVisibleItem);
			}
		});
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
				convertView = LayoutInflater.from(DemoScrollBarPanelActivity.this).inflate(R.layout.list_item, parent, false);
			}
			
			TextView textView = (TextView) convertView;
			textView.setText("" + position);
			
			return convertView;
		}
	}
}
