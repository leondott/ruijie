package com.ruijie.rush.base;

import com.ruijie.rush.util.ActivityCollector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView();
		initData();
		initView();
		setListener();
		ActivityCollector.addActivity(this);
		Log.d("BaseActivity", getClass().getSimpleName());
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	
	protected abstract void setContentView();
	
	protected abstract void initView();
	
	protected abstract void initData();
	
	protected abstract void setListener();
	
}
