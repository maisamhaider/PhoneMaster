package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.utils.StorageUtils;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JunkFilesAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junk_files_atc);
        SharedPreferences preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        StorageUtils storageUtils = new StorageUtils();
        SharedPreferences.Editor editor = preferences.edit();

        LinearLayout junkFileCleanBtn_ll = findViewById(R.id.junkFileCleanBtn_ll);

        ImageView cacheJunkClear_iv = findViewById(R.id.cacheJunkClear_iv);
        ImageView residualJunk_iv = findViewById(R.id.residualJunk_iv);
        ImageView installationPackages_iv = findViewById(R.id.installationPackages_iv);
        ImageView junkFileEmptyFolderClear_iv = findViewById(R.id.junkFileEmptyFolderClear_iv);

        ConstraintLayout cacheJunkBin_cl = findViewById(R.id.cacheJunkBin_cl);
        cacheJunkBin_cl.setVisibility(View.GONE);

        //junk File Empty Folder
        if (preferences.getBoolean("isEFolderClean", false)) {
            junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_deselect);
        } else {
            junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_select);
        }

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

        if (preferences.getBoolean("isCacheJunkClean", false)) {
            cacheJunkClear_iv.setImageResource(R.drawable.ic_deselect);
        } else {
            cacheJunkClear_iv.setImageResource(R.drawable.ic_select);
        }

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

        //installation pkg.s
        if (preferences.getBoolean("isInstallationPkgClean", false)) {
            installationPackages_iv.setImageResource(R.drawable.ic_deselect);
        } else {
            installationPackages_iv.setImageResource(R.drawable.ic_select);
        }

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

        //residual junk
        if (preferences.getBoolean("isResidualJunkClean", false)) {
            residualJunk_iv.setImageResource(R.drawable.ic_deselect);
        } else {
            residualJunk_iv.setImageResource(R.drawable.ic_select);
        }

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
                Calendar current = Calendar.getInstance();


                if (preferences.getLong("junkCleanTime", current.getTimeInMillis()) <= current.getTimeInMillis()) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cacheJunkBin_cl.setVisibility(View.VISIBLE);
                        }
                    }, 5000);


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
                }
                else {
                    Toast.makeText(JunkFilesAct.this, "cleaned", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}