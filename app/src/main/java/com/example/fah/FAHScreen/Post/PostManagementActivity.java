package com.example.fah.FAHScreen.Post;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

public class PostManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_management_activity);

        List<Post> image_details = getListData();
        final ListView listView = findViewById(R.id.lstPost);
        listView.setAdapter(new com.example.fah.FAHModel.Adapters.ListPostAdapter(this, image_details));
    }

    private List<Post> getListData() {
        List<Post> list = new ArrayList<>();
//        Post vietnam = new Post("Tuyển thư ký chân dài", "CanhDV1", "F-Complex");
//        Post usa = new Post("Tuyển thư ký chân vừa", "CanhDV1", "F-Complex");
//        Post russia = new Post("Tuyển thư ký chân ngắn", "CanhDV1", "F-Complex");

//        list.add(vietnam);
//        list.add(usa);
//        list.add(russia);

        return list;
    }
}
