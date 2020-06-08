package com.example.phonemaster.utils;

import android.content.Context;
import android.view.Gravity;

import com.gmail.samehadar.iosdialog.IOSDialog;

public class LoadingDialog {

    private IOSDialog iosDialog;

    public boolean show() {
        iosDialog.show();
        return true;
    }

    public boolean dismiss() {
        iosDialog.dismiss();
        return false;
    }

    public LoadingDialog(Context context) {
        iosDialog = new IOSDialog.Builder(context)
                .setCancelable(false)
                .setSpinnerClockwise(false)
                .setMessageContentGravity(Gravity.END)
                .setMessageContent("Loading")
                .build();
    }
}