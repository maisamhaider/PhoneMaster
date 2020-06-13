package com.example.phonemaster.async;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.adapters.DeepCleanAudioAdapter;
import com.example.phonemaster.models.DeepCleanAudioModel;
import com.example.phonemaster.utils.LoadingDialog;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class DeepCleanAudiosTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private DeepCleanAudioAdapter deepCleanAudioAdapter;
    private RecyclerView recyclerView;
    private List<DeepCleanAudioModel> list;
    Utils utils;
    LoadingDialog loadingDialog;

    public DeepCleanAudiosTask(Context context, DeepCleanAudioAdapter deepCleanAudioAdapter,
                               RecyclerView recyclerView) {
        this.context = context;
        this.deepCleanAudioAdapter = deepCleanAudioAdapter;
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
        list = utils.getAllAudiosPaths();
    return null;
    }

    @Override
    protected void onPostExecute(String s) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        deepCleanAudioAdapter.setFileList(list);
        recyclerView.setAdapter(deepCleanAudioAdapter);
        deepCleanAudioAdapter.notifyDataSetChanged();
            loadingDialog.dismiss();
        }
}
