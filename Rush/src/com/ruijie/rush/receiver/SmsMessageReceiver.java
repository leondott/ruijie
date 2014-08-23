package com.ruijie.rush.receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public abstract class SmsMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Object[] pdus = (Object[]) bundle.get("pdus");
		SmsMessage[] messages = new SmsMessage[pdus.length];
		for (int i = 0; i < messages.length; i++) {
			messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
		}
		String address = messages[0].getOriginatingAddress();
		String fullMessage = "";
		for (SmsMessage message : messages) {
			fullMessage += message.getMessageBody();
		}
		Toast.makeText(context, "发信人：" + address + "\n内容：" + fullMessage,
				Toast.LENGTH_SHORT).show();
		updateUI(getAuthenCodes(fullMessage, 6));
	}

	private static String getAuthenCodes(String body, int length) {
		Pattern p = Pattern.compile("(?<![a-zA-Z0-9])([a-zA-Z0-9]{" + length
				+ "})(?![a-zA-Z0-9])");
		Matcher m = p.matcher(body);
		if (m.find()) {
			System.out.println(m.group());
			return m.group(0);
		}
		return null;
	}

	protected abstract void updateUI(String content);

}
