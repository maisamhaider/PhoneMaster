package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

import java.net.URI;
import java.util.ArrayList;

public class OnImageVideoAct extends AppCompatActivity {

    VideoView videoPlayer_vv;
    ImageView myImage_iv1, backFromImageVideo_iv, saveImageVideo_tv, shareImageVideo_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_image_video);
        final Utils utils = new Utils(this);

        videoPlayer_vv = findViewById(R.id.videoPlayer_vv);
        myImage_iv1 = findViewById(R.id.myImage_iv1);
        backFromImageVideo_iv = findViewById(R.id.backFromImageVideo_iv);
        saveImageVideo_tv = findViewById(R.id.saveImageVideo_tv);
        shareImageVideo_iv = findViewById(R.id.shareImageVideo_iv);

        Intent intent = getIntent();
        final String data = intent.getStringExtra("imageOrVideoPath");
        boolean isSaved = intent.getBooleanExtra("isSaved",false);

        if (isSaved)
        {
            backFromImageVideo_iv.setVisibility(View.GONE);
            saveImageVideo_tv.setVisibility(View.GONE);
            shareImageVideo_iv.setVisibility(View.GONE);
        }
        else
        {

        }
        if (data.endsWith("mp4")) {

            videoPlayer_vv.setVideoPath(data);
            MediaController mc = new MediaController(this);
            mc.setAnchorView(videoPlayer_vv);
            mc.setMediaPlayer(videoPlayer_vv);
            videoPlayer_vv.setMediaController(mc);

            videoPlayer_vv.setMediaController(mc);
            videoPlayer_vv.start();

        } else {

            Uri image = Uri.parse(data);
            myImage_iv1.setImageURI(image);

        }

        backFromImageVideo_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        saveImageVideo_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.copyFileOrDirectory(data);
            }
        });
        shareImageVideo_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.endsWith("mp4")) {
                    Uri uriImage = Uri.parse(data);
                    ArrayList<Uri> oneImageArrayList = new ArrayList<>();
                    oneImageArrayList.add(uriImage);
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,oneImageArrayList );
                    sendIntent.setType("video/*");

                    startActivity(Intent.createChooser(sendIntent, "Share Image to"));
                    startActivity(sendIntent);

                } else {

                    Uri uriImage = Uri.parse(data);
                    ArrayList<Uri> oneImageArrayList = new ArrayList<>();
                    oneImageArrayList.add(uriImage);
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,oneImageArrayList );
                    sendIntent.setType("image/*");

                    startActivity(Intent.createChooser(sendIntent, "Share Image to"));
                    startActivity(sendIntent);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (videoPlayer_vv.isPlaying()) {
            videoPlayer_vv.pause();
        }
    }
}