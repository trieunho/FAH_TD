package com.example.fah.FAHScreen.Main.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.fah.FAHScreen.Main.GridView.Menu.GridListMenuMainAdapter;
import com.example.fah.FAHScreen.Main.GridView.Menu.Menu;
import com.example.fah.FAHScreen.User.ProfileActivity;
import com.example.fah.R;
import com.example.fah.TestControl.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        GetControl();

        return view;
    }

    private void GetControl(){
        ImageControl();
        GridViewControl();
    }

    private void ImageControl(){
        ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });

    }

    private void GridViewControl(){
        List<Menu> listPost = getListData();
        GridView gvMenu = view.findViewById(R.id.gvMenu);
        gvMenu.setAdapter(new GridListMenuMainAdapter(getActivity(), listPost));

        // Khi người dùng click vào các GridItem
        gvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Menu objectGrid = (Menu) gvMenu.getItemAtPosition(position);
                startActivity(new Intent(getContext(), TestActivity.class));
            }
        });
    }

    private  List<Menu> getListData() {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("Tìm kiếm công việc", "ic_launcher_search_job"));
        list.add(new Menu("Công việc của tôi", "ic_launcher_job"));

        list.add(new Menu("Đăng bài viết mới", "ic_launcher_add_job"));
        list.add(new Menu("Quản lý bài đăng", "ic_launcher_post"));
        list.add(new Menu("Tìm kiếm ứng viên", "ic_launcher_search_people"));
        list.add(new Menu("Danh sách ứng viên", "ic_launcher_list_people"));

        list.add(new Menu("Quản lý bài đăng", "ic_launcher_manage_post"));
        list.add(new Menu("Quản lý người dùng", "ic_launcher_manage_account"));
        list.add(new Menu("Danh mục công việc", "ic_launcher_option"));

        list.add(new Menu("Đăng xuất", "ic_launcher_logout"));
        list.add(new Menu("Đăng nhập", "ic_launcher_login"));

        return list;
    }
}
