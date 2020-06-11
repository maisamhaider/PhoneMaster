package com.example.phonemaster.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import com.example.phonemaster.database.Db;
import com.example.phonemaster.models.AllApplicationsModel;
import com.example.phonemaster.utils.AppUtility;

import java.util.ArrayList;
import java.util.List;

import bot.box.appusage.handler.Monitor;
import bot.box.appusage.utils.UsageUtils;

public class FastChargeAllAppsAdapter extends RecyclerView.Adapter<FastChargeAllAppsAdapter.AllAppsHolder>   {

    private List<AllApplicationsModel> apps;
    private List<AllApplicationsModel> fullList;
    private Context context;
    private AppUtility appUtility;
    private MainActivity mainActivity;
    private Db db;


    @SuppressLint("NewApi")
    public FastChargeAllAppsAdapter(Context context) {

        this.context = context;
        appUtility = new AppUtility( context );
        apps = new ArrayList<>(  );
        fullList = new ArrayList<>(  );
        db = new Db(context);


     }

     public void setList(List<AllApplicationsModel> apps,Db db){
        this.apps.clear();
         this.fullList.clear();
        this.apps = apps;
         this.fullList.addAll( apps );
         this.db = db;
     }

    @NonNull
    @Override
    public AllAppsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.fast_charge_allapp_lo, parent, false );
        return new AllAppsHolder( view );
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull AllAppsHolder holder, final int position) {

         mainActivity = new MainActivity();
        String appName = apps.get( position ).getAppName();
        final String appPackage = apps.get( position ).getPackageName();

        holder.fastChargeAllAppName_Tv.setText( appName );

         Cursor cursor = db.getPkg(appPackage );
         if (cursor.getCount()==0)
         {

         }
         while (cursor.moveToNext())
         {
             String pgk = cursor.getString(0);
             if (appPackage.matches(pgk))
             {
                 holder.fastChargeAllApp_cb.setChecked(true);
             }
             else
             {
                 holder.fastChargeAllApp_cb.setChecked(false);

             }
         }
        Glide.with(context).load( UsageUtils.parsePackageIcon(apps.get( position ).getPackageName(), R.mipmap.ic_launcher))
                .transition(new DrawableTransitionOptions().crossFade()).into(holder.fastChargeAllAppImage_Iv);




        holder.fastChargeAllApp_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String packageName = apps.get( position ).getPackageName();

                if (isChecked)
                {
                   boolean isInserted =  db.insertPkgPath(packageName);
                    if (isInserted)
                    {
                        Toast.makeText(context, "pkg Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "pkg not Inserted", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    boolean isDeleted = db.deletePkgPath(packageName);
                    if (isDeleted)
                    {
                        Toast.makeText(context, "pkg Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "pkg Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return apps.size();
    }



    class AllAppsHolder extends RecyclerView.ViewHolder {
        TextView fastChargeAllAppName_Tv, appInstalledTime_Tv;
        ImageView fastChargeAllAppImage_Iv;
        CheckBox fastChargeAllApp_cb;

        public AllAppsHolder(@NonNull View itemView) {
            super( itemView );
            fastChargeAllAppName_Tv = itemView.findViewById( R.id.fastChargeAllAppName_Tv );
            fastChargeAllAppImage_Iv = itemView.findViewById( R.id.fastChargeAllAppImage_Iv );
            fastChargeAllApp_cb = itemView.findViewById( R.id.fastChargeAllApp_cb );

        }
    }
}
