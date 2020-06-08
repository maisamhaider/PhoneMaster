package com.example.phonemaster.async;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.phonemaster.R;
import com.example.phonemaster.adapters.AllAppsAdapter;
import com.example.phonemaster.models.AllApplicationsModel;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class AllAppsTask extends AsyncTask<Void, Integer, String> {

    Context context;
     private AllAppsAdapter allAppsAdapter;
      private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<AllApplicationsModel> list;
    Utils utils;
    LoadingDialog loadingDialog;

    public AllAppsTask(Context context, AllAppsAdapter allAppsAdapter,
                       RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        this.context = context;
        this.allAppsAdapter = allAppsAdapter;
        this.recyclerView = recyclerView;
        this.linearLayoutManager = linearLayoutManager;
        this.list = new ArrayList<>();
        utils = new Utils(context);
        loadingDialog = new LoadingDialog(context);


    }

    @Override
    protected void onPreExecute() {
        try {
            loadingDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String doInBackground(Void... voids) {
        for (int i = 0; i < utils.GetAllInstalledApkInfo().size(); i++) {
            String pName = utils.GetAllInstalledApkInfo().get(i);
            String appName = utils.GetAppName(pName);
            Drawable appIcon = utils.getAppIconByPackageName(pName);

            list.add(new AllApplicationsModel(pName, appName, appIcon));
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        recyclerView.setLayoutManager(linearLayoutManager);
        allAppsAdapter.setList(list);
        recyclerView.setAdapter(allAppsAdapter);
        allAppsAdapter.notifyDataSetChanged();
             loadingDialog.dismiss();


    }


}
