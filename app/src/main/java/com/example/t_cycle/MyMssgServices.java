package com.example.t_cycle;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
        Log.d("remote_message",String.valueOf(remoteMessage.getNotification()));
     if (remoteMessage.getNotification()!=null){
        show_notification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        Log.d("Show notify",String.valueOf(remoteMessage.getNotification().getBody()));}
    }

    private NotificationManager mNotificationManager;

    public void show_notification(String Title, String Message) {

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotification")
//                .setContentTitle(Title)
//                .setContentText(Message)
//                .setSmallIcon(R.drawable.prize_re)
//                .setAutoCancel(true);
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//        managerCompat.notify(9999, builder.build());

        String [] MSG = Message.split(";");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(), "MyNotification");
       // Intent ii = new Intent(mContext.getApplicationContext(), RootActivity.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, ii, 0);




       // bigText.setBigContentTitle("Today's Bible Verse");
       // bigText.setSummaryText("Text in detail");

      //  mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.t_cycle_logo);
        mBuilder.setContentTitle("T_Cycle ♻");
       // mBuilder.setSubText(MSG[1]);

        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(new NotificationCompat.InboxStyle()
                .addLine(MSG[0])
                .addLine(MSG[1]));

        mNotificationManager =
                (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "0";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());

    }
}