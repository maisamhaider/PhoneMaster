package com.example.phonemaster.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.phonemaster.R;
import com.example.phonemaster.receivers.FastChargingChargerReceiver;

public class MyService extends Service {
    public static final String TAG = "CustomService";
    private Context context;


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager) {
        String channelId = "my_service_channelid";
        String channelName = "My Foreground Service";
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_MIN);
        // omitted the LED color
        channel.setImportance(NotificationManager.IMPORTANCE_NONE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? createNotificationChannel(notificationManager) : "Apps Protected";
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Apps Protected").setContentText("Apps Protection is on. If you want to turn off protection go to app and remove it.").setStyle(new NotificationCompat.BigTextStyle().bigText("Apps Protection is on. If you want to turn off protection go to app and remove it.")).setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Notification notification = notificationBuilder.setOngoing(true).setSmallIcon(R.drawable.ic_launcher_foreground).setContentInfo("Apps Protected. Apps Protection is on. If you want to turn off protection go to app and remove it.").setCategory(NotificationCompat.CATEGORY_SERVICE).build();
            startForeground(100, notification);
        }


            FastChargingChargerReceiver fastChargingChargerReceiver = new FastChargingChargerReceiver();
            IntentFilter intentFilter = new IntentFilter( Intent.ACTION_BATTERY_CHANGED );
            getApplicationContext().registerReceiver( fastChargingChargerReceiver, intentFilter );

        Log.d("Custom","service");
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("CustomService", "onTaskRemoved");
        Intent restartService = new Intent(getApplicationContext(),
                this.getClass());
        restartService.setPackage(getPackageName());
        PendingIntent restartServicePI = PendingIntent.getService(
                getApplicationContext(), 1, restartService,
                PendingIntent.FLAG_ONE_SHOT);

        //Restart the service once it has been killed android
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 100, restartServicePI);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
