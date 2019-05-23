package com.example.fah.FAHModel.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountByPostAdapter extends ArrayAdapter<String> {

    Context context;
    int layout;
    ArrayList<String> accountList;

    DatabaseReference myRef;
    FirebaseDatabase database;

    public AccountByPostAdapter(Context context, int layout, ArrayList<String> accountList) {
        super(context, layout, accountList);
        this.context = context;
        this.layout = layout;
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList == null ? 0 : accountList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Account");
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(this.layout, null);

        LinearLayout linearLayout = convertView.findViewById(R.id.llAccount);
        final TextView tvStt = convertView.findViewById(R.id.tvStt);
        ImageView ivAvatar = convertView.findViewById(R.id.ivAvatar);
        final TextView tvAccountName = convertView.findViewById(R.id.tvAccountName);
        final TextView tvEmail = convertView.findViewById(R.id.tvEmail);

        String stt = String.valueOf(position + 1);

        tvStt.setText(stt);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Account> accountLst = (ArrayList<Account>) FAHQuery.GetDataObject(dataSnapshot, new Account());
                for (Account acc : accountLst) {
                    if (acc != null && acc.getKey().equals(accountList.get(position))) {
                        tvAccountName.setText(acc.getAccountName());
                        tvEmail.setText(acc.getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Set background foreach records follow position
        if (position % 2 == 0) {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSolidWhiteSmoke));
        } else {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSolidLavender));
        }
        return convertView;
    }
}
