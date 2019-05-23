package com.example.fah.FAHScreen.Main.Tab;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Models.IEvenItem;
import com.example.fah.FAHScreen.Account.ManageAccountByAdminActivity;
import com.example.fah.FAHScreen.Account.ManageAccountByPostActivity;
import com.example.fah.FAHScreen.Main.GridView.Menu.GridListMenuMainAdapter;
import com.example.fah.FAHScreen.Main.GridView.Menu.Menu;
import com.example.fah.FAHScreen.Manage.ManageCategoryActivity;
import com.example.fah.FAHScreen.Manage.ManageTypePostActivity;
import com.example.fah.FAHScreen.Other.RulesActivity;
import com.example.fah.FAHScreen.Other.SecurityActivity;
import com.example.fah.FAHScreen.Post.DetailSearchPostActivity;
import com.example.fah.FAHScreen.Post.PostManagementActivity;
import com.example.fah.FAHScreen.User.Login.LoginActivity;
import com.example.fah.FAHScreen.User.ProfileActivity;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

/**
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    protected View view;
    GridView gvMenu;
    ProgressDialog progressDoalog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Đang tải dữ liệu....");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        checkImageUser();
        GetControl();
        return view;
    }

    private void GetControl(){
        ImageControl();
        GridViewControl();
    }

    private void ImageControl(){
//        ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
//        ivAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), ProfileActivity.class));
//            }
//        });

    }

    private void GridViewControl(){
        List<Menu> listMenu = getListData();
        gvMenu = view.findViewById(R.id.gvMenu);
        gvMenu.setAdapter(new GridListMenuMainAdapter(getActivity(), listMenu));

        // Khi người dùng click vào các GridItem
        gvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Menu objectGrid = (Menu) gvMenu.getItemAtPosition(position);
                if( objectGrid.getEventClickItem() == null){
                    Toast.makeText(getContext(), "Event not found!", Toast.LENGTH_SHORT).show();
                }{
                    objectGrid.getEventClickItem().callEvent();
                }

            }
        });
    }
    private  List<Menu> getListData() {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("Avatar", "ic_launcher_search_job", "123", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        }));
        list.add(new Menu("Đăng nhập", "ic_launcher_login", "0", new IEvenItem() {
            @Override
            public void callEvent() {
                checkAndCallLogin();
            }
        }));
        list.add(new Menu("Tìm kiếm bài đăng", "ic_launcher_search_job", "12", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), DetailSearchPostActivity.class));
            }
        }));
        list.add(new Menu("Quản lý bài đăng", "ic_launcher_manage_post", "2", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), ManageAccountByPostActivity.class));
            }
        }));
        list.add(new Menu("Duyệt bài đăng", "ic_launcher_post", "3", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), PostManagementActivity.class));
            }
        }));
        list.add(new Menu("Quản lý người dùng", "ic_launcher_manage_account", "3", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), ManageAccountByAdminActivity.class));
            }
        }));
        list.add(new Menu("Quản lý danh mục", "ic_launcher_option", "3", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), ManageCategoryActivity.class));
            }
        }));
        list.add(new Menu("Quản lý loại bài viết", "ic_launcher_option", "3", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), ManageTypePostActivity.class));
            }
        }));
        list.add(new Menu("Điều khoản", "ic_launcher_option", "0123", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), RulesActivity.class));
            }
        }));
        list.add(new Menu("Chính sách bảo mật", "ic_launcher_option", "0123", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), SecurityActivity.class));
            }
        }));
        list.add(new Menu("Trợ giúp", "ic_launcher_option", "0123", new IEvenItem() {
            @Override
            public void callEvent() {
                startActivity(new Intent(getContext(), RulesActivity.class));
            }
        }));
        list.add(new Menu("Đăng xuất", "ic_launcher_logout", "123", new IEvenItem() {
            @Override
            public void callEvent() {
                EventLogout();
            }
        }));

        return list;
    }

    private void checkAndCallLogin() {
        if(AccountData.userLogin != null && AccountData.userLogin.isLogin()){
            Toast.makeText(getContext(), "User already exists ", Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }

    private void EventLogout(){
        new AlertDialog.Builder(getContext())
                .setTitle("THOÁT")
                .setMessage("Bạn có muốn đăng xuất?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (AccountData.firebaseUser != null) {
                            AccountData.firebaseAuth.signOut();
                            AccountData.firebaseUser = null;
                            AccountData.userLogin.setLogin(false);
                            AccountData.userLogin.isSignOut = true;
                            FAHQuery.UpdateData(AccountData.userLogin, "Account/" + AccountData.userLogin.getKey());
                            gvMenu.setAdapter(new GridListMenuMainAdapter(getActivity(), getListData()));
                        } else {
                            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void checkImageUser(){
//        if(MainActivity.userLogin.isLogin() == true){
//            progressDoalog.show();
//            try{
//                if (MainActivity.userLogin.getKey() != null) {
//                    FirebaseDatabase.getInstance().getReference().child("Avata").child(MainActivity.userLogin.getKey()).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if (dataSnapshot != null) {
//                                Image img =dataSnapshot.getValue(Image.class);
//                                if(img!=null){
//                                    MainActivity.userLogin.setAvata(img.getSource());
//                                    progressDoalog.dismiss();
//                                    GetControl();
//                                }else{
//                                    progressDoalog.dismiss();
//                                    GetControl();
//                                }
//                            }
//
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                            Toast.makeText(getContext(), "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
//                            progressDoalog.dismiss();
//                        }
//                    });
//                }
//            }catch (Exception e){
//                Toast.makeText(getContext(), "Lỗi"+e, Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            GetControl();
//        }
    }
}
