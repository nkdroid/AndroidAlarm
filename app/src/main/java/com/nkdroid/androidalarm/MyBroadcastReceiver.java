package com.nkdroid.androidalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;


public class MyBroadcastReceiver extends BroadcastReceiver {
//	public static MediaPlayer mMediaPlayer;
	private Context context;
	@Override
	public void onReceive(Context context, final Intent intent) {
		this.context=context;

		// Vibrate the mobile phone
		Vibrator vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(2000);

		Intent trIntent = new Intent("android.intent.action.MAIN");

		trIntent.setClass(context, YourDialog.class);
		trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



		context.startActivity(trIntent);
//		mMediaPlayer = MediaPlayer.create(context, R.raw.ringtone);
//		mMediaPlayer.setLooping(true);
//		mMediaPlayer.start();
	}
}
