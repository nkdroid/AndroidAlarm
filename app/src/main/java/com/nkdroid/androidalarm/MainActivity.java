package com.nkdroid.androidalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    public static AlarmManager alarmMgr;
    public static PendingIntent alarmIntent;
    private String day,month,year,hour,minute;

    Date date;
    Date time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setAlarm();
    }

    private void setAlarm() {
        try {
            String stringDate="16/03/2015";
            String stringTime="8:00 PM";

            // complete date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(stringDate);
            System.out.println("date:"+date);

            // only day
            SimpleDateFormat ddFormatter = new SimpleDateFormat("dd");
            day=ddFormatter.format(date);


            // only month
            SimpleDateFormat mmFormatter = new SimpleDateFormat("MM");
            month=mmFormatter.format(date);


            // only year
            SimpleDateFormat yyyyFormatter = new SimpleDateFormat("yyyy");
            year=yyyyFormatter.format(date);


            // complete time
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
            time = parseFormat.parse(stringTime);

            // only hour
            SimpleDateFormat hhFormater = new SimpleDateFormat("HH");
            hour=hhFormater.format(time);


            //only minutes
            SimpleDateFormat mmFormater = new SimpleDateFormat("mm");
            minute=mmFormater.format(time);



            // String output
            System.out.println("day:"+day);
            System.out.println("month:"+month);
            System.out.println("year:"+year);
            System.out.println("hour:"+hour);
            System.out.println("minute:"+minute);


            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
            calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
            calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
            calendar.set(Calendar.SECOND, 0);

            // Calendar output
            Log.e("YEAR", calendar.get(Calendar.YEAR) + "");
            Log.e("MONTH",calendar.get(Calendar.MONTH)+"");
            Log.e("DAY_OF_MONTH",calendar.get(Calendar.DAY_OF_MONTH)+"");
            Log.e("HOUR_OF_DAY",calendar.get(Calendar.HOUR_OF_DAY)+"");
            Log.e("MINUTE",calendar.get(Calendar.MINUTE)+"");
            Log.e("SECOND",calendar.get(Calendar.SECOND)+"");

            alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
            intent.putExtra("title", "Suhoor Time");
            intent.putExtra("time",hour+":"+minute);
            intent.putExtra("data","");

            alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 20000000 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmMgr.cancel(alarmIntent);

            // Current Date Logic in dd/MM/yyyy
            Date currentDate=new Date();
            SimpleDateFormat currentFormatter= new SimpleDateFormat("dd/MM/yyyy");
            String currentString=currentFormatter.format(currentDate);
            currentDate=currentFormatter.parse(currentString);


            // Current Time Logic in hh:mm a
            Date currentTime=new Date();
            SimpleDateFormat currentTimeFormatter= new SimpleDateFormat("hh:mm a");
            String currentTimeString=currentTimeFormatter.format(currentTime);
            currentTime=currentTimeFormatter.parse(currentTimeString);

            if(currentDate.after(date)){
                Log.e("Date:","Sorry Date was gone");
            } else {
                if(currentDate.equals(date)){
                    if(currentTime.after(time)){
                        Log.e("Time:","Sorry Time was gone");
                    } else {
                        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                alarmIntent);
                        Log.e("set:","alarm set");
                    }
                }else {
                    alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            alarmIntent);
                    Log.e("set:","alarm set");
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("HOME");
            setSupportActionBar(toolbar);
        }
    }
}
