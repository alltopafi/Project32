package com.example.jalltop.project3;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

/**
 * Created by jalltop on 4/8/2015.
 */
public class AlarmReciever extends BroadcastReceiver
{






    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO Auto-generated method stub


        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone

        System.out.println("Alarm worked");
        // Show the toast  like in above screen shot
        Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show();



        NotificationManager mNM;
        mNM = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        // Set the info for the views that show in the notification panel.

        PendingIntent dismissIntent = PendingIntent.getActivity(context,0,new Intent(context,DismissAlarm.class),0);

        int temp = CountDownActivity.alarmID;
        System.out.println(temp);


        Notification not2 = new Notification.Builder(context)
                .setFullScreenIntent(contentIntent, true)
                .setPriority(2)
                .setSmallIcon(R.drawable.ic_launcher)
                .addAction(R.drawable.ic_launcher,"Dismiss",dismissIntent)
                .addAction(R.drawable.ic_launcher,"Snooze",contentIntent)
                .setContentTitle("Alarm")
                .setContentText("test 1 " + temp)
                .setAutoCancel(true)
                .build();

        mNM.notify(CountDownActivity.alarmID, not2);

    }


}