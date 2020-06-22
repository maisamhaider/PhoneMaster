package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.utils.StorageUtils;
import com.example.phonemaster.utils.Utils;

import java.util.Calendar;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class JunkFilesAct extends AppCompatActivity {
    boolean isFirstThreadDone;
    TextView trashCleanLast_tv;
    GifImageView junkCollecting_giv, junkBinDone_giv,junkGiv;
    ConstraintLayout cacheJunkBin_cl,whenJunkCleanedMain_cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junk_files_atc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        StorageUtils storageUtils = new StorageUtils();
        SharedPreferences.Editor editor = preferences.edit();

        LinearLayout junkFileCleanBtn_ll = findViewById(R.id.junkFileCleanBtn_ll);
        LinearLayout junkFileMoreApps_ll = findViewById(R.id.junkFileMoreApps_ll);

        ImageView cacheJunkClear_iv = findViewById(R.id.cacheJunkClear_iv);
        ImageView residualJunk_iv = findViewById(R.id.residualJunk_iv);
        ImageView installationPackages_iv = findViewById(R.id.installationPackages_iv);
        ImageView junkFileEmptyFolderClear_iv = findViewById(R.id.junkFileEmptyFolderClear_iv);
        ImageView junkFileBack_iv = findViewById(R.id.junkFileBack_iv);

        ConstraintLayout constraintLayout = findViewById(R.id.junkFilesSecond_cl);
        whenJunkCleanedMain_cl= findViewById(R.id.whenJunkCleanedMain_cl);
        cacheJunkBin_cl = findViewById(R.id.cacheJunkBin_cl);
        junkGiv = findViewById(R.id.junk_giv);

        trashCleanLast_tv = findViewById(R.id.trashCleanLast_tv);
        junkCollecting_giv = findViewById(R.id.junkCollecting_giv);
        junkBinDone_giv = findViewById(R.id.junkBinDone_giv);

        cacheJunkBin_cl.setVisibility(View.GONE);
        junkCollecting_giv.setVisibility(View.GONE);
        whenJunkCleanedMain_cl.setVisibility(View.GONE);
        Calendar current = Calendar.getInstance();



        junkFileBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (preferences.getLong("junkCleanTime", current.getTimeInMillis()) <= current.getTimeInMillis()) {
            constraintLayout.setVisibility(View.VISIBLE);
            junkGiv.setImageResource(R.drawable.junk_bin_white);
        } else {
            junkGiv.setImageResource(R.drawable.optimized);
            constraintLayout.setVisibility(View.GONE);
            whenJunkCleanedMain_cl.setVisibility(View.VISIBLE);

        }

        //junk File Empty Folder

        junkFileEmptyFolderClear_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("isEFolderClean", false)) {
                    junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_deselect);
                    editor.putBoolean("isEFolderClean", false).commit();
                } else {
                    junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_select);
                    editor.putBoolean("isEFolderClean", true).commit();
                }
            }
        });

        //cache junks

        cacheJunkClear_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("isCacheJunkClean", false)) {
                    cacheJunkClear_iv.setImageResource(R.drawable.ic_deselect);
                    editor.putBoolean("isCacheJunkClean", false).commit();
                } else {
                    cacheJunkClear_iv.setImageResource(R.drawable.ic_select);
                    editor.putBoolean("isCacheJunkClean", true).commit();
                }
            }
        });

        installationPackages_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("isInstallationPkgClean", false)) {
                    installationPackages_iv.setImageResource(R.drawable.ic_deselect);
                    editor.putBoolean("isInstallationPkgClean", false).commit();
                } else {
                    installationPackages_iv.setImageResource(R.drawable.ic_select);
                    editor.putBoolean("isInstallationPkgClean", true).commit();
                }
            }
        });



        residualJunk_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("isResidualJunkClean", false)) {
                    residualJunk_iv.setImageResource(R.drawable.ic_deselect);
                    editor.putBoolean("isResidualJunkClean", false).commit();
                } else {
                    residualJunk_iv.setImageResource(R.drawable.ic_select);
                    editor.putBoolean("isResidualJunkClean", true).commit();
                }
            }
        });


        String dirPath = String.valueOf(Environment.getExternalStorageDirectory());

        Utils utils = new Utils(this);
        List<DeepCleanPackagesModel> pkg = utils.getAllPackages(dirPath);

        junkFileCleanBtn_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (preferences.getLong("junkCleanTime", current.getTimeInMillis()) <= current.getTimeInMillis()) {

                    cacheJunkBin_cl.setVisibility(View.VISIBLE);
                    junkCollecting_giv.setVisibility(View.VISIBLE);
                    trashCleanLast_tv.setText("FINISHED CLEANING");

                     Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lastView();
                        }
                    }, 3000);


                    if (preferences.getBoolean("isCacheJunkClean", false)) {
                        storageUtils.deleteCache(JunkFilesAct.this);
                    }


                    if (preferences.getBoolean("isEFolderClean", false)) {
                        storageUtils.deleteEmptyFolder(dirPath);
                    }


                    if (preferences.getBoolean("isInstallationPkgClean", false)) {
                        if (pkg.size() != 0) {
                            for (int i = 0; i < pkg.size(); i++) {
                                pkg.get(i).getPkgPath();
                            }
                        }

                    }

                    if (preferences.getBoolean("isResidualJunkClean", false)) {
                        storageUtils.deleteCache(JunkFilesAct.this);
                    }
                    Calendar nextTime = Calendar.getInstance();

                    nextTime.add(Calendar.MINUTE, 5);
                    long dateMilliSec = nextTime.getTimeInMillis();

                    editor.putLong("junkCleanTime", dateMilliSec).commit();
                    constraintLayout.setVisibility(View.GONE);
                    junkGiv.setImageResource(R.drawable.optimized);

                } else {
                    Toast.makeText(JunkFilesAct.this, "Not cleaned", Toast.LENGTH_SHORT).show();
                }

            }
        });

        junkFileMoreApps_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(JunkFilesAct.this, "More Apps ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void lastView() {
        junkBinDone_giv.setImageResource(R.drawable.junk_bin);
        trashCleanLast_tv.setText("FINISHED CLEANING");
        junkCollecting_giv.setVisibility(View.GONE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cacheJunkBin_cl.setVisibility(View.GONE);
                junkBinDone_giv.setVisibility(View.GONE);
                whenJunkCleanedMain_cl.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }
}