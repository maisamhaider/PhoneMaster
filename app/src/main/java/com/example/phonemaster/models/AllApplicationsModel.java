package com.example.phonemaster.models;

import android.graphics.drawable.Drawable;

public class AllApplicationsModel {
     String packageName;
    String appName;
    Drawable image;

    public AllApplicationsModel(String pkgeName, String appName, Drawable image  ) {
        this.packageName = pkgeName;
        this.appName = appName;
        this.image = image;
     }

    public String getPackageName() {
        return packageName;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getImage() {
        return image;
    }

 }
