package com.example.fah.FAHScreen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.example.fah.FAHScreen.Models.Post;
import com.example.fah.R;
import java.util.List;

public class ListPostAdapter extends BaseAdapter {

    private List<Post> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListPostAdapter(Context aContext,  List<Post> listData) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewPost holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_post, null);
            holder = new ViewPost();
            holder.titlePost =  convertView.findViewById(R.id.txtTitlePost);
            holder.account = convertView.findViewById(R.id.txtAccount);
            holder.companyName = convertView.findViewById(R.id.txtCompanyName);
            convertView.setTag(holder);
        } else {
            holder = (ViewPost) convertView.getTag();
        }

        Post post = this.listData.get(position);
        holder.titlePost.setText(post.getTitlePost());
        holder.account.setText("Người đăng: " + post.getAccount());
        holder.companyName.setText("Công ty: " + post.getCompanyName());

        return convertView;
    }

    static class ViewPost {
        TextView titlePost;
        TextView account;
        TextView companyName;
    }
}
