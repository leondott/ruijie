package com.ruijie.rush.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class ActivityInfoParser {

	public static List<ActivityInfo> getActivityInfos(Context context) {
		Intent mainIntent = new Intent("com.ruijie.rush.intent.action.MAIN");
		mainIntent.addCategory("android.intent.category.DEFAULT");
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);
		List<ActivityInfo> activityInfos = new ArrayList<ActivityInfo>();
		if (resolveInfos == null) {
			resolveInfos = new ArrayList<ResolveInfo>();
		}
		for (ResolveInfo resolveInfo : resolveInfos) {
			activityInfos.add(resolveInfo.activityInfo);
		}
		return activityInfos;
	}

}
