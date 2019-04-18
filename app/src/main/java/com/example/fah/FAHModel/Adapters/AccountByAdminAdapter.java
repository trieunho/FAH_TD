package com.example.fah.FAHModel.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHModel.Models.Account;
import com.example.fah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccountByAdminAdapter extends ArrayAdapter <Account> {

    Context context;
    int layout;
    ArrayList <Account> accountList;

    public AccountByAdminAdapter(Context context, int layout, ArrayList <Account> accountList) {
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
        final Button btnBlock = convertView.findViewById(R.id.btnBlock);

        final Account account = this.accountList.get(position);

        String stt = String.valueOf(position + 1);

        tvStt.setText(stt);
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

        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // how activity ???
                // int position = (int) v.getTag();

                showAlertDialog(account);
            }
        });

        return convertView;
    }

    /**
     * Extends showAlertDialog
     */
    public void showAlertDialog(final Account account) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Chú ý !");
        builder.setMessage("Bạn có muốn thay đổi quyền của " + account.getAccountName() + " ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("TestHongCT");

                if (account.getStatusBlock() == 0) {
                    myRef.child("nani0").child("statusBlock").setValue(1);
                } else {
                    myRef.child("nani0").child("statusBlock").setValue(0);
                }


                Toast.makeText(context, "Thay đổi quyền thành công !", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
