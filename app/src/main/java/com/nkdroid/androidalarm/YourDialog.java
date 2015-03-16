package com.nkdroid.androidalarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class YourDialog extends Activity {

//	public static MediaPlayer player;
	public static void SoundPlayer(Context ctx,int raw_id){

	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);

		Context ctx = YourDialog.this;

		final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
		alert.setCancelable(false);
		alert.setTitle("Alert");
		alert.setMessage("Bingo....");

		alert.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton)
			{
//				MyBroadcastReceiver.mMediaPlayer.stop();
				dialog.dismiss();
				finish();
			}});
		alert.create();
		alert.show();


	}

}
