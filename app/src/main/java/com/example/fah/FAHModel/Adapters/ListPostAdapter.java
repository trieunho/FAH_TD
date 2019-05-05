package com.example.fah.FAHModel.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.*;

import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHScreen.Post.IOnButtonCLick;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class ListPostAdapter extends BaseAdapter {

    private Context context;
    private List<Post> listData;
    private LayoutInflater layoutInflater;
    private IOnButtonCLick iOnButtonCLick;
    Post current;
    Account creator;

    public void registerBtnClick(IOnButtonCLick iOnButtonCLick) {
        this.iOnButtonCLick = iOnButtonCLick;
    }

    public void unRegisterBtnClick() {
        this.iOnButtonCLick = null;
    }

    public void setData(List<Post> data) {
        this.listData = data;
    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewPost holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_post, null);
            holder = new ViewPost();

            holder.titlePost =  convertView.findViewById(R.id.txtTitlePost);
            holder.account = convertView.findViewById(R.id.txtAccount);
            holder.companyName = convertView.findViewById(R.id.txtCompanyName);
            holder.createDate = convertView.findViewById(R.id.txtCreateDate);
            holder.btnApprove = convertView.findViewById(R.id.btnApprove);
            holder.btnDel = convertView.findViewById(R.id.btnDel);
            convertView.setTag(holder);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnButtonCLick.onItemClick(position, creator.getAccountName(), current.getKey());
                }
            });
        } else {
            holder = (ViewPost) convertView.getTag();
        }

        current = this.listData.get(position);
        holder.titlePost.setText(current.getTitlePost());
        holder.companyName.setText("Công ty: " + current.getCompanyName());
        holder.createDate.setText("Ngày tạo: " + DateFormat.format("dd/MM/yyyy", current.getCreateDate()));
        holder.btnApprove.setText(current.getStatus() == 0 ? "Duyệt" : "Đã duyệt");
        holder.btnApprove.setEnabled(current.getStatus() == 0);
        FirebaseDatabase.getInstance().getReference().child("Account")
                .child(current.getKeyAccount()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                creator = dataSnapshot.getValue(Account.class);
                holder.account.setText("Người đăng: " + creator.getAccountName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnButtonCLick.onBtnApproveClick(position);
            }
        });

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnButtonCLick.onBtnDelClick(position);
            }
        });

        return convertView;
    }

    static class ViewPost {
        TextView titlePost;
        TextView account;
        TextView companyName;
        TextView createDate;
        Button btnApprove;
        Button btnDel;
    }
}
