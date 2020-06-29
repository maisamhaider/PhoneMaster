package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;

public class DeepCleanAct extends AppCompatActivity {

    private Permissions permissions;
    private ConstraintLayout deepCleanWhatsApp_cl, deeCleanImages_cl, deeCleanVideos_cl, deeCleanAudios_cl, deeCleanAppData_cl, deepCleanLargeFiles_cl, deeCleanInstallationPkg_cl;
    private ImageView deepCleanWhatsAppNext_iv, deepCleanImagesNext_iv, deepCleanVideosNext_iv, deepCleanAudiosNext_iv,deepCleanLargeFilesNext_iv, deepCleanAppDataNext_iv, deepCleanInstallationPkgsNext_iv;

    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        permissions = new Permissions(this);
        permissions.permission();
        utils = new Utils(this);

        deepCleanWhatsApp_cl = findViewById(R.id.deepCleanWhatsApp_cl);
        deeCleanImages_cl = findViewById(R.id.deeCleanImages_cl);
        deeCleanVideos_cl = findViewById(R.id.deeCleanVideos_cl);
        deeCleanAudios_cl = findViewById(R.id.deeCleanAudios_cl);
        deepCleanLargeFiles_cl = findViewById(R.id.deeCleanLargeFiles_cl);
        deeCleanAppData_cl = findViewById(R.id.deeCleanAppData_cl);
        deeCleanInstallationPkg_cl = findViewById(R.id.deeCleanInstallationPkg_cl);

        deepCleanWhatsAppNext_iv = findViewById(R.id.deepCleanWhatsAppNext_iv);
        deepCleanImagesNext_iv = findViewById(R.id.deepCleanImagesNext_iv);
        deepCleanVideosNext_iv = findViewById(R.id.deepCleanVideosNext_iv);
        deepCleanAudiosNext_iv = findViewById(R.id.deepCleanAudiosNext_iv);
        deepCleanAppDataNext_iv = findViewById(R.id.deeCleanAppDataNext_iv);
        deepCleanLargeFilesNext_iv = findViewById(R.id.deepCleanLargeFilesNext_iv);
        deepCleanInstallationPkgsNext_iv = findViewById(R.id.deepCleanInstallationPkgsNext_iv);


        ImageView deepCleanImage_iv1, deepCleanImage_iv2, deepCleanImage_iv3, deepCleanVideos_iv1, deepCleanVideos_iv2, deepCleanVideos_iv3,deepCleanMainBack_iv;
        deepCleanImage_iv1 = findViewById(R.id.deepCleanImage_iv1);
        deepCleanImage_iv2 = findViewById(R.id.deepCleanImage_iv2);
        deepCleanImage_iv3 = findViewById(R.id.deepCleanImage_iv3);

        deepCleanVideos_iv1 = findViewById(R.id.deepCleanVideos_iv1);
        deepCleanVideos_iv2 = findViewById(R.id.deepCleanVideos_iv2);
        deepCleanVideos_iv3 = findViewById(R.id.deepCleanVideos_iv3);

        deepCleanMainBack_iv = findViewById(R.id.deepCleanMainBack_iv);

        TextView deepCleanWhatsAppDataSize_tv, deepCleanImagesSize_tv, deepCleanVideosSize_tv,
                deepCleanAudiosDataSize_tv,deepCleanAppDataSize_tv, deepCleanLargeFileSize_tv, deepCleanInstallationPkgseSize_tv,
                deepCleanProgress_tv,deepCleanDetail_tv,deepCleanMainTotalDataSize_tv,deepCleanDataPrefix_tv;

        deepCleanMainTotalDataSize_tv = findViewById(R.id.deepCleanMainTotalDataSize_tv);
        deepCleanDataPrefix_tv = findViewById(R.id.deepCleanDataPrefix_tv);
        deepCleanImagesSize_tv = findViewById(R.id.deepCleanImagesSize_tv);
        deepCleanVideosSize_tv = findViewById(R.id.deepCleanVideosSize_tv);
        deepCleanAudiosDataSize_tv = findViewById(R.id.deepCleanAudiosDataSize_tv);
        deepCleanLargeFileSize_tv = findViewById(R.id.deepCleanLargeFileSize_tv);
        deepCleanInstallationPkgseSize_tv = findViewById(R.id.deepCleanInstallationPkgseSize_tv);
        deepCleanWhatsAppDataSize_tv = findViewById(R.id.deepCleanWhatsAppDataSize_tv);
        deepCleanProgress_tv = findViewById(R.id.deepCleanProgress_tv);
        deepCleanDetail_tv = findViewById(R.id.deepCleanDetail_tv);
        deepCleanAppDataSize_tv = findViewById(R.id.deepCleanAppDataSize_tv);

        ProgressBar deepClean_pb;
        deepClean_pb= findViewById(R.id.deepClean_pb);


        float bUDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Databases");
        float cHDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Backups");
        float whatsAppAudioSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Audio");
        float whatsAppVideoSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Video");
        float whatsAppDocumentsSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Documents");
        float whatsAppImagesSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Images");

        float facebookSavedImagesSize = utils.getAllSize(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Facebook");
        float messengerSavedImagesSize = utils.getAllSize(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Messenger");
        float messengerSavedVideosSize = utils.getAllSize(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Movies/Messenger");
        float instagramSavedImagesSize = utils.getAllSize(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Instagram");
        float instagramSavedVideosSize = utils.getAllSize(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Movies/Instagram");

        float whatsAppDataSize = bUDataSize + cHDataSize + whatsAppAudioSize + whatsAppVideoSize + whatsAppDocumentsSize + whatsAppImagesSize;
        float imagesSize = utils.getAllIAAsSize("images");
        float VideosSize = utils.getAllIAAsSize("videos");
        float audiosSize = utils.getAllIAAsSize("audios");
        float docSize = utils.getAllDocSize(String.valueOf( Environment.getExternalStorageDirectory()));
        float pkgSize = utils.getAllPkgsSize(String.valueOf(Environment.getExternalStorageDirectory()));

        float totalSize = whatsAppDataSize+imagesSize+VideosSize+audiosSize+docSize+pkgSize;
        int size = utils.getCalculatedDataSize(totalSize).length();
        String dataPrefix;
        if (utils.getCalculatedDataSize(totalSize).contains("Bytes"))
         {
            dataPrefix = utils.getCalculatedDataSize(totalSize).substring(size-5,size);
        }
        else {
            dataPrefix = utils.getCalculatedDataSize(totalSize).substring(size-2,size);

        }

        deepCleanDataPrefix_tv.setText(dataPrefix);
        deepCleanImagesSize_tv.setText(utils.getCalculatedDataSize(imagesSize));
        deepCleanVideosSize_tv.setText(utils.getCalculatedDataSize(VideosSize));
        deepCleanAudiosDataSize_tv.setText(utils.getCalculatedDataSize(audiosSize));
        deepCleanLargeFileSize_tv.setText(utils.getCalculatedDataSize(docSize));
        deepCleanInstallationPkgseSize_tv.setText(utils.getCalculatedDataSize(pkgSize));
        deepCleanWhatsAppDataSize_tv.setText(utils.getCalculatedDataSize(whatsAppDataSize));
        deepCleanAppDataSize_tv.setText(utils.getCalculatedDataSize(facebookSavedImagesSize+messengerSavedImagesSize+messengerSavedVideosSize+instagramSavedImagesSize+instagramSavedVideosSize));

        deepCleanWhatsApp_cl.setVisibility(View.GONE);
        deeCleanImages_cl.setVisibility(View.GONE);
        deeCleanVideos_cl.setVisibility(View.GONE);
        deeCleanAudios_cl.setVisibility(View.GONE);
        deeCleanAppData_cl.setVisibility(View.GONE);
        deeCleanInstallationPkg_cl.setVisibility(View.GONE);

        deepCleanMainBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deepClean_pb.setMax(100);
        ValueAnimator animator = ValueAnimator.ofInt(0, (int) utils.getCalculatedDataSizeFloat(totalSize));
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(0);
        animator.setDuration(3_000);
        animator.addUpdateListener(valueAnimator -> {
            int value = (int) valueAnimator.getAnimatedValue();
            deepCleanMainTotalDataSize_tv.setText(String.valueOf(value));
            if (value == (int) utils.getCalculatedDataSizeFloat(totalSize))
            {
                deepCleanMainTotalDataSize_tv.setText( String.format("%.1f",utils.getCalculatedDataSizeFloat(totalSize)));
            }
        });
        animator.start();


        deepClean_pb.setMax(100);
        ValueAnimator animatorText = ValueAnimator.ofInt(0, 100);
        animatorText.setInterpolator(new LinearInterpolator());
        animatorText.setStartDelay(0);
        animatorText.setDuration(3_000);

        animatorText.addUpdateListener(valueAnimator -> {
            int value = (int) valueAnimator.getAnimatedValue();
            if (value==20)
            {
                deepCleanWhatsApp_cl.setVisibility(View.VISIBLE);
            }
            if (value==40)
            {
                deeCleanImages_cl.setVisibility(View.VISIBLE);
            }
            if (value==50)
            {
                deeCleanVideos_cl.setVisibility(View.VISIBLE);
            }
            if (value==60)
            {
                deeCleanAudios_cl.setVisibility(View.VISIBLE);
            }
            if (value==70)
            {
                deepCleanLargeFiles_cl.setVisibility(View.VISIBLE);
            }
            if (value==80)
            {
                deeCleanAppData_cl.setVisibility(View.VISIBLE);
            }
            if (value==100)
            {
                deeCleanInstallationPkg_cl.setVisibility(View.VISIBLE);
                deepCleanDetail_tv.setText("Scan is completed");
            }
            deepClean_pb.setProgress(value);
            deepCleanProgress_tv.setText("SCANNING"+"("+utils.getPercentage((float)100,(float)value)+"%)");
        });
        animatorText.start();



        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size() - 1).getImagePath())
                .into(deepCleanImage_iv1);
        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size() - 2).getImagePath())
                .into(deepCleanImage_iv2);
        Glide.with(this)
                .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size() - 3).getImagePath())
                .into(deepCleanImage_iv3);




        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size() - 1).getVideoPath())
                .into(deepCleanVideos_iv1);
        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size() - 2).getVideoPath())
                .into(deepCleanVideos_iv2);
        Glide.with(this)
                .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size() - 3).getVideoPath())
                .into(deepCleanVideos_iv3);

        initIntents();


    }

    private void intentFun(Class<?> cls, boolean isSend) {
        Intent intent = new Intent(DeepCleanAct.this, cls);
        intent.putExtra("isSend",isSend);
        startActivity(intent);

    }
    private void initIntents()
    {
        // Constraints
        deepCleanWhatsApp_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(CleanWhatsAppAct.class,false);
            }
        });
        deeCleanImages_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllImagesAct.class,false);
            }
        });
        deeCleanVideos_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllVideosAct.class,false);
            }
        });
        deeCleanAudios_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllAudiosAct.class,false);
            }
        });
        deeCleanAppData_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAppDataAct.class,false);
            }
        });

        deepCleanLargeFiles_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllDocsAct.class,false);
            }
        });
        deeCleanInstallationPkg_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllPackagesAct.class,false);
            }
        });

        //deepClean Next imageViews

        deepCleanWhatsAppNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(CleanWhatsAppAct.class,false);
            }
        });
        deepCleanImagesNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllImagesAct.class,false);
            }
        });
        deepCleanVideosNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllVideosAct.class,false);
            }
        });
        deepCleanAudiosNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllAudiosAct.class,false);
            }
        });
        deepCleanAppDataNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAppDataAct.class,false);
            }
        });
        deepCleanLargeFilesNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllDocsAct.class,false);
            }
        });
        deepCleanInstallationPkgsNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllPackagesAct.class,false);
            }
        });
    }
}