package com.example.phonemaster.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.phonemaster.R;
import com.example.phonemaster.activities.ChargingLockedScreenAct;
import com.example.phonemaster.activities.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyService  {
    Context context;
    public MyService(Context context) {
        this.context = context;
    }

}
