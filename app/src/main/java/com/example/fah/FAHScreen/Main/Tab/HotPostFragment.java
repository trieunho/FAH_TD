package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create an instance of this fragment.
 */
public class HotPostFragment extends Fragment {
    protected View view;
    protected ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_hot_post, container, false);

        GetControl();

        return view;
    }

    private void GetControl(){
        GridViewControl();
    }

    private void GridViewControl(){
        List<Post> listPost = getListData();
        listView = view.findViewById(R.id.listView);
        listView.setAdapter(new SearchAdapter(getContext(), listPost));
    }

    private  List<Post> getListData() {
        List<com.example.fah.FAHModel.Models.Post> list = new ArrayList<>();
//        Post vietnam = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
//        Post usa = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
//        Post russia = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
//
//        list.add(vietnam);
//        list.add(usa);
//        list.add(russia);

        return list;
    }
}
