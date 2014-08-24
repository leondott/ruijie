package com.ruijie.rush.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ruijie.rush.R;
import com.ruijie.rush.base.BaseActivity;

public class MainActivity extends BaseActivity {

	private ArrayAdapter<String> adapter = null;
	private ListView listView = null;
	private String[] listViewData = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initView() {
		listView = (ListView) findViewById(R.id.lv_main);
		listView.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		listViewData = new String[] {"UsingWebViewActivity"};
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewData);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (listViewData[position] == "UsingWebViewActivity") {
					UsingWebViewActivity.actionStart(MainActivity.this);
				}
			}
		});
	}

}
