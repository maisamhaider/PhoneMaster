package com.cleaner.booster.phone.repairer.app.models;

import android.graphics.drawable.Drawable;

public class DeepCleanImagesModel {
    String imagePath;
    String imageName;

    public DeepCleanImagesModel() {
    }

    public DeepCleanImagesModel(String imagePath) {
        this.imagePath = imagePath;
     }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
