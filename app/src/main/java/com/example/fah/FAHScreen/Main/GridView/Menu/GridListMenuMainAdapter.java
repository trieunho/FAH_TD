package com.example.fah.FAHScreen.Main.GridView.Menu;

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

public class GridListMenuMainAdapter extends BaseAdapter {

    private List<Menu> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GridListMenuMainAdapter(Context context, List<Menu> listData) {
        this.context = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
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
            convertView = layoutInflater.inflate(R.layout.grid_item_main_menu, null);
            holder = new ViewHolder();
            holder.tvNameFunc = convertView.findViewById(R.id.tvNameFunc);
            holder.ivFunction = convertView.findViewById(R.id.ivFunction);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Menu objMenu = this.listData.get(position);
        holder.tvNameFunc.setText(objMenu.getName());

        int imageId = this.getMipmapResIdByName(objMenu.getImage());

        holder.ivFunction.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        TextView tvNameFunc;
        ImageView ivFunction;
    }

}
