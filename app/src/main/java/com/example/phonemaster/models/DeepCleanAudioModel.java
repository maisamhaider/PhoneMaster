package com.example.phonemaster.models;

public class DeepCleanAudioModel {
    String audioPath;
    String audioName;

    public DeepCleanAudioModel() {
    }

    public DeepCleanAudioModel(String audioPath, String audioName) {
        this.audioPath = audioPath;
        this.audioName = audioName;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
}
