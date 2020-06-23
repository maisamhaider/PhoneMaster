package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.content.pm.ChangedPackages;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.AllAppsAdapter;
import com.example.phonemaster.async.AllAppsTask;
import com.example.phonemaster.utils.SimpleDividerItemDecoration;

import org.w3c.dom.Text;

import pl.droidsonroids.gif.GifImageView;

public class UnInstallAppAct extends AppCompatActivity {

    private GifImageView uninstall_gif;
    private ImageView uninstall_iv;
    private TextView totalNumber_tv;
    AllAppsAdapter allAppsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_install_app);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView allAppsUnInstallApp_rv = findViewById(R.id.allAppsUnInstallApp_rv);
        uninstall_gif = findViewById(R.id.uninstall_gif);
        uninstall_iv = findViewById(R.id.uninstall_iv);
        totalNumber_tv = findViewById(R.id.tv_total_apps);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        allAppsUnInstallApp_rv.addItemDecoration(new SimpleDividerItemDecoration(this));

        allAppsAdapter = new AllAppsAdapter(this);
        AllAppsTask allAppsTsk = new AllAppsTask(this, allAppsAdapter, allAppsUnInstallApp_rv);
        allAppsTsk.execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation();
            }
        }, 1_000);

    }


    private void startAnimation() {

        uninstall_gif.setVisibility(View.VISIBLE);
        uninstall_iv.setVisibility(View.GONE);
        totalNumber_tv.setVisibility(View.GONE);

        ValueAnimator animator = ValueAnimator.ofInt(0, allAppsAdapter.getItemCount());
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(0);
        animator.setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                totalNumber_tv.setText(String.valueOf(value));
                if (value == allAppsAdapter.getItemCount()) {
                    uninstall_gif.setVisibility(View.GONE);
                    uninstall_iv.setVisibility(View.VISIBLE);
                    totalNumber_tv.setVisibility(View.VISIBLE);
                }
            }
        });

        animator.start();
    }

}