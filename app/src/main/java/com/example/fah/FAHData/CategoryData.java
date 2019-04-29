package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryData {
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    public static ArrayList<Category> categoryList = new ArrayList<>();


    public CategoryData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CATEGORY_OF_POST");
    }

    public void getListCategory() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Category> listCategory = (ArrayList<Category>) FAHQuery.GetDataObject(dataSnapshot, new Category());
                for (Category cat : listCategory) {
                    categoryList.add(cat);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
