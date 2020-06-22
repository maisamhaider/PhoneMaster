package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.AllAppsAdapter;
import com.example.phonemaster.async.AllAppsTask;
import com.example.phonemaster.utils.SimpleDividerItemDecoration;

public class UnInstallAppAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_install_app);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView allAppsUnInstallApp_rv = findViewById(R.id.allAppsUnInstallApp_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this );
        linearLayoutManager.setOrientation( RecyclerView.VERTICAL );

        allAppsUnInstallApp_rv.addItemDecoration(new SimpleDividerItemDecoration(this));

        AllAppsAdapter allAppsAdapter = new AllAppsAdapter(this);
        AllAppsTask allAppsTsk = new AllAppsTask(this, allAppsAdapter, allAppsUnInstallApp_rv);
        allAppsTsk.execute(  );
    }
}