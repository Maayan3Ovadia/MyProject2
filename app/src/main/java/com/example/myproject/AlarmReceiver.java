package com.example.myproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String CHANNEL_1_ID = "channel1";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"Alarm Receiver ya Habibti " ,Toast.LENGTH_SHORT).show();


        // set notification...
        NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,
                "channel 1",
                NotificationManager.IMPORTANCE_HIGH);
        // create the settings as default user can change
        // user can see in the settings
        channel1.setDescription(" Channel 1 user for...");

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel1);


        // define intent
        // wrap with pending intent


        //
        Notification notification = new NotificationCompat.Builder(context,CHANNEL_1_ID).
                // icon is the only mandatory field
                setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Message from Maayan")
                .setContentText("you have a driving lesson soon...")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //add the pending intent to notification builder
  //              .setContentIntent(notifyPendingIntent)
                .build();


        NotificationManagerCompat notificationManagerCompat =  NotificationManagerCompat.from(context);


        notificationManagerCompat.notify(1,notification);




    }




}



