package com.cleaner.booster.phone.repairer.app.async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cleaner.booster.phone.repairer.app.activities.WhatsAppBaseActivity;
import com.cleaner.booster.phone.repairer.app.adapters.CommonAdapter;
import com.cleaner.booster.phone.repairer.app.models.CommonModel;
import com.cleaner.booster.phone.repairer.app.utils.LoadingDialog;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class WhatsAppCommonTask extends AsyncTask<Void, Integer, String> {

    Context context;
    private CommonAdapter commonAdapter;
    private RecyclerView recyclerView;
    private List<CommonModel> list;
    Utils utils;
    LoadingDialog loadingDialog;
    String whatThingIs;
    File file1;
    private File file12;



    public WhatsAppCommonTask(Context context, CommonAdapter commonAdapter, RecyclerView recyclerView,String whatThingIs) {
        this.context = context;
        this.commonAdapter = commonAdapter;
        this.recyclerView = recyclerView;
        this.list = new ArrayList<>();
        utils = new Utils(context);
        loadingDialog = new LoadingDialog(context);
        this.whatThingIs = whatThingIs;


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
        if (whatThingIs.matches("videos"))
        {
            file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Video");
        }
        else
        if (whatThingIs.matches("audios"))
        {
            file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Audio");
        }
        else
        if (whatThingIs.matches("images"))
        {
            file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Images");
        }
        else
        if (whatThingIs.matches("doc"))
        {
            file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Documents");

        } else
        if (whatThingIs.matches("BUCH"))
        {
            //back up and conversation history
            file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Backups");
             file12 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Databases");
//            List<CommonModel> list1 ;
//            List<CommonModel> list2 ;

            list=  utils.getListFiles(file1);
//            list2 = utils.getListFiles(file12);
            list.addAll(utils.getListFiles(file12));
        }


        list = utils.getListFiles(file1);


    return null;
    }

    @Override
    protected void onPostExecute(String s) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        commonAdapter.setFileList(list);
        recyclerView.setAdapter(commonAdapter);
        commonAdapter.notifyDataSetChanged();
        loadingDialog.dismiss();
        if (list.size()>0){
             ((WhatsAppBaseActivity)context).toggleVisibility(true);
        }else{
             ((WhatsAppBaseActivity)context).toggleVisibility(false);
        }
        }
}
