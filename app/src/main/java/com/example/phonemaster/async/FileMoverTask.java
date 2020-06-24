package com.example.phonemaster.async;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

import java.util.List;

public class FileMoverTask extends AsyncTask<Void,Integer,String> {

    Utils utils ;
    Context context;
    String disDirName;
    AlertDialog.Builder builder;
    List<String> sourcePathList;
    ProgressBar progressBar;
    AlertDialog dialog;

    public FileMoverTask( Context context,List<String> sourcePathList, String disDirName) {
         this.context = context;
         this.disDirName = disDirName;
         this.sourcePathList = sourcePathList;;
    }

    @Override
    protected void onPreExecute() {
        utils = new Utils(context);
        builder = new AlertDialog.Builder(context);
        View view = View.inflate(context,R.layout.loading_progressbar_dialog_layout,null);
        builder.setView(view);
        progressBar = view.findViewById(R.id.loading_pb);
        dialog = builder.create();

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (int i = 0; i<sourcePathList.size(); i++)
        {
            utils.moveFile(sourcePathList.get(i),disDirName);
        }
        publishProgress();
        dialog.show();
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing())
        {
            dialog.dismiss();
        }
    }
}
