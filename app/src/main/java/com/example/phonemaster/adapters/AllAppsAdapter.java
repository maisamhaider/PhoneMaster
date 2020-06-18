package com.example.phonemaster.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
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
 import com.example.phonemaster.utils.AppUtility;
import com.example.phonemaster.utils.Utils;


import java.util.ArrayList;
import java.util.List;

import bot.box.appusage.handler.Monitor;
import bot.box.appusage.utils.UsageUtils;

public class AllAppsAdapter extends RecyclerView.Adapter<AllAppsAdapter.AllAppsHolder>   {

    private List<String> apps;
    private List<String> fullList;
    private Context context;
    private AppUtility appUtility;
     private Utils utils;

    @SuppressLint("NewApi")
    public AllAppsAdapter(Context context) {

        this.context = context;
        appUtility = new AppUtility( context );
        apps = new ArrayList<>(  );
        fullList = new ArrayList<>(  );

     }

     public void setList(List<String> apps){
        this.apps.clear();
         this.fullList.clear();
        this.apps = apps;
         this.fullList.addAll( apps );
     }

    @NonNull
    @Override
    public AllAppsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.allappsrecyclerviewitem_lo, parent, false );
        return new AllAppsHolder( view );
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull AllAppsHolder holder, final int position) {
        utils = new Utils(context);
         String appName = utils.GetAppName( apps.get( position )) ;
        final String appPackage = apps.get( position );

        holder.appName_Tv.setText( appName );

        Glide.with(context).load( UsageUtils.parsePackageIcon(apps.get( position ), R.mipmap.ic_launcher))
                .transition(new DrawableTransitionOptions().crossFade()).into(holder.appImage_Iv);

         if (Monitor.hasUsagePermission())


        holder.allAppsMain_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = context.getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage( appPackage );
                if (intent != null) {
                    context.startActivity(intent);
                }
            }
        });

        holder.deleteAppImage_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = apps.get( position );


                if (appUtility.isSystemApp( packageName )) {
                    Toast.makeText( context, "Can not Uninstall system's application", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if (!appUtility.isAppPreLoaded( packageName )) {
                    Intent intent = new Intent( Intent.ACTION_DELETE );
                    intent.setData( Uri.parse( "package:" + packageName ) );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    context.startActivity( intent );
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }



    class AllAppsHolder extends RecyclerView.ViewHolder {
        TextView appName_Tv, appInstalledTime_Tv;
        ImageView appImage_Iv, deleteAppImage_Iv;
        RelativeLayout allAppsMain_Rl;

        public AllAppsHolder(@NonNull View itemView) {
            super( itemView );
            appName_Tv = itemView.findViewById( R.id.allAppName_Tv );
             appImage_Iv = itemView.findViewById( R.id.allAppImage_Iv );
            allAppsMain_Rl = itemView.findViewById( R.id.allAppsMain_Rl );
            deleteAppImage_Iv = itemView.findViewById( R.id.allAppSelected_deselect_Iv );

        }
    }
}
