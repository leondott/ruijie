package com.ruijie.rush.util;

import android.app.Application;
import android.content.Context;

public class RushApplication extends Application {

	private static Context context = null;
	
	@Override
	public void onCreate() {
		context = getApplicationContext();
	}
	
	public static Context getContext() {
		return context;
	}
	
}
