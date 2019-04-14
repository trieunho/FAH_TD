package com.example.fah.FAHScreen.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailSearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout divSearch;
    ListView lstSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_search_activity);

        addControls();

        List<Post> data = getListData();
        final ListView listView = findViewById(R.id.lstSearch);
        listView.setAdapter(new SearchAdapter(DetailSearchActivity.this, data));
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.fah_icon_notifications);
        toolbar.setTitle("Tìm kiếm bài viết");
        setSupportActionBar(toolbar);

        divSearch = findViewById(R.id.divSearch);
        lstSearch = findViewById(R.id.lstSearch);
        btnSearch = findViewById(R.id.btnSearch);

        lstSearch.setVisibility(View.GONE);
    }

    public void onClickSearch(View v) {

    }

    private List<Post> getListData() {
        List<Post> list = new ArrayList<>();
        Post vietnam = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
        Post usa = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
        Post russia = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));

        list.add(vietnam);
        list.add(usa);
        list.add(russia);

        return list;
    }
}
