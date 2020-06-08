package com.example.phonemaster.models;

public class DeepCleanVideosModel {
    String videoPath;

    public DeepCleanVideosModel() {
    }

    public DeepCleanVideosModel(String imagePath) {
        this.videoPath = imagePath;
     }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String imagePath) {
        this.videoPath = imagePath;
    }


}
