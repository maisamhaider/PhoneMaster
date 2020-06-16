package com.example.phonemaster.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.phonemaster.R;
import com.example.phonemaster.activities.MainActivity;
import com.example.phonemaster.database.Db;
import com.example.phonemaster.utils.AppUtility;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import bot.box.appusage.utils.UsageUtils;

public class FastChargeAllAppsAdapter extends RecyclerView.Adapter<FastChargeAllAppsAdapter.AllAppsHolder>   {

    private List<String> apps;
    private List<String> fullList;
    private List<String> checkList;
    private Context context;
    private AppUtility appUtility;
     private Db db;
    private Utils utils;


    @SuppressLint("NewApi")
    public FastChargeAllAppsAdapter(Context context) {

        this.context = context;
        appUtility = new AppUtility( context );
        apps = new ArrayList<>(  );
        fullList = new ArrayList<>(  );
        db = new Db(context);
        this.checkList = new ArrayList<>();


     }

     public void setList(List<String> apps,Db db){
        this.apps.clear();
         this.fullList.clear();
        this.apps = apps;
         this.fullList.addAll( apps );
         this.db = db;
         getDbList();
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
            utils = new Utils(context);
         String appName = utils.GetAppName(apps.get( position ));
        final String appPackage = apps.get( position );

        if (checkList.contains(appPackage)){
            holder.fastChargeAllApp_iv.setImageResource(R.drawable.ic_select);
        }else{
            holder.fastChargeAllApp_iv.setImageResource(R.drawable.ic_deselect);

        }


        holder.fastChargeAllAppName_Tv.setText( appName );

        Glide.with(context).load( UsageUtils.parsePackageIcon(apps.get( position ), R.mipmap.ic_launcher))
                .transition(new DrawableTransitionOptions().crossFade()).into(holder.fastChargeAllAppImage_Iv);




        holder.fastChargeAllApp_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = apps.get( position ) ;
                if (!checkList.contains(packageName))
                {
                    boolean isInserted =  db.insertPkgPath(packageName);
                    if (isInserted)
                    {
                        checkList.add(packageName);
                        Toast.makeText(context, "pkg Inserted", Toast.LENGTH_SHORT).show();
                        holder.fastChargeAllApp_iv.setImageResource(R.drawable.ic_select);

                    }
                    else {
                        Toast.makeText(context, "pkg not Inserted", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    boolean isDeleted = db.deletePkgPath(packageName);
                    if (isDeleted)
                    {
                        if (checkList.contains(packageName))
                        {
                            checkList.remove(packageName);
                        }
                        Toast.makeText(context, "pkg Deleted", Toast.LENGTH_SHORT).show();
                        holder.fastChargeAllApp_iv.setImageResource(R.drawable.ic_deselect);
                    }
                    else {
                        Toast.makeText(context, "pkg not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                getDbList();
            }
        });
    }

    public void getDbList(){

        Cursor cursor = db.getAllPkg();

        while (cursor.moveToNext())
        {
            checkList.add(cursor.getString(1));
        }

    }


    @Override
    public int getItemCount() {
        return apps.size();
    }



    class AllAppsHolder extends RecyclerView.ViewHolder {
        TextView fastChargeAllAppName_Tv;
        ImageView fastChargeAllAppImage_Iv,fastChargeAllApp_iv;

        public AllAppsHolder(@NonNull View itemView) {
            super( itemView );
            fastChargeAllAppName_Tv = itemView.findViewById( R.id.fastChargeAllAppName_Tv );
            fastChargeAllAppImage_Iv = itemView.findViewById( R.id.fastChargeAllAppImage_Iv );
            fastChargeAllApp_iv = itemView.findViewById( R.id.fastChargeAllApp_iv );

        }
    }
}
