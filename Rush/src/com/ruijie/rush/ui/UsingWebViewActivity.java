package com.ruijie.rush.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ruijie.rush.R;
import com.ruijie.rush.base.BaseActivity;
import com.ruijie.rush.receiver.SmsMessageReceiver;
import com.ruijie.rush.util.Constants;

public class UsingWebViewActivity extends BaseActivity {

	public static final String URL_LOCAL_HTML = "file:///android_asset/html/index.html";
	
	private WebView webView = null;
	private SmsMessageReceiver receiver = null;
	
	@Override
	protected void onResume() {
		super.onResume();
		registerSmsMessageReceiver();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unRegisterSmsMessageReceiver();
	}
	
	@Override
	protected void setContentView() {
		setContentView(R.layout.act_using_webview);
	}

	@SuppressLint("SetJavaScriptEnabled") @Override
	protected void initView() {
		webView = (WebView) findViewById(R.id.wv_content);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
			
		});
		webView.loadUrl(URL_LOCAL_HTML);
	}

	@Override
	protected void initData() {
		receiver = new SmsMessageReceiver() {
			@Override
			protected void updateUI(String content) {
				if (!TextUtils.isEmpty(content)) {
					webView.loadUrl("javascript:document.getElementById('msg').value = '" + content + "'");
				}
			}
		};
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}
	
	private void registerSmsMessageReceiver(){
		IntentFilter filter = new IntentFilter(Constants.ACTION_SMS_RECEIVED);
		filter.setPriority(Constants.PRIORITY_HIGH);
		registerReceiver(receiver, filter);
	}
	
	private void unRegisterSmsMessageReceiver(){
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}

	public static void actionStart(Context context) {
		context.startActivity(new Intent(context, UsingWebViewActivity.class));
	}
	
}
