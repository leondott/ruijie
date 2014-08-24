package com.ruijie.rush.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ruijie.rush.R;
import com.ruijie.rush.base.BaseActivity;
import com.ruijie.rush.util.IRequestControl;
import com.ruijie.rush.util.impl.RequestControl;

public class UsingVolleyActivity extends BaseActivity implements OnClickListener {

	private IRequestControl request = null;
	private TextView textView = null;
	
	@Override
	protected void setContentView() {
		setContentView(R.layout.act_using_volley);
	}

	@Override
	protected void initView() {
		textView = (TextView) findViewById(R.id.tv_result);
	}

	@Override
	protected void initData() {
		request = new RequestControl(this);
	}

	@Override
	protected void setListener() {
		findViewById(R.id.btn_string_request).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_string_request:
			textView.setText(request.stringRequest("http://www.baidu.com"));
			break;
		case R.id.btn_json_request:
			textView.setText(request.jsonObjectRequest("http://m.weather.com.cn/data/101010100.html").toString());
			break;
		}
	}
	
	public static void actionStart(Context context){
		context.startActivity(new Intent(context, UsingVolleyActivity.class));
	}

}
