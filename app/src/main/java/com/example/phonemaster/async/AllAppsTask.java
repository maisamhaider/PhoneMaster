package com.example.phonemaster.async;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.phonemaster.adapters.AllAppsAdapter;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class AllAppsTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private AllAppsAdapter allAppsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<String> list;
    Utils utils;
    LoadingDialog loadingDialog;

    public AllAppsTask(Context context, AllAppsAdapter allAppsAdapter,
                       RecyclerView recyclerView  ) {
        this.context = context;
        this.allAppsAdapter = allAppsAdapter;
        this.recyclerView = recyclerView;
         this.list = new ArrayList<>();
        utils = new Utils(context);
        loadingDialog = new LoadingDialog(context);

    }

    @Override
    protected void onPreExecute() {
        linearLayoutManager = new LinearLayoutManager(context);
        try {
            loadingDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String doInBackground(Void... voids) {


            list = utils.GetAllInstalledApkInfo();
            return null;
    }

    @Override
    protected void onPostExecute(String s) {
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        allAppsAdapter.setList(list);
        recyclerView.setAdapter(allAppsAdapter);
        allAppsAdapter.notifyDataSetChanged();
        loadingDialog.dismiss();


    }


}
