package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.fah.FAHScreen.Main.GridView.Post.GridListPostMainAdapter;
import com.example.fah.FAHScreen.Main.GridView.Post.Post;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create an instance of this fragment.
 */
public class HotPostFragment extends Fragment {
    protected View view;
    protected GridView gvPost;

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
        gvPost = view.findViewById(R.id.gvPost);
        gvPost.setAdapter(new GridListPostMainAdapter(getActivity(), listPost));

        // Khi người dùng click vào các GridItem
        gvPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gvPost.getItemAtPosition(position);
                Post objectGrid = (Post) o;
                Toast.makeText(getActivity(), "Selected :"
                        + " " + objectGrid, Toast.LENGTH_LONG).show();
            }
        });
    }

    private  List<Post> getListData() {
        List<Post> list = new ArrayList<Post>();
        list.add(new Post("Vietnam1", "ic_arrow_down", "111111"));
        list.add(new Post("Vietnam2", "ic_arrow_down", "111111"));
        list.add(new Post("Vietnam3", "ic_arrow_down", "111111"));
        list.add(new Post("Vietnam4", "ic_arrow_down", "111111"));
        list.add(new Post("Vietnam5", "ic_arrow_down", "111111"));
        list.add(new Post("Vietnam6", "ic_arrow_down", "111111"));

        return list;
    }
}
