package com.ruijie.rush.ui;

import android.content.Context;
import android.content.Intent;

public class UsingNiftyDialogEffectsActivity extends com.gitonway.lee.niftymodal.MainActivity {
	
	public static void actionStart(Context context){
		context.startActivity(new Intent(context, UsingNiftyDialogEffectsActivity.class));
	}

}
