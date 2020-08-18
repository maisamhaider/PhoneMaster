package com.cleaner.booster.phone.repairer.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.models.CommonModel;
import com.cleaner.booster.phone.repairer.app.utils.StorageUtils;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class JunkFilesAct extends AppCompatActivity {
    private TextView trashCleanLast_tv;
    private GifImageView junkCollecting_giv, junkBinDone_giv, junkGiv;
    private ConstraintLayout cacheJunkBin_cl, whenJunkCleanedMain_cl;
    private List<CommonModel> pkg;
    private Utils utils;
    private String dirPath;
    private StorageUtils storageUtils;
    private SharedPreferences preferences;
    private Calendar current;
    private ConstraintLayout constraintLayout;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junk_files_atc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        storageUtils = new StorageUtils();
        editor = preferences.edit();

        LinearLayout junkFileCleanBtn_ll = findViewById(R.id.junkFileCleanBtn_ll);
        LinearLayout junkFileMoreApps_ll = findViewById(R.id.junkFileMoreApps_ll);

        ImageView cacheJunkClear_iv = findViewById(R.id.cacheJunkClear_iv);
        ImageView residualJunk_iv = findViewById(R.id.residualJunk_iv);
        ImageView installationPackages_iv = findViewById(R.id.installationPackages_iv);
        ImageView junkFileEmptyFolderClear_iv = findViewById(R.id.junkFileEmptyFolderClear_iv);
        ImageView junkFileBack_iv = findViewById(R.id.junkFileBack_iv);

        constraintLayout = findViewById(R.id.junkFilesSecond_cl);
        whenJunkCleanedMain_cl = findViewById(R.id.whenJunkCleanedMain_cl);
        cacheJunkBin_cl = findViewById(R.id.cacheJunkBin_cl);
        junkGiv = findViewById(R.id.junk_giv);

        trashCleanLast_tv = findViewById(R.id.trashCleanLast_tv);
        junkCollecting_giv = findViewById(R.id.junkCollecting_giv);
        junkBinDone_giv = findViewById(R.id.junkBinDone_giv);

        cacheJunkBin_cl.setVisibility(View.GONE);
        junkCollecting_giv.setVisibility(View.GONE);
        whenJunkCleanedMain_cl.setVisibility(View.GONE);
        current = Calendar.getInstance();


        junkFileBack_iv.setOnClickListener(v -> finish());
        if (preferences.getLong("junkCleanTime", current.getTimeInMillis()) <= current.getTimeInMillis()) {
            constraintLayout.setVisibility(View.VISIBLE);
            junkGiv.setImageResource(R.drawable.junk_bin_white);
        } else {
            junkGiv.setImageResource(R.drawable.optimized);
            constraintLayout.setVisibility(View.GONE);
            whenJunkCleanedMain_cl.setVisibility(View.VISIBLE);

        }
        if (preferences.getBoolean("isEFolderClean", false)) {
            junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_select);
        } else {
            junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_deselect);
        }

        if (preferences.getBoolean("isCacheJunkClean", false)) {
            cacheJunkClear_iv.setImageResource(R.drawable.ic_select);

        } else {
            cacheJunkClear_iv.setImageResource(R.drawable.ic_deselect);
        }
        if (preferences.getBoolean("isInstallationPkgClean", false)) {
            installationPackages_iv.setImageResource(R.drawable.ic_select);

        } else {
            installationPackages_iv.setImageResource(R.drawable.ic_deselect);
        }
        if (preferences.getBoolean("isResidualJunkClean", false)) {

            residualJunk_iv.setImageResource(R.drawable.ic_select);

        } else {
            residualJunk_iv.setImageResource(R.drawable.ic_deselect);
        }

        //junk File Empty Folder
        junkFileEmptyFolderClear_iv.setOnClickListener(v -> {
            if (preferences.getBoolean("isEFolderClean", false)) {
                junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_deselect);
                editor.putBoolean("isEFolderClean", false).commit();
            } else {
                junkFileEmptyFolderClear_iv.setImageResource(R.drawable.ic_select);
                editor.putBoolean("isEFolderClean", true).commit();
            }
        });

        //cache junks
        cacheJunkClear_iv.setOnClickListener(v -> {
            if (preferences.getBoolean("isCacheJunkClean", false)) {
                cacheJunkClear_iv.setImageResource(R.drawable.ic_deselect);
                editor.putBoolean("isCacheJunkClean", false).commit();
            } else {
                cacheJunkClear_iv.setImageResource(R.drawable.ic_select);
                editor.putBoolean("isCacheJunkClean", true).commit();
            }
        });

        installationPackages_iv.setOnClickListener(v -> {
            if (preferences.getBoolean("isInstallationPkgClean", false)) {
                installationPackages_iv.setImageResource(R.drawable.ic_deselect);
                editor.putBoolean("isInstallationPkgClean", false).commit();
            } else {
                installationPackages_iv.setImageResource(R.drawable.ic_select);
                editor.putBoolean("isInstallationPkgClean", true).commit();
            }
        });


        residualJunk_iv.setOnClickListener(v -> {
            if (preferences.getBoolean("isResidualJunkClean", false)) {
                residualJunk_iv.setImageResource(R.drawable.ic_deselect);
                editor.putBoolean("isResidualJunkClean", false).commit();
            } else {
                residualJunk_iv.setImageResource(R.drawable.ic_select);
                editor.putBoolean("isResidualJunkClean", true).commit();
            }
        });


        dirPath = String.valueOf(Environment.getExternalStorageDirectory());
        utils = new Utils(this);


        junkFileCleanBtn_ll.setOnClickListener(v -> {
            new CacheClean().execute();
        });

        junkFileMoreApps_ll.setOnClickListener(v -> {
            Toast.makeText(JunkFilesAct.this, "More Apps ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=SPAG+Apps+Studio")));

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new JunkCleanerTask().execute();
    }


    public void lastView() {
        junkBinDone_giv.setImageResource(R.drawable.junk_bin);
        trashCleanLast_tv.setText("FINISHED CLEANING");
        junkCollecting_giv.setVisibility(View.GONE);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            cacheJunkBin_cl.setVisibility(View.GONE);
            junkBinDone_giv.setVisibility(View.GONE);
            whenJunkCleanedMain_cl.setVisibility(View.VISIBLE);
        }, 3000);
    }

    @SuppressLint("StaticFieldLeak")
    class JunkCleanerTask extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            pkg = utils.getAllPackages(dirPath);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


    class CacheClean extends AsyncTask<Void, Integer, String> {
        String p = Environment.getExternalStorageDirectory().getAbsolutePath();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cacheJunkBin_cl.setVisibility(View.VISIBLE);
            junkCollecting_giv.setVisibility(View.VISIBLE);
            trashCleanLast_tv.setText("FINISHED CLEANING");
            Handler handler = new Handler();
            handler.postDelayed(() -> lastView(), 3000);
        }

        @Override
        protected String doInBackground(Void... voids) {
            cleanOrFinishFun(true, p);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            constraintLayout.setVisibility(View.GONE);
            junkGiv.setImageResource(R.drawable.optimized);
        }
    }
    public void cleanOrFinishFun(boolean isClean, String p) {
        if (isClean) {
            if (preferences.getLong("junkCleanTime", current.getTimeInMillis()) <= current.getTimeInMillis()) {
                if (preferences.getBoolean("isCacheJunkClean", false)) {
                    storageUtils.deleteCache(JunkFilesAct.this);
                }
                if (preferences.getBoolean("isEFolderClean", false)) {
                    storageUtils.deleteEmptyFolder(dirPath);
                    storageUtils.deleteCache(JunkFilesAct.this);
                    for (CommonModel path : storageUtils.getAllUnUsableFile(p)) {
                        File file = new File(path.getPath());
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
                if (preferences.getBoolean("isInstallationPkgClean", false)) {
                    if (pkg.size() != 0) {
                        for (int i = 0; i < pkg.size(); i++) {
                            pkg.get(i).getPath();
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
            } else {
                Toast.makeText(JunkFilesAct.this, "Not cleaned", Toast.LENGTH_SHORT).show();
            }
        } else {
            finish();
        }
    }
}