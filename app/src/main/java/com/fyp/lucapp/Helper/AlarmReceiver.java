package com.fyp.lucapp.Helper;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fyp.lucapp.Main;
import com.fyp.lucapp.R;

public class AlarmReceiver extends android.content.BroadcastReceiver {
    public static int NOTIFICATION_ID = 1;


    @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
        Intent nextIntent = new Intent(context, Main.class);
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = android.app.PendingIntent.getActivity(context, 0,
                nextIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                "LucApp")
                .setSmallIcon(R.drawable.medication_notification)
                .setContentTitle("Medication Reminder")
                .setContentText("Please take your medicine")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}

