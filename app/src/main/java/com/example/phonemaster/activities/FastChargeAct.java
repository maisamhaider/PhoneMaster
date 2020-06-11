package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.FastChargeAllAppsAdapter;
import com.example.phonemaster.async.AllAppsTask;
import com.example.phonemaster.async.FastAllAppsTask;
import com.example.phonemaster.database.Db;

public class FastChargeAct extends AppCompatActivity {

    FastAllAppsTask allAppsTask;
    FastChargeAllAppsAdapter adapter;
    Db db;
    LinearLayoutManager linearLayoutManager;
    RecyclerView fastCharge_rv;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_charge);
        fastCharge_rv = findViewById(R.id.fastCharge_rv);
        linearLayoutManager = new LinearLayoutManager(this);


        db = new Db(this);
         adapter = new FastChargeAllAppsAdapter(this);

         allAppsTask = new FastAllAppsTask(this,adapter,fastCharge_rv,linearLayoutManager,db);
         allAppsTask.execute();
    }
}