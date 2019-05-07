package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Create an instance of this fragment.
 */
public class HotPostFragment extends Fragment {
    protected View view;
    protected ListView listView;
    private List<Post> listCreate;
    Query myRef;
    protected TextView txtTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_hot_post, container, false);

        addControls();
        addEvents();

        return view;
    }

    private void addControls(){
        if (AccountData.userLogin != null) {
            if (AccountData.userLogin.getRole() == 1) {
                txtTitle.setText("Danh sách bài viết đã ứng tuyển");
            } else if (AccountData.userLogin.getRole() == 2) {
                txtTitle.setText("Bài viết của tôi");
            }
        }

        myRef = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, 1, FAHQueryParam.TypeInteger));
    }

    private void addEvents() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> listData = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                listCreate = new ArrayList<>();
                if (listData != null && listData.size() > 0) {
                    if (AccountData.userLogin.getRole() == 2) {
                        for (Post item : listData) {
                            if (item.getKeyAccount().equals(AccountData.userLogin.getKey())) {
                                listCreate.add(item);
                            }
                        }
                    } else if (AccountData.userLogin.getRole() == 1) {
                        for (Post item : listData) {
                            if (item.getListAccount().contains(AccountData.userLogin.getKey())) {
                                listCreate.add(item);
                            }
                        }
                    }
                }

                listView.setAdapter(new SearchAdapter(getContext(), listCreate));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
