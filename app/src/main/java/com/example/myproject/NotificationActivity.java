package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    private int counter;

    public void showNotification()
    {
        //1. create notification chanel
        NotificationChannel notificationChannel= new NotificationChannel(
                "CHANEL_ONE",
                "Notification Chanel",
                NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription("just an example chanel");

        //2. using android system service
        NotificationManager manager = getSystemService(NotificationManager.class);

        //3. create the chanel
        manager.createNotificationChannel(notificationChannel);

        //create and Display notification
        Notification notification = new NotificationCompat.Builder(this, "CHANEL_ONE")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("title")
                .setContentText("text " + counter).build();
        manager.notify(counter, notification);
        counter++;
    }

}