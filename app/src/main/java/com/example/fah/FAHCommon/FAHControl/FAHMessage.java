package com.example.fah.FAHCommon.FAHControl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

    public static void AlertDialogMessage(Activity activity, String title, String content, String nameButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setCancelable(false);
        builder.setPositiveButton(nameButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
