package com.example.fah.FAHScreen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fah.FAHScreen.Models.Category;
import com.example.fah.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    public CategoryAdapter(Context context, int layout, List<Category> categoryList) {
        this.context = context;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    private Context context;
    private int layout;
    private List<Category> categoryList;

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
        TextView txtNameCategory = convertView.findViewById(R.id.txtNameCategory);

        // set values view
        Category category = categoryList.get(position);
        txtNameCategory.setText(category.getNameCategory());

        return convertView;
    }
}
