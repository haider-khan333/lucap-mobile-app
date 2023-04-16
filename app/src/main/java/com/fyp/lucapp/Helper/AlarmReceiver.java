package com.fyp.lucapp.Helper;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.fyp.lucapp.R;

public class AlarmReceiver extends BroadcastReceiver {

    public int NOTIFICATION_ID = 100;

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("CANCEL_ALARM".equals(intent.getAction())) {
            cancelAlarm(context);
        } else {
            createNotificationChannel(context);
            playAlarm(context);
            showNotificationWithCancelAction(context, intent);
        }

    }

    private void playAlarm(Context context) {
        Uri alarmURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmURI == null) {
            alarmURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmURI);
        ringtone.play();
    }

    private void showNotificationWithCancelAction(Context context, Intent receivedIntent) {
        Intent cancelIntent = new Intent(context, AlarmReceiver.class);
        cancelIntent.setAction("CANCEL_ALARM");
        int SHOW_NOTIFICATION_REQUEST_CODE = 1;
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(
                context, SHOW_NOTIFICATION_REQUEST_CODE,
                cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Create a cancel action for the notification
        NotificationCompat.Action cancelAction = new NotificationCompat.Action.Builder(
                R.drawable.cancel_alarm_foreground, "Cancel", cancelPendingIntent).build();

        String medicineName = receivedIntent.getStringExtra("medicineName");

        // Create a notification
        String channelId = "LucApp";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.app_logo_foreground) // Replace with your app's small icon
                .setContentTitle("Hello haider!")
                .setContentText("You have to take your medicine " + medicineName + " now!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setAutoCancel(true)
                .addAction(cancelAction);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // Show the notification
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }


    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "LucApp";
            String description = "LucApp";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("LucApp", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int CANCEL_ALARM_REQUEST_CODE = 0;
            pendingIntent = PendingIntent.getBroadcast(
                    context, CANCEL_ALARM_REQUEST_CODE,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT |
                            PendingIntent.FLAG_IMMUTABLE);
        } else {
            System.out.println("SDK < 23");
        }

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);

            if (pendingIntent != null) {
                pendingIntent.cancel();
            } else {
                System.out.println("Pending intent is null");
            }
        } else {
            System.out.println("Alarm manager is null");
        }

        cancelNotification(context);
    }

    private void cancelNotification(Context context) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(NOTIFICATION_ID);
    }


}

