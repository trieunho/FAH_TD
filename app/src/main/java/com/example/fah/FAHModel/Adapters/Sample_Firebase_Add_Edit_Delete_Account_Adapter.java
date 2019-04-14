package com.example.fah.FAHModel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fah.FAHModel.Models.SampleAccount;
import com.example.fah.R;

import java.util.ArrayList;

public class Sample_Firebase_Add_Edit_Delete_Account_Adapter extends ArrayAdapter<SampleAccount> {
    Context context; int resource; ArrayList<SampleAccount> list;
    public Sample_Firebase_Add_Edit_Delete_Account_Adapter(Context context, int resource, ArrayList<SampleAccount> list) {
        super(context, resource, list);
        this.context=context;
        this.resource=resource;
        this.list=list;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Context context_final = this.context;
        convertView = layoutInflater.inflate(this.resource, null);
       TextView txtNameAC=convertView.findViewById(R.id.emailAccount);
        ImageView imageupload=convertView.findViewById(R.id.imageUppload);
        txtNameAC.setText(list.get(position).getEmail());
        imageupload.setImageResource(list.get(position).getResourceImg());
       // final SampleAccount sampleAccount = this.list.get(position);
        return convertView;
    }
}
