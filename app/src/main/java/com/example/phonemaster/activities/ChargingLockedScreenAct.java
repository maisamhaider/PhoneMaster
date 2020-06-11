package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.phonemaster.R;

public class ChargingLockedScreenAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_locked_screen);
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//            this.setShowWhenLocked(true);
//        }

    }

    public class FastChargingChargerReceiver extends BroadcastReceiver {

        //TODO Execute the from service class..and service class is not created already.
        @Override
        public void onReceive(Context context, Intent intent) {
            KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

            int batterySource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

            if (batterySource == BatteryManager.BATTERY_PLUGGED_AC) {
                if( myKM.inKeyguardRestrictedInputMode()) {

                    finish();
                    Intent i = new Intent(context, ChargingLockedScreenAct.class);
                    context.startActivity(i);
                } else {
                    //it is not locked
                }

            } else if (batterySource == BatteryManager.BATTERY_PLUGGED_USB) {

                if( myKM.inKeyguardRestrictedInputMode()) {
                    finish();
                    Intent i = new Intent(context, ChargingLockedScreenAct.class);
                    context.startActivity(i);
                } else {
                    //it is not locked
                }

            } else if (batterySource == BatteryManager.BATTERY_PLUGGED_WIRELESS) {

                if( myKM.inKeyguardRestrictedInputMode()) {
                    finish();
                    Intent i = new Intent(context, ChargingLockedScreenAct.class);
                    context.startActivity(i);
                } else {
                    //it is not locked
                }
            }
        }


    }


}