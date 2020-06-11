package com.example.phonemaster.adapters;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.phonemaster.R;
import com.example.phonemaster.activities.MainActivity;
import com.example.phonemaster.models.AllApplicationsModel;
import com.example.phonemaster.utils.AppUtility;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import bot.box.appusage.handler.Monitor;
import bot.box.appusage.utils.UsageUtils;

public class processesAdapter extends RecyclerView.Adapter<processesAdapter.AllAppsHolder>   {

    private List<ActivityManager.RunningServiceInfo> apps;
    private List<String> fullList;
    private Context context;
    private AppUtility appUtility;
    private MainActivity mainActivity;
    Utils utils;


    @SuppressLint("NewApi")
    public processesAdapter(Context context,List<ActivityManager.RunningServiceInfo> apps) {

        this.context = context;
        appUtility = new AppUtility( context );
        this.apps = apps;
        fullList = new ArrayList<>(  );


     }



    @NonNull
    @Override
    public AllAppsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.processes_lo, parent, false );
        return new AllAppsHolder( view );
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull AllAppsHolder holder, final int position) {
        utils = new Utils(context);
         String appName = utils.GetAppName(apps.get( position ).process);
        final String appPackage = apps.get( position ).process;

        holder.processesName_Tv.setText( appName );

        Glide.with(context).load( UsageUtils.parsePackageIcon(apps.get( position ).process, R.mipmap.ic_launcher))
                .transition(new DrawableTransitionOptions().crossFade()).into(holder.processes_Iv);
        holder.processes_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {}
                else
                {}
            }
        });


    }


    @Override
    public int getItemCount() {
        return apps.size();
    }



    class AllAppsHolder extends RecyclerView.ViewHolder {
        TextView processesName_Tv;
        ImageView processes_Iv;
        CheckBox processes_cb;
        public AllAppsHolder(@NonNull View itemView) {
            super( itemView );
            processesName_Tv = itemView.findViewById( R.id.processesName_Tv );
            processes_Iv = itemView.findViewById( R.id.processes_Iv );
            processes_cb = itemView.findViewById( R.id.processes_cb );

        }
    }
}
