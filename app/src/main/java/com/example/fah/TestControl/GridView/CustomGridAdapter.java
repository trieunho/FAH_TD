package com.example.fah.TestControl.GridView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fah.R;

import java.util.List;

public class CustomGridAdapter  extends BaseAdapter {

    private List<ObjectGrid> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridAdapter(Context aContext,  List<ObjectGrid> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null);
            holder = new ViewHolder();
            holder.flagView = convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = convertView.findViewById(R.id.textView_countryName);
            holder.populationView = convertView.findViewById(R.id.textView_population);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ObjectGrid objectGrid = this.listData.get(position);
        holder.countryNameView.setText(objectGrid.getCountryName());
        holder.populationView.setText("" + objectGrid.getPopulation());

        int imageId = this.getMipmapResIdByName(objectGrid.getFlagName());

        holder.flagView.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView flagView;
        TextView countryNameView;
        TextView populationView;
    }

}
