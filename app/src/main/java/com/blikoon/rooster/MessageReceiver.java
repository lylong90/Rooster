package com.blikoon.rooster;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.jivesoftware.smack.Chat;

/**
 * Created by lylong on 02/10/2016.
 */
public class MessageReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (ChatActivity.isActivityRunning) return; // if app is running, don't send notification

        String action = intent.getAction();
        switch (action)
        {
            case RoosterConnectionService.NEW_MESSAGE:
                String from = intent.getStringExtra(RoosterConnectionService.BUNDLE_FROM_JID);
                String body = intent.getStringExtra(RoosterConnectionService.BUNDLE_MESSAGE_BODY);


                Intent myIntent = new Intent(context, ChatActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtras(intent.getExtras());
                myIntent.putExtra("EXTRA_CONTACT_JID", from);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, myIntent, PendingIntent.FLAG_ONE_SHOT);

                Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                        .setContentTitle("New message from " + from)
                        .setSmallIcon(R.drawable.fab_bg_mini)   // if smallIcon not set, push notification won't appear
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

                return;
        }
    }
}
