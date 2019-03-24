package com.example.fah.FAHScreen.Main.GridView.Post;

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

public class GridListPostMainAdapter  extends BaseAdapter {

    private List<Post> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GridListPostMainAdapter(Context context,  List<Post> listData) {
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
            convertView = layoutInflater.inflate(R.layout.grid_item_main_list_post, null);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.ivPost = convertView.findViewById(R.id.ivPost);
            holder.tvContent = convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Post objPost = this.listData.get(position);
        holder.tvTitle.setText(objPost.getTitle());
        holder.tvContent.setText(objPost.getContent());

        int imageId = this.getMipmapResIdByName(objPost.getImage());

        holder.ivPost.setImageResource(imageId);

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
        TextView tvTitle;
        ImageView ivPost;
        TextView tvContent;
    }

}
