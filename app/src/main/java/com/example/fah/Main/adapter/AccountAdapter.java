package com.example.fah.Main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fah.Main.model.Account;
import com.example.fah.R;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account> {

    Context context;
    int layout;
    ArrayList<Account> accountList;

    public AccountAdapter(Context context, int layout, ArrayList<Account> accountList) {
        super(context, layout, accountList);
        this.context = context;
        this.layout = layout;
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(this.layout, null);

        LinearLayout linearLayout = convertView.findViewById(R.id.llAccount);
        TextView tvStt = convertView.findViewById(R.id.tvStt);
        ImageView imageView = convertView.findViewById(R.id.ivTest);
        TextView textView = convertView.findViewById(R.id.tvTest);

        String stt = position + 1 + "";

        tvStt.setText(stt);
        if (position % 2 == 0) {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSolidWhiteSmoke));
        } else {

            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSolidGray));
        }
        imageView.setImageResource(this.accountList.get(position).getSex() == 1 ? R.drawable.men : R.drawable.women );
        textView.setText(this.accountList.get(position).getAccountName());


        return convertView;
    }
}
