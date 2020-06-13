package com.example.phonemaster.async;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.adapters.FastChargeAllAppsAdapter;
import com.example.phonemaster.database.Db;
import com.example.phonemaster.models.AllApplicationsModel;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class FastAllAppsTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private FastChargeAllAppsAdapter appsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<AllApplicationsModel> list;
    Utils utils;
    LoadingDialog loadingDialog;
    Db db ;

    public FastAllAppsTask(Context context, FastChargeAllAppsAdapter appsAdapter,
                           RecyclerView recyclerView, LinearLayoutManager linearLayoutManager,Db db) {
        this.context = context;
        this.appsAdapter = appsAdapter;
        this.recyclerView = recyclerView;
        this.linearLayoutManager = linearLayoutManager;
        this.list = new ArrayList<>();
        utils = new Utils(context);
        loadingDialog = new LoadingDialog(context);
        this.db = db;


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
        appsAdapter.setList(list,db);
        recyclerView.setAdapter(appsAdapter);
        appsAdapter.notifyDataSetChanged();
        loadingDialog.dismiss();


    }


}
