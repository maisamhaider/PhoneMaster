package com.example.phonemaster.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

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

        if (readStoragePer == PackageManager.PERMISSION_GRANTED
                && writeStoragePer == PackageManager.PERMISSION_GRANTED
                && phoneStatePer == PackageManager.PERMISSION_GRANTED
                && callPer == PackageManager.PERMISSION_GRANTED
                && readContactPer == PackageManager.PERMISSION_GRANTED
                && writeContactPer == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_PHONE_STATE
                    , Manifest.permission.CALL_PHONE
                    , Manifest.permission.READ_CONTACTS
                    , Manifest.permission.WRITE_CONTACTS}, REQ);

        return false;
    }
}
