package com.cleaner.booster.phone.repairer.app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.cleaner.booster.phone.repairer.app.BuildConfig;
import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.services.SmartChargeService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_tool, R.id.navigation_me)
                .build();
          navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SharedPreferences preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        if (preferences.getBoolean("IS_SMART_CHARGE_ENABLED", false)) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(MainActivity.this, SmartChargeService.class));
            } else {
                startService(new Intent(MainActivity.this, SmartChargeService.class));
            }
        }
   }

    @Override
    public void onBackPressed()
    {

        if (navController.getCurrentDestination().getId()==R.id.navigation_home){
            exitt();
        }else {
            navController.popBackStack();
        }
    }

    public void exitt() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            @SuppressLint("InflateParams") final View dialogView = layoutInflater.inflate(R.layout.dialog_exit, null);
            Button yes = dialogView.findViewById(R.id.yes);
            Button no = dialogView.findViewById(R.id.no);
            Button rate = dialogView.findViewById(R.id.rate);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            yes.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {
                    moveTaskToBack(true);
                    alertDialog.cancel();
                    finishAffinity();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rateUs();
                }
            });
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public void rateUs() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +getPackageName())));
    }

}