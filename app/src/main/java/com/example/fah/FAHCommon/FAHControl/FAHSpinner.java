package com.example.fah.FAHCommon.FAHControl;


import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class FAHSpinner {
    // Set item Souce combobox, pick TrieuHT
    public static void setItemSource(Activity activity, Spinner spinner, List<String> listData){
        ArrayAdapter<String> adapter = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, listData);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
    }
}
