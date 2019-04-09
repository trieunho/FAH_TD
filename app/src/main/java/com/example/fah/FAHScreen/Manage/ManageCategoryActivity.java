package com.example.fah.FAHScreen.Manage;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHExcuteData.Adapters.CategoryAdapter;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.FAHExcuteData.Models.Category;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

public class ManageCategoryActivity extends AppCompatActivity {
    Toolbar toolbar;

    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayAdapter<String> adapter;
    ArrayList<Category> categoryList;
    CategoryAdapter categoryAdapter;
    ListView listV;
    Button btnAdd;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);
        addControl();
        addEvent();
    }

    private void addEvent() {

    }

    private void addControl() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CATEGORY_OF_POST");
        toolbar = findViewById(R.id.toolbarCategory);
        listV = findViewById(R.id.categoryList);
        btnAdd = findViewById(R.id.btnAdd);

//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

//        myRef.chi

        toolbar.setNavigationIcon(ic_chevron_left_black_24dp);
        toolbar.setTitle("JOB MANAGEMENT");
        toolbar.setTitleMargin(2, 0, 0, 2);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        categoryList = new ArrayList<>();

        // Call private method to set data for List View Category
        getListCategory();
//        setCategoryAdapter(categoryList);

        editText = (EditText) findViewById(R.id.txtInput);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = editText.getText().toString().trim();
                if (categoryName != null && !"".equals(categoryName)) {
                    int categoryID = 1;
                    Category newCategory;
                    if (categoryList != null && categoryList.size() > 0) {
                        categoryID =  Integer.parseInt(categoryList.get(categoryList.size() - 1).getCategoryID()) +1 ;
                        newCategory = new Category(String.valueOf(categoryID), categoryName);
                    } else {
                        newCategory = new Category(String.valueOf(categoryID), categoryName);
                    }

                    myRef.child(String.valueOf(categoryID)).setValue(newCategory);

                    // add new item to arraylist
                    categoryList.add(newCategory);
                    // clear edittext
                    editText.setText("");
                    // notify listview of data changed
                    setCategoryAdapter(categoryList);
                }
            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(categoryList.get(position), position);
//                showAlertDialog(categoryList.get(position), position);
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

    public void showInputBox(final Category category, final int index) {
        final Dialog dialog = new Dialog(ManageCategoryActivity.this);
        dialog.setTitle("EDIT CATEGORY NAME");
        dialog.setContentView(R.layout.update_category_item);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Please enter category name:");
        txtMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText = (EditText) dialog.findViewById(R.id.txtinput);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*
                 * The loop is in reverse for a purpose,
                 * each replace or delete call on the Editable will cause
                 * the afterTextChanged method to be called again.
                 * Hence the return statement after the first removal.
                 */
                for(int i = s.length()-1; i >= 0; i--){
                    if(s.charAt(i) == '\n'){
                        s.delete(i, i + 1);
                        return;
                    }
                }
            }
        });
        editText.setText(category.getCategoryName());
        Button btnDongY = (Button) dialog.findViewById(R.id.btnDongY);
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(category.getCategoryID()).setValue(
                        new Category(category.getCategoryID(), editText.getText().toString())
                );
                Toast.makeText(ManageCategoryActivity.this, "Edit Success", Toast.LENGTH_SHORT).show();
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

    private void setCategoryAdapter(ArrayList<Category> categoryList) {
        categoryAdapter = new CategoryAdapter(
                ManageCategoryActivity.this,
                R.layout.list_category_item,
                categoryList);
        listV.setAdapter(categoryAdapter);
    }

    private void getListCategory() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categoryList = new ArrayList<>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Category category = snapshot.getValue(Category.class);
                        categoryList.add(category);
                    }

                    setCategoryAdapter(categoryList);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ManageCategoryActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}