package com.example.fah.FHADefine;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class FAHMessage {
    public static void ToastMessage(Activity activity, String content){
        Toast.makeText(activity, content, Toast.LENGTH_LONG).show();
    }

    public static void SnackbarMessage(View view, String content){
        Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
