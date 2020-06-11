package com.example.phonemaster.async;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.adapters.DeepCleanAudioAdapter;
import com.example.phonemaster.adapters.DeepCleanPackagesAdapter;
import com.example.phonemaster.models.DeepCleanAudioModel;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class DeepCleanPkgsTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private DeepCleanPackagesAdapter deepCleanPackagesAdapter;
    private RecyclerView recyclerView;
    private List<DeepCleanPackagesModel> list;
    Utils utils;
    LoadingDialog loadingDialog;

    public DeepCleanPkgsTask(Context context, DeepCleanPackagesAdapter deepCleanPackagesAdapter,
                             RecyclerView recyclerView) {
        this.context = context;
        this.deepCleanPackagesAdapter = deepCleanPackagesAdapter;
        this.recyclerView = recyclerView;
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
        list = utils.getAllPackages(String.valueOf(Environment.getExternalStorageDirectory()));
    return null;
    }

    @Override
    protected void onPostExecute(String s) {
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        deepCleanPackagesAdapter.setFileList(list);
        recyclerView.setAdapter(deepCleanPackagesAdapter);
        deepCleanPackagesAdapter.notifyDataSetChanged();
            loadingDialog.dismiss();
        }
}
