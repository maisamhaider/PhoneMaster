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
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.StorageUtils;
import com.example.phonemaster.utils.Utils;

public class FilesMoverAct extends AppCompatActivity {
    private Permissions permissions;
    private ConstraintLayout fileMoverImages_cl, fileMoverVideos_cl, fileMoverAudios_cl, fileMoverLargeFiles_cl, fileMoverInstallationPkg_cl;
    private ImageView fileMoverImagesNext_iv, fileMoverVideosNext_iv, fileMoverAudiosNext_iv, fileMoverLargeFilesNext_iv, fileMoverInstallationPkgsNext_iv, isSdCard_iv;
    private ScrollView scrollViewFileMover;
    Utils utils;
    StorageUtils storageUtils;
    TextView isSdCard_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_mover);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        storageUtils = new StorageUtils();
        scrollViewFileMover = findViewById(R.id.scrollViewFileMover);
        isSdCard_tv = findViewById(R.id.isSdCard_tv);
        isSdCard_iv = findViewById(R.id.isSdCard_iv);

        if (!utils.externalMemoryAvailable(this)) {
            scrollViewFileMover.setVisibility(View.GONE);
            isSdCard_tv.setVisibility(View.VISIBLE);
            isSdCard_iv.setVisibility(View.VISIBLE);

        } else {
            isSdCard_tv.setVisibility(View.GONE);
            isSdCard_iv.setVisibility(View.GONE);
            scrollViewFileMover.setVisibility(View.VISIBLE);
            permissions = new Permissions(this);
            permissions.permission();
            utils = new Utils(this);

            fileMoverImages_cl = findViewById(R.id.fileMoverImages_cl);
            fileMoverVideos_cl = findViewById(R.id.fileMoverVideos_cl);
            fileMoverAudios_cl = findViewById(R.id.fileMoverAudios_cl);
            fileMoverLargeFiles_cl = findViewById(R.id.fileMoverLargeFiles_cl);
            fileMoverInstallationPkg_cl = findViewById(R.id.fileMoverInstallationPkg_cl);

            fileMoverImagesNext_iv = findViewById(R.id.fileMoverImagesNext_iv);
            fileMoverVideosNext_iv = findViewById(R.id.fileMoverVideosNext_iv);
            fileMoverAudiosNext_iv = findViewById(R.id.fileMoverAudiosNext_iv);
            fileMoverLargeFilesNext_iv = findViewById(R.id.fileMoverLargeFilesNext_iv);
            fileMoverInstallationPkgsNext_iv = findViewById(R.id.fileMoverInstallationPkgsNext_iv);


            ImageView fileMoverImage_iv1, fileMoverImage_iv2, fileMoverImage_iv3, fileMoverVideos_iv1, fileMoverVideos_iv2, fileMoverVideos_iv3, fileMoverMainBack_iv;
            fileMoverImage_iv1 = findViewById(R.id.fileMoverImage_iv1);
            fileMoverImage_iv2 = findViewById(R.id.fileMoverImage_iv2);
            fileMoverImage_iv3 = findViewById(R.id.fileMoverImage_iv3);

            fileMoverVideos_iv1 = findViewById(R.id.fileMoverVideos_iv1);
            fileMoverVideos_iv2 = findViewById(R.id.fileMoverVideos_iv2);
            fileMoverVideos_iv3 = findViewById(R.id.fileMoverVideos_iv3);

            fileMoverMainBack_iv = findViewById(R.id.fileMoverMainBack_iv);

            TextView fileMoverImagesSize_tv, fileMoverVideosSize_tv, fileMoverAudiosDataSize_tv,
                    fileMoverLargeFileSize_tv, fileMoverInstallationPkgseSize_tv,
                    fileMoverProgress_tv, fileMoverDetail_tv, fileMoverMainTotalDataSize_tv, fileMoverDataPrefix_tv;

            fileMoverMainTotalDataSize_tv = findViewById(R.id.fileMoverMainTotalDataSize_tv);
            fileMoverDataPrefix_tv = findViewById(R.id.fileMoverDataPrefix_tv);
            fileMoverImagesSize_tv = findViewById(R.id.fileMoverImagesSize_tv);
            fileMoverVideosSize_tv = findViewById(R.id.fileMoverVideosSize_tv);
            fileMoverAudiosDataSize_tv = findViewById(R.id.fileMoverAudiosDataSize_tv);
            fileMoverLargeFileSize_tv = findViewById(R.id.fileMoverLargeFileSize_tv);
            fileMoverInstallationPkgseSize_tv = findViewById(R.id.fileMoverInstallationPkgseSize_tv);
            fileMoverProgress_tv = findViewById(R.id.fileMoverProgress_tv);
            fileMoverDetail_tv = findViewById(R.id.fileMoverDetail_tv);

            ProgressBar fileMover_pb;
            fileMover_pb = findViewById(R.id.fileMover_pb);


            float bUDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Databases");
            float cHDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Backups");
            float whatsAppAudioSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Audio");
            float whatsAppVideoSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Video");
            float whatsAppDocumentsSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Documents");
            float whatsAppImagesSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Images");


            float whatsAppDataSize = bUDataSize + cHDataSize + whatsAppAudioSize + whatsAppVideoSize + whatsAppDocumentsSize + whatsAppImagesSize;
            float imagesSize = utils.getAllIAAsSize("images");
            float VideosSize = utils.getAllIAAsSize("videos");
            float audiosSize = utils.getAllIAAsSize("audios");
            float docSize = utils.getAllDocSize(String.valueOf(Environment.getExternalStorageDirectory()));
            float pkgSize = utils.getAllPkgsSize(String.valueOf(Environment.getExternalStorageDirectory()));

            float totalSize = whatsAppDataSize + imagesSize + VideosSize + audiosSize + docSize + pkgSize;
            int size = utils.getCalculatedDataSize(totalSize).length();
            String dataPrefix = utils.getCalculatedDataSize(totalSize).substring(size - 2, size);

            fileMoverDataPrefix_tv.setText(dataPrefix);
            fileMoverImagesSize_tv.setText(utils.getCalculatedDataSize(imagesSize));
            fileMoverVideosSize_tv.setText(utils.getCalculatedDataSize(VideosSize));
            fileMoverAudiosDataSize_tv.setText(utils.getCalculatedDataSize(audiosSize));
            fileMoverLargeFileSize_tv.setText(utils.getCalculatedDataSize(docSize));
            fileMoverInstallationPkgseSize_tv.setText(utils.getCalculatedDataSize(pkgSize));

            fileMoverImages_cl.setVisibility(View.GONE);
            fileMoverVideos_cl.setVisibility(View.GONE);
            fileMoverAudios_cl.setVisibility(View.GONE);
            fileMoverInstallationPkg_cl.setVisibility(View.GONE);

            fileMoverMainBack_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            fileMover_pb.setMax(100);
            ValueAnimator animator = ValueAnimator.ofInt(0, (int) utils.getCalculatedDataSizeFloat(totalSize));
            animator.setInterpolator(new LinearInterpolator());
            animator.setStartDelay(0);
            animator.setDuration(3_000);
            animator.addUpdateListener(valueAnimator -> {
                int value = (int) valueAnimator.getAnimatedValue();
                fileMoverMainTotalDataSize_tv.setText(String.valueOf(value));
                if (value == (int) utils.getCalculatedDataSizeFloat(totalSize)) {
                    fileMoverMainTotalDataSize_tv.setText(String.format("%.1f", utils.getCalculatedDataSizeFloat(totalSize)));
                }
            });
            animator.start();


            fileMover_pb.setMax(100);
            ValueAnimator animatorText = ValueAnimator.ofInt(0, 100);
            animatorText.setInterpolator(new LinearInterpolator());
            animatorText.setStartDelay(0);
            animatorText.setDuration(3_000);

            animatorText.addUpdateListener(valueAnimator -> {
                int value = (int) valueAnimator.getAnimatedValue();

                if (value == 40) {
                    fileMoverImages_cl.setVisibility(View.VISIBLE);
                }
                if (value == 50) {
                    fileMoverVideos_cl.setVisibility(View.VISIBLE);
                }
                if (value == 60) {
                    fileMoverAudios_cl.setVisibility(View.VISIBLE);
                }
                if (value == 70) {
                    fileMoverLargeFiles_cl.setVisibility(View.VISIBLE);
                }
                if (value == 90) {
                    fileMoverInstallationPkg_cl.setVisibility(View.VISIBLE);
                    fileMoverDetail_tv.setText("Scan is completed");
                }
                fileMover_pb.setProgress(value);
                fileMoverProgress_tv.setText("SCANNING" + "(" + utils.getPercentage((float) 100, (float) value) + "%)");
            });
            animatorText.start();

            if (utils.getAllImagePaths().size() != 0) {
                Glide.with(this)
                        .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size() - 1).getImagePath())
                        .into(fileMoverImage_iv1);
                Glide.with(this)
                        .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size() - 2).getImagePath())
                        .into(fileMoverImage_iv2);
                Glide.with(this)
                        .load(utils.getAllImagePaths().get(utils.getAllImagePaths().size() - 3).getImagePath())
                        .into(fileMoverImage_iv3);
            }

            if (utils.getAllVideosPaths().size() != 0) {

                Glide.with(this)
                        .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size() - 1).getVideoPath())
                        .into(fileMoverVideos_iv1);
                Glide.with(this)
                        .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size() - 2).getVideoPath())
                        .into(fileMoverVideos_iv2);
                Glide.with(this)
                        .load(utils.getAllVideosPaths().get(utils.getAllVideosPaths().size() - 3).getVideoPath())
                        .into(fileMoverVideos_iv3);
            }
            initIntents();


        }
    }


    private void intentFun(Class<?> cls, boolean isSend) {
        Intent intent = new Intent(FilesMoverAct.this, cls);
        intent.putExtra("isSend", isSend);
        startActivity(intent);

    }

    private void initIntents() {
        // Constraints

        fileMoverImages_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllImagesAct.class, true);
            }
        });
        fileMoverVideos_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllVideosAct.class, true);
            }
        });
        fileMoverAudios_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllAudiosAct.class, true);
            }
        });

        fileMoverLargeFiles_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllDocsAct.class, true);
            }
        });
        fileMoverInstallationPkg_cl.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllPackagesAct.class, true);
            }
        });

        //deepClean Next imageViews

        fileMoverImagesNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllImagesAct.class, true);
            }
        });
        fileMoverVideosNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllVideosAct.class, true);
            }
        });
        fileMoverAudiosNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllAudiosAct.class, true);
            }
        });

        fileMoverLargeFilesNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllDocsAct.class, true);
            }
        });
        fileMoverInstallationPkgsNext_iv.setOnClickListener(v -> {
            if (permissions.permission()) {
                intentFun(DeepCleanAllPackagesAct.class, true);

            }
        });

    }

}