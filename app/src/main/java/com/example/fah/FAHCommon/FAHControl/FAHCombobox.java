package com.example.fah.FAHCommon.FAHControl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.fah.FAHCommon.FAHMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class FAHCombobox {
    private Activity activity;
    private EditText editText;
    private int itemChoose;
    private String[] itemSouce;

    public static int VALUEDEFAULT = -1;

    public FAHCombobox(){
        itemChoose = VALUEDEFAULT;
    }

    public FAHCombobox(Activity activity, EditText editText, String[] itemSouce, int itemChoose){
        this.activity = activity;
        this.editText = editText;
        this.itemChoose = itemChoose;
        this.itemSouce = itemSouce;

        setItemChoose(itemChoose);

        this.editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowItemChoose();
            }
        });

        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ShowItemChoose();
            }
        });
    }

    public void OnItemChange(final String callable){
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                new Callable<Void>() {
                    public Void call() {
                        callable.toString();
                        return null;
                    }
                };
            }
        });
    }

    public int getItemChoose() {
        return itemChoose;
    }

    public void setItemChoose(int itemChoose) {
        this.itemChoose = itemChoose;
        this.editText.setText(itemChoose == VALUEDEFAULT ? null : itemSouce[itemChoose]);
    }

    public void setItemSouce(String[] itemSouce) {
        this.itemSouce = itemSouce;
    }

    private void ShowItemChoose(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setSingleChoiceItems(this.itemSouce, this.itemChoose, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(itemChoose != which){
                    setItemChoose(which);
                }
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
