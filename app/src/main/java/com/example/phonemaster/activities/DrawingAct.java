package com.example.phonemaster.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phonemaster.utils.Drawing;


public class DrawingAct extends AppCompatActivity {
    Drawing drawingClass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        drawingClass = new Drawing( this,null );
        setContentView( drawingClass );

    }
}
