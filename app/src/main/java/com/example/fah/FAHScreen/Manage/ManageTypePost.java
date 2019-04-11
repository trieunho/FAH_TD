package com.example.fah.FAHScreen.Manage;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHExcuteData.Adapters.TypeOfPostAdapter;
import com.example.fah.FAHExcuteData.Models.TypeOfPost;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

public class ManageTypePost extends AppCompatActivity {


    DatabaseReference myRef;

    Toolbar toolbar;
    TextView txtContentNote;
    ListView lvTypePost;
    ArrayList<TypeOfPost> typeOfPostList;

    TypeOfPostAdapter typeOfPostAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_type_post);
        addControl();
   }

    private void addControl() {
        myRef = FirebaseDatabase.getInstance().getReference("TYPE_OF_POST");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ic_chevron_left_black_24dp);
        toolbar.setTitle("JOB MANAGEMENT");
        toolbar.setTitleMargin(2, 0, 0, 2);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        lvTypePost = findViewById(R.id.lvTypePost);
        txtContentNote = findViewById(R.id.txtContentNote);
        txtContentNote.setText("\t Loại 1: Hiển thị ở mục bài viết hot của trang chủ." +
                "\n\t Loại 2: Uu tiên xuất hiện truớc khi tìm kiếm." +
                "\n\t Loại 3: Bình thường");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                typeOfPostList = (ArrayList<TypeOfPost>) FAHQuery.GetDataObject(dataSnapshot, new TypeOfPost());
                setTypeOfPostAdapter(typeOfPostList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ManageTypePost.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        lvTypePost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(typeOfPostList.get(position), position);
            }
        });
    }

    private void showInputBox(final TypeOfPost typeOfPost, int position) {
        final Dialog dialog = new Dialog(ManageTypePost.this);
        dialog.setTitle("EDIT TYPE OF POST");
        dialog.setContentView(R.layout.update_type_post_item);
        final TextView txtMessageCoin = dialog.findViewById(R.id.txtMessageCoin);
        final TextView txtMessageTime = dialog.findViewById(R.id.txtMessageTime);
        txtMessageCoin.setText("Please enter Coin:");
        txtMessageTime.setText("Please enter Time:");
        txtMessageCoin.setTextColor(Color.parseColor("#ff2222"));
        txtMessageTime.setTextColor(Color.parseColor("#ff2222"));

        final EditText txtInputCoin = dialog.findViewById(R.id.txtInputCoin);
        final EditText txtInputTime = dialog.findViewById(R.id.txtInputTime);

        txtInputCoin.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_VARIATION_NORMAL);
        txtInputTime.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_VARIATION_NORMAL);
        txtInputCoin.setText(typeOfPost.getTypeCoin());
        txtInputTime.setText(typeOfPost.getTypeTime());
        Button btnDongY = dialog.findViewById(R.id.btnDongY);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(typeOfPost.getTypeID()).setValue(
                        new TypeOfPost(typeOfPost.getTypeID(),
                                typeOfPost.getTypeName(),
                                txtInputCoin.getText().toString(),
                                txtInputTime.getText().toString())
                );
                Toast.makeText(ManageTypePost.this, "Edit Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getListTypeOfPost() {

    }

    private void setTypeOfPostAdapter(@NonNull ArrayList<TypeOfPost> typeOfPostList) {
        typeOfPostAdapter = new TypeOfPostAdapter(
                ManageTypePost.this,
                R.layout.grid_type_of_post_item,
                typeOfPostList);

        lvTypePost.setAdapter(typeOfPostAdapter);
    }



}
