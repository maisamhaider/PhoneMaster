package com.example.phonemaster.receivers;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

import com.example.phonemaster.activities.ChargingLockedScreenAct;


public class FastChargingChargerReceiver extends BroadcastReceiver {

    private String TAG="TAG";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.w(TAG, "onReceive: " );
//        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

         int batterySource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

        Intent i = new Intent(context, ChargingLockedScreenAct.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (batterySource == BatteryManager.BATTERY_PLUGGED_AC) {


                    context.startActivity(i);


            } else if (batterySource == BatteryManager.BATTERY_PLUGGED_USB) {

                    context.startActivity(i);

            } else if (batterySource == BatteryManager.BATTERY_PLUGGED_WIRELESS) {

                    context.startActivity(i);
                }
        }
}