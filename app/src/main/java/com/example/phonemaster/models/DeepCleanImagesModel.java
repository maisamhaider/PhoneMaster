package com.example.phonemaster.models;

import android.graphics.drawable.Drawable;

public class DeepCleanImagesModel {
    String imagePath;

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


}
