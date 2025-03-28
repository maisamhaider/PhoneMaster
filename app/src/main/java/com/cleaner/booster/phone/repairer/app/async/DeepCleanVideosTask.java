package com.cleaner.booster.phone.repairer.app.async;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cleaner.booster.phone.repairer.app.adapters.DeepCleanVideosAdapter;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanVideosModel;
import com.cleaner.booster.phone.repairer.app.utils.LoadingDialog;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class DeepCleanVideosTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private DeepCleanVideosAdapter deepCleanVideosAdapter;
    private RecyclerView recyclerView;
    private List<DeepCleanVideosModel> list;
    Utils utils;
    LoadingDialog loadingDialog;

    public DeepCleanVideosTask(Context context, DeepCleanVideosAdapter deepCleanVideosAdapter,
                               RecyclerView recyclerView) {
        this.context = context;
        this.deepCleanVideosAdapter = deepCleanVideosAdapter;
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
        list = utils.getAllVideosPaths();
    return null;
    }

    @Override
    protected void onPostExecute(String s) {
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        deepCleanVideosAdapter.setFileList(list);
        recyclerView.setAdapter(deepCleanVideosAdapter);
        deepCleanVideosAdapter.notifyDataSetChanged();
            loadingDialog.dismiss();
        }
}
