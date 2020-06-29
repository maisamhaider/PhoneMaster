package com.example.phonemaster.activities;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.StorageUtils;
import com.example.phonemaster.utils.Utils;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static com.example.phonemaster.R.raw.notification_sound;

public class ChargingLockedScreenAct extends AppCompatActivity {

    private ProgressBar pbBattery;
    private TextView tvCharging, tvPercentage;
    private View vHead;
    LoadingDialog mLoadingDialog;
    private int selection = 0;
    private StorageUtils storageUtils;
    private Utils utils;
    private String dirPath;
    private ActivityManager am;
    private MediaPlayer mp;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_locked_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        utils = new Utils(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor = preferences.edit();

        mLoadingDialog = new LoadingDialog(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            this.setShowWhenLocked(true);
        }

        ImageView ivJunkFile = findViewById(R.id.iv_junk_file);
        ImageView ivCpuCooler = findViewById(R.id.iv_cpu_cooler);
        ImageView ivBoostPhone = findViewById(R.id.iv_boost_phone);
        tvPercentage = findViewById(R.id.tv_percentage);
        tvCharging = findViewById(R.id.tv_charging);
        pbBattery = findViewById(R.id.pb_battery);
        vHead = findViewById(R.id.v_head);
        am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        storageUtils = new StorageUtils();
        dirPath = String.valueOf(Environment.getExternalStorageDirectory());

        ivJunkFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 1;
                new BackgroundTask().execute();

            }
        });
        ivCpuCooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 2;
                new BackgroundTask().execute();
            }
        });

        ivBoostPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 3;
                new BackgroundTask().execute();
            }
        });

        vHead.setBackground(getResources().getDrawable(R.drawable.s_bg_head));
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        startAnimation(60);
    }

    private void startAnimation(int setLevel) {
        ValueAnimator animator = ValueAnimator.ofInt(0, setLevel);
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(0);
        animator.setDuration(2_000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                pbBattery.setProgress(value);
                String bValue = value + "%";
                tvPercentage.setText(bValue);
                if (value == 100) {
                    playOrVibrate();
                    vHead.setBackground(getResources().getDrawable(R.drawable.bg_head));
                }
            }
        });
        animator.start();
    }

    private void playOrVibrate() {

        SharedPreferences preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        if (preferences.getBoolean("FULL_CHARGED_ALARM", false)) {
            if (preferences.getBoolean("FULL_CHARGED_VIBRATE", false)) {

                Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(2000);
            }

            if (preferences.getBoolean("FULL_CHARGED_SOUND", false)) {

                mp = MediaPlayer.create(this, notification_sound);
                try {
                    mp.prepare();
                    mp.start();
                    mp.setLooping(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void cleanJunk() {
        storageUtils.deleteCache(getApplicationContext());
        storageUtils.deleteEmptyFolder(dirPath);
        List<DeepCleanPackagesModel> pkg = utils.getAllPackages(dirPath);

        if (pkg.size() != 0) {
            for (int i = 0; i < pkg.size(); i++) {
                pkg.get(i).getPkgPath();
            }
        }
    }

    public void cpuCooler() {
        for (int i = 0; i < utils.getSystemActiveApps().size(); i++) {
            am.killBackgroundProcesses(utils.getSystemActiveApps().get(i));
            Log.w("cpuCooler", "cpuCooler: " + utils.getSystemActiveApps().size());
        }
    }

    public void phoneBooster() {
        for (int i = 0; i < utils.getSystemActiveApps().size(); i++) {
            am.killBackgroundProcesses(utils.getSystemActiveApps().get(i));
        }
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int batterySource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

            if (batterySource == 0) {
                tvCharging.setVisibility(View.GONE);
            } else {
                tvCharging.setVisibility(View.VISIBLE);
            }
            startAnimation(level);
        }
    };


    @SuppressLint("StaticFieldLeak")
    class BackgroundTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            if (selection == 1) {
                cleanJunk();
            } else if (selection == 2) {
                cpuCooler();
            } else if (selection == 3) {
                phoneBooster();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.putLong("lock_charge_delay", Calendar.getInstance().getTimeInMillis()).commit();
        editor.putBoolean("first_lock_charge", false).commit();

        if (mp != null) {
            mp.release();
            mp = null;
        }
        if (mBatInfoReceiver != null) {
            unregisterReceiver(mBatInfoReceiver);
        }
    }
}