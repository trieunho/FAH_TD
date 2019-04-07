package com.example.fah.FAHScreen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fah.FAHScreen.Models.Category;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Context context, int layout, ArrayList<Category> categoryList) {
        super(context, layout, categoryList);
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    private Context context;
    private int layout;
    private List<Category> categoryList;
    TextView txtCategoryName;
    TextView txtCategoryID;


    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        // mapping view
        txtCategoryName = convertView.findViewById(R.id.txtCategoryName);
        txtCategoryID = convertView.findViewById(R.id.txtCategoryID);

        // set values view
        Category category = categoryList.get(position);
        txtCategoryName.setText(category.getCategoryName());
        txtCategoryID.setText(category.getCategoryID());

        return convertView;
    }
}
