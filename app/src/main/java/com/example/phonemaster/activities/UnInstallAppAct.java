package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.AllAppsAdapter;
import com.example.phonemaster.async.AllAppsTask;
import com.example.phonemaster.utils.SimpleDividerItemDecoration;

public class UnInstallAppAct extends AppCompatActivity {
    private AllAppsTask allAppsTsk;
    private RecyclerView allAppsUnInstallApp_rv;
    private AllAppsAdapter allAppsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_install_app);

        allAppsUnInstallApp_rv =  findViewById( R.id.allAppsUnInstallApp_rv );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this );
        linearLayoutManager.setOrientation( RecyclerView.VERTICAL );

        allAppsUnInstallApp_rv.addItemDecoration(new SimpleDividerItemDecoration(this));

        allAppsAdapter = new AllAppsAdapter(this );
        allAppsTsk = new AllAppsTask(this,allAppsAdapter,allAppsUnInstallApp_rv,linearLayoutManager);
        allAppsTsk.execute(  );
    }
}