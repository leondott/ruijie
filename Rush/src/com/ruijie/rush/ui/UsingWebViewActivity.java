package com.ruijie.rush.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ruijie.rush.R;
import com.ruijie.rush.base.BaseActivity;
import com.ruijie.rush.receiver.SmsMessageReceiver;
import com.ruijie.rush.util.Constants;

public class UsingWebViewActivity extends BaseActivity implements OnClickListener {

	public static final String URL_LOCAL_HTML = "file:///android_asset/html/index.html";
	
	private WebView webView = null;
	private SmsMessageReceiver receiver = null;
	
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
		findViewById(R.id.btn_reg).setOnClickListener(this);
		findViewById(R.id.btn_unreg).setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg:
			registerSmsMessageReceiver();
			Toast.makeText(this, "已开启短信监听", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_unreg:
			unRegisterSmsMessageReceiver();
			Toast.makeText(this, "已关闭短信监听", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	public static void actionStart(Context context) {
		context.startActivity(new Intent(context, UsingWebViewActivity.class));
	}
	
}
