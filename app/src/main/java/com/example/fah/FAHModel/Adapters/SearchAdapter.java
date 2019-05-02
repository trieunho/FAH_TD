package com.example.fah.FAHModel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;

import java.util.List;

public class SearchAdapter extends BaseAdapter {
    private List<Post> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public SearchAdapter(Context aContext,  List<Post> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_search, null);
            holder = new ViewPost();
            holder.titlePost =  convertView.findViewById(R.id.txtTitlePost);
            holder.companyName = convertView.findViewById(R.id.txtCompanyName);
            holder.address =  convertView.findViewById(R.id.txtAddress);
            holder.thoiGian = convertView.findViewById(R.id.txtThoiGian);
            holder.salary = convertView.findViewById(R.id.txtLuong);
            holder.deadLine = convertView.findViewById(R.id.txtDeadline);
            convertView.setTag(holder);
        } else {
            holder = (ViewPost) convertView.getTag();
        }

        Post post = this.listData.get(position);
        String salary = post.getTypeOfSalary() != null ? post.getTypeOfSalary().equals("Thỏa thuận") ? post.getTypeOfSalary()
                : post.getTypeOfSalary().equals("Cố định") ? post.getSalary_from() : post.getSalary_from() + " ~ " + post.getSalary_to() : "";
        holder.titlePost.setText(post.getTitlePost());
        holder.companyName.setText(post.getCompanyName());
        holder.address.setText(post.getAddress());
        holder.thoiGian.setText("Từ " + post.getDtFrom() + " giờ Đến " + post.getDtTo() + " Giờ");
        holder.salary.setText("Lương: " + salary);
        holder.deadLine.setText("Hạn nhận: " + post.getDeadLine());

        return convertView;
    }

    static class ViewPost {
        TextView titlePost;
        TextView companyName;
        TextView address;
        TextView salary;
        TextView thoiGian;
        TextView deadLine;
    }
}
