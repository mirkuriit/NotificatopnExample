package com.example.notificatopnexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class TimeNotificationService extends Service {
    public TimeNotificationService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri notificationSound;
        notificationSound = Uri.parse("android.resource://your.app.package/" + R.raw.badumtss);

        //Создание PendingIntent
        Intent intent1 = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        //Создание менеджера
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Создание канала для уведмолений
        NotificationChannel channel = new NotificationChannel("Music channel",
                "Music", NotificationManager.IMPORTANCE_HIGH);

        //Активация канала
        manager.createNotificationChannel(channel);
        //Создание настроек уведмоление
        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(this, "Music channel")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Возможно вам стоит")
                        .setContentText("заняться полезными делами")
                        .setColorized(true)
                        .setChannelId("Music channel")
                        .setSound(notificationSound)
                        .setContentIntent(pendingIntent);

        //Создание уведомления
        Notification notification = nBuilder.build();
        //Запуск уведомления
        manager.notify(5345, notification);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}