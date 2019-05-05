package com.example.fah.FAHCommon.FAHControl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.fah.FAHScreen.Post.IConfirmClick;
import com.example.fah.FAHScreen.Post.IOnButtonCLick;

public class FAHMessage {
    private static IConfirmClick iConfirmClick;

    public static void confirmBtnClick(IConfirmClick iClick) {
        iConfirmClick = iClick;
    }

    public static void unConfirmBtnClick() {
        iConfirmClick = null;
    }

    public static void ToastMessage(Activity activity, String content){
        Toast.makeText(activity, content, Toast.LENGTH_LONG).show();
    }

    public static void SnackbarMessage(View view, String content){
        Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public static void AlertDialogMessage(final Activity activity, String title, String message, String yes, String no){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                iConfirmClick.onYesClick();
            }
        });
        builder.setNegativeButton(no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
