package com.cleaner.booster.phone.repairer.app.models;

public class DeepCleanVideosModel {
    String videoPath;
    String videoName;

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

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
