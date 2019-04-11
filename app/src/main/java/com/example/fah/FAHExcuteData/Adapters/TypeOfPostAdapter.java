package com.example.fah.FAHExcuteData.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fah.FAHExcuteData.Models.TypeOfPost;
import com.example.fah.R;

import java.util.ArrayList;

public class TypeOfPostAdapter extends ArrayAdapter<TypeOfPost> {

    private Context context;
    private int layout;
    private ArrayList<TypeOfPost> typeOfPostList;

    TextView txtTypeName;
    TextView txtTypeCoin;
    TextView txtTypeTime;

    public TypeOfPostAdapter(Context context, int layout, ArrayList<TypeOfPost> typeOfPostList) {
        super(context, layout, typeOfPostList);
        this.context = context;
        this.layout = layout;
        this.typeOfPostList = typeOfPostList;
    }

    @Override
    public int getCount() {
        return typeOfPostList != null ?  typeOfPostList.size() : 0;
    }

    @Override
    public int getPosition(@Nullable TypeOfPost item) {
        return super.getPosition(item);
    }

    @Nullable
    @Override
    public TypeOfPost getItem(int position) {
        return typeOfPostList.get(position);
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        txtTypeName = convertView.findViewById(R.id.txtTypeName);
        txtTypeCoin = convertView.findViewById(R.id.txtTypeCoin);
        txtTypeTime = convertView.findViewById(R.id.txtTypeTime);

        TypeOfPost typeOfPost = typeOfPostList.get(position);

        txtTypeName.setText(typeOfPost.getTypeName());
        txtTypeCoin.setText(typeOfPost.getTypeCoin());
        txtTypeTime.setText(typeOfPost.getTypeTime());

        return convertView;
    }
}
