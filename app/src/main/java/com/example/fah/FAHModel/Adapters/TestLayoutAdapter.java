package com.example.fah.FAHModel.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fah.FAHModel.Models.TestLayout;
import com.example.fah.R;

import java.util.ArrayList;

public class TestLayoutAdapter  extends ArrayAdapter<TestLayout> {
    public  Context context;
    public int layout;
    public ArrayList<TestLayout> LayoutList;


    public TestLayoutAdapter(Context context, int layout, ArrayList<TestLayout> LayoutList) {
        super(context, layout, LayoutList);
        this.context = context;
        this.layout = layout;
        this.LayoutList = LayoutList;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Context context_final = this.context;
        convertView = layoutInflater.inflate(this.layout, null);
        TextView txtNameAC=convertView.findViewById(R.id.NameAc);
        ImageView imgStart=convertView.findViewById(R.id.startAc);
        txtNameAC.setText(LayoutList.get(position).getNameActivity());

        final TestLayout testLayout = this.LayoutList.get(position);
        txtNameAC.setText(testLayout.getNameActivity());
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(testLayout.getTypeActivity()!=0 && testLayout.getTypeActivity() == 2){
                 testLayout.getEvent().setEvent();
                }else{
                    context_final.startActivity(new Intent(testLayout.getPackageContext(),testLayout.getCls()));
                }

//                ((Activity) context_final).overridePendingTransition(R.anim.translate_right_side,R.anim.translate_left_side);
            }
        });

        txtNameAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(testLayout.getTypeActivity()!=0 && testLayout.getTypeActivity() == 2){
                    testLayout.getEvent().setEvent();
                }else{
                    context_final.startActivity(new Intent(testLayout.getPackageContext(),testLayout.getCls()));
                }
//                ((Activity) context_final).overridePendingTransition(R.anim.translate_right_side,R.anim.translate_left_side);
            }
        });
        return convertView;
    }

}
