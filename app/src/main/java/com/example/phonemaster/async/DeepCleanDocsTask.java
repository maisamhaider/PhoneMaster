package com.example.phonemaster.async;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.adapters.DeepCleanAudioAdapter;
import com.example.phonemaster.adapters.DeepCleanDocsAdapter;
import com.example.phonemaster.models.DeepCleanAudioModel;
import com.example.phonemaster.models.DeepCleanDocsModel;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DeepCleanDocsTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private DeepCleanDocsAdapter deepCleanDocsAdapter;
    private RecyclerView recyclerView;
    private List<DeepCleanDocsModel> list;
    Utils utils;
    LoadingDialog loadingDialog;

    public DeepCleanDocsTask(Context context, DeepCleanDocsAdapter deepCleanDocsAdapter,
                             RecyclerView recyclerView) {
        this.context = context;
        this.deepCleanDocsAdapter = deepCleanDocsAdapter;
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
        list = utils.getAllDocs(String.valueOf(Environment.getExternalStorageDirectory()));
    return null;
    }

    @Override
    protected void onPostExecute(String s) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        deepCleanDocsAdapter.setFileList(list);
        recyclerView.setAdapter(deepCleanDocsAdapter);
        deepCleanDocsAdapter.notifyDataSetChanged();
            loadingDialog.dismiss();
        }
}
