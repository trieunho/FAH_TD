package com.example.fah.FAHScreen.Manage;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

public class ManageCategoryActivity extends AppCompatActivity {
    Toolbar toolbar;

    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);

        toolbar = findViewById(R.id.toolbarCategory);
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setNavigationIcon(ic_chevron_left_black_24dp);
        toolbar.setTitle("Quản lý danh mục bài viết");
        toolbar.setTitleMargin(2, 0, 0, 2);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        String[] items = {"Apple", "Banana", "Clementine"};
        itemList = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_category_item, R.id.txtNameCategory, itemList);
        ListView listV = (ListView) findViewById(R.id.categoryList);
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(itemList.get(position), position);
//                showAlertDialog(itemList.get(position), position);
            }
        });


        editText = (EditText) findViewById(R.id.txtInput);
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // clear edittext
                editText.setText("");
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home: {
                startActivity(new Intent(ManageCategoryActivity.this, MainActivity.class));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);

            }
        }

    }

    public void showInputBox(String oldItem, final int index) {
        final Dialog dialog = new Dialog(ManageCategoryActivity.this);
        dialog.setTitle("Chỉnh sửa tên danh mục");
        dialog.setContentView(R.layout.update_category_item);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Mời bạn nhập vào dưới đây!");
        txtMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText = (EditText) dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);
        Button btnDongY = (Button) dialog.findViewById(R.id.btnDongY);
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.set(index, editText.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(ManageCategoryActivity.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showAlertDialog(String oldItem, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chỉnh sửa tên danh mục");
        builder.setMessage("Mời bạn nhập vào dưới đây!");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itemList.set(index, editText.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(ManageCategoryActivity.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}