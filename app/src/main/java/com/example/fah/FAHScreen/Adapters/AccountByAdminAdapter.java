package com.example.fah.FAHScreen.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fah.FAHScreen.Models.Account;
import com.example.fah.R;

import java.util.ArrayList;

public class AccountByAdminAdapter extends ArrayAdapter<Account> {

    Context context;
    int layout;
    ArrayList<Account> accountList;

    public AccountByAdminAdapter(Context context, int layout, ArrayList<Account> accountList) {
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
        ImageView ivAvatar = convertView.findViewById(R.id.ivAvatar);
        TextView tvAccountName = convertView.findViewById(R.id.tvAccountName);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);
        Button btnBlock = convertView.findViewById(R.id.btnBlock);

        Account account = this.accountList.get(position);
        String stt = String.valueOf(position + 1);

        tvStt.setText(stt);
        ivAvatar.setImageResource(account.getSex() == 1 ? R.drawable.men : R.drawable.women );
        tvAccountName.setText(account.getAccountName());
        tvEmail.setText(account.getEmail());

        // Set background foreach records follow position
        if (position % 2 == 0) {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSolidWhiteSmoke));
        } else {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSolidLavender));
        }

        // Set background and text for button Block or Unlock follow Status Block
        // 0 : being unlock ==> set text is Block
        // != 0: being block ==> set text is Unlock
        if (account.getStatusBlock() == 0) {
            btnBlock.setBackgroundColor(context.getResources().getColor(R.color.colorRoyalBlue));
            btnBlock.setText("Block");
        } else {
            btnBlock.setBackgroundColor(context.getResources().getColor(R.color.colorCrimson));
            btnBlock.setText("Unlock");
        }

        return convertView;
    }
}
