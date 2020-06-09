package com.example.t_cycle;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class MyMssgServices extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        shownotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        Log.d("Show notify",String.valueOf(remoteMessage.getNotification().getTitle()));
    }


    public void shownotification(String Title, String Message) {


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotification")
                .setContentTitle(Title)
                .setContentText(Message)
                .setSmallIcon(R.drawable.prize_re)
                .setSound(alarmSound)
                .setAutoCancel(true);
//builder.addAction(Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show());
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(99999, builder.build());
    }
}