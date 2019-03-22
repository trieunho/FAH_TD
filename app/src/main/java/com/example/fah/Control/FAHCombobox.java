package com.example.fah.Control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

public class FAHCombobox {
    private int itemChoose;

    public FAHCombobox(){
        itemChoose = -1;
    }

    public void ShowItemChoose(Activity activity,final EditText editText, final String[] itemSouce){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setSingleChoiceItems(itemSouce, itemChoose, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemChoose = which;
                editText.setText(itemChoose == -1 ? editText.getText() : itemSouce[itemChoose]);
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public int GetItemChoose(){
        return itemChoose;
    }
}
