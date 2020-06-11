package com.example.phonemaster.receivers;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.example.phonemaster.activities.ChargingLockedScreenAct;

public class FastChargingChargerReceiver extends BroadcastReceiver {

    //TODO Execute the from service class..and service class is not created already.
    @Override
    public void onReceive(Context context, Intent intent) {
        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

        int batterySource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

        if (batterySource == BatteryManager.BATTERY_PLUGGED_AC) {
            if( myKM.inKeyguardRestrictedInputMode()) {
                Intent i = new Intent(context, ChargingLockedScreenAct.class);
                context.startActivity(i);
            } else {
                //it is not locked
            }

        } else if (batterySource == BatteryManager.BATTERY_PLUGGED_USB) {

            if( myKM.inKeyguardRestrictedInputMode()) {
                 Intent i = new Intent(context, ChargingLockedScreenAct.class);
                context.startActivity(i);
            } else {
                //it is not locked
            }

        } else if (batterySource == BatteryManager.BATTERY_PLUGGED_WIRELESS) {

            if( myKM.inKeyguardRestrictedInputMode()) {
                 Intent i = new Intent(context, ChargingLockedScreenAct.class);
                context.startActivity(i);
            } else {
                //it is not locked
            }
        }
    }


}