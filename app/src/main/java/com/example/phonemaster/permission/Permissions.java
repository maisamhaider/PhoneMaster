package com.example.phonemaster.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {
    private static final int REQ = 1111;
    private Context context;

    public Permissions(Context context) {
        this.context = context;
    }

    public boolean permission() {

        int readStoragePer = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStoragePer = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int phoneStatePer = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        int callPer = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        int readContactPer = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);
        int writeContactPer = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS);
        int accessNetwork = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE);
        int accessWifi = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE);

        if (readStoragePer == PackageManager.PERMISSION_GRANTED
                && writeStoragePer == PackageManager.PERMISSION_GRANTED
                && phoneStatePer == PackageManager.PERMISSION_GRANTED
                && callPer == PackageManager.PERMISSION_GRANTED
                && readContactPer == PackageManager.PERMISSION_GRANTED
                && writeContactPer == PackageManager.PERMISSION_GRANTED
                && accessNetwork == PackageManager.PERMISSION_GRANTED
                && accessWifi == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_PHONE_STATE
                    , Manifest.permission.CALL_PHONE
                    , Manifest.permission.READ_CONTACTS
                    , Manifest.permission.WRITE_CONTACTS
                    , Manifest.permission.ACCESS_NETWORK_STATE
                    , Manifest.permission.ACCESS_WIFI_STATE}, REQ);

        return false;
    }

//    public void overlayAppPermission() {
//        //This permission is by default available for API<23. But for API > 23
//        //you have to ask for the permission in runtime.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
//            //If the draw over permission is not available open the settings screen
//            //to grant the permission.
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + context.getPackageName()));
//            context.startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION);
//        }
//    }
}
