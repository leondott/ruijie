package com.ruijie.rush.ui;

import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ruijie.rush.R;
import com.ruijie.rush.base.BaseActivity;
import com.ruijie.rush.util.ActivityInfoParser;

public class MainActivity extends BaseActivity {

	private ArrayAdapter<String> adapter = null;
	private ListView listView = null;
	private String[] listViewData = null;
	private List<ActivityInfo> activityInfos = null;
	
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
		activityInfos = ActivityInfoParser.getActivityInfos(this);
//		listViewData = new String[] {"UsingWebView", "UsingDatabase"};
		listViewData = new String[activityInfos.size()];
		for (int i = 0; i < activityInfos.size(); i++) {
			ActivityInfo activityInfo = activityInfos.get(i);
			listViewData[i] = activityInfo.loadLabel(getPackageManager()).toString();
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewData);
	}

	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (listViewData[position] == getResources().getString(R.string.using_webview)) {
					UsingWebViewActivity.actionStart(MainActivity.this);
				}
				if (listViewData[position] == getResources().getString(R.string.using_database)) {
					UsingDatabaseActivity.actionStart(MainActivity.this);
				}
				if (listViewData[position] == getResources().getString(R.string.using_volley)) {
					UsingVolleyActivity.actionStart(MainActivity.this);
				}
				if (listViewData[position] == getResources().getString(R.string.using_niftydialog)) {
					startActivity(new Intent(MainActivity.this, com.gitonway.lee.niftymodal.MainActivity.class));
				}
			}
		});
	}

}
