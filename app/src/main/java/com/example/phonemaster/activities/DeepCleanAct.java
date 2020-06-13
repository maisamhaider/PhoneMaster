package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;

public class DeepCleanAct extends AppCompatActivity {

    private static final int PICK_IMAGES = 1;
    private Permissions permissions;

    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean);
        permissions = new Permissions(this);
        permissions.permission();
        utils = new Utils(this);
         ImageView deepCleanImage_iv1,deepCleanImage_iv2,deepCleanImage_iv3,deepCleanImage_iv4,deepCleanVideos_iv1,deepCleanVideos_iv2,deepCleanVideos_iv3,deepCleanVideos_iv4;
        deepCleanImage_iv1 = findViewById(R.id.deepCleanImage_iv1);
        deepCleanImage_iv2 = findViewById(R.id.deepCleanImage_iv2);
        deepCleanImage_iv3 = findViewById(R.id.deepCleanImage_iv3);
        deepCleanImage_iv4 = findViewById(R.id.deepCleanImage_iv4);

        deepCleanVideos_iv1 = findViewById(R.id.deepCleanVideos_iv1);
        deepCleanVideos_iv2 = findViewById(R.id.deepCleanVideos_iv2);
        deepCleanVideos_iv3 = findViewById(R.id.deepCleanVideos_iv3);
        deepCleanVideos_iv4 = findViewById(R.id.deepCleanVideos_iv4);

        TextView deepCleanWhatsAppDataSize_tv,deepCleanImagesSize_tv,deepCleanVideosSize_tv,deepCleanAudiosDataSize_tv,deepCleanLargeFileSize_tv,deepCleanInstallationPkgseSize_tv;
        deepCleanImagesSize_tv = findViewById(R.id.deepCleanImagesSize_tv);
        deepCleanVideosSize_tv = findViewById(R.id.deepCleanVideosSize_tv);
        deepCleanAudiosDataSize_tv = findViewById(R.id.deepCleanAudiosDataSize_tv);
        deepCleanLargeFileSize_tv = findViewById(R.id.deepCleanLargeFileSize_tv);
        deepCleanInstallationPkgseSize_tv = findViewById(R.id.deepCleanInstallationPkgseSize_tv);
        deepCleanWhatsAppDataSize_tv = findViewById(R.id.deepCleanWhatsAppDataSize_tv);


        float  bUDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Databases");
        float cHDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Backups");
        float whatsAppAudioSize =  utils.getAllSize(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Audio");
        float whatsAppVideoSize =  utils.getAllSize(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Video");
        float whatsAppDocumentsSize =  utils.getAllSize(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Documents");
        float whatsAppImagesSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Images");

        float whatsAppDataSize = bUDataSize+cHDataSize+whatsAppAudioSize+whatsAppVideoSize+whatsAppDocumentsSize+whatsAppImagesSize;
        float imagesSize = utils.getAllIAAsSize("images");
        float VideosSize = utils.getAllIAAsSize("videos");
        float audiosSize = utils.getAllIAAsSize("audios");
        float docSize = utils.getAllDocSize(String.valueOf(Environment.getExternalStorageDirectory()));
        float pkgSize = utils.getAllPkgsSize(String.valueOf(Environment.getExternalStorageDirectory()));

        deepCleanImagesSize_tv.setText(utils.getCalculatedDataSize(imagesSize));
        deepCleanVideosSize_tv.setText(utils.getCalculatedDataSize(VideosSize));
        deepCleanAudiosDataSize_tv.setText(utils.getCalculatedDataSize(audiosSize));
        deepCleanLargeFileSize_tv.setText(utils.getCalculatedDataSize(docSize));
        deepCleanInstallationPkgseSize_tv.setText(utils.getCalculatedDataSize(pkgSize));
        deepCleanWhatsAppDataSize_tv.setText(utils.getCalculatedDataSize(whatsAppDataSize));


        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size()-1).getImagePath())
                .into(deepCleanImage_iv1);
        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size()-2).getImagePath())
                .into(deepCleanImage_iv2);
        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size()-3).getImagePath())
                .into(deepCleanImage_iv3);
        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size()-4).getImagePath())
                .into(deepCleanImage_iv4);

        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size()-1).getVideoPath())
                .into(deepCleanVideos_iv1);
        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size()-2).getVideoPath())
                .into(deepCleanVideos_iv2);
        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size()-3).getVideoPath())
                .into(deepCleanVideos_iv3);
        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size()-4).getVideoPath())
                .into(deepCleanVideos_iv4);


        deepCleanWhatsAppDataSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this,CleanWhatsAppAct.class);
                    startActivity(intent);
                }
            }
        });
        deepCleanImagesSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this,DeepCleanAllImagesAct.class);
                    startActivity(intent);
                }
            }
        });
        deepCleanVideosSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this, DeepCleanAllVideosAct.class);
                    startActivity(intent);
                }
            }
        });
        deepCleanAudiosDataSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this, DeepCleanAllAudiosAct.class);
                    startActivity(intent);
                }
            }
        });
        deepCleanLargeFileSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this, DeepCleanAllDocsAct.class);
                    startActivity(intent);
                }
            }
        });
        deepCleanInstallationPkgseSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this, DeepCleanAllPackagesAct.class);
                    startActivity(intent);
                }
            }
        });
    }

}