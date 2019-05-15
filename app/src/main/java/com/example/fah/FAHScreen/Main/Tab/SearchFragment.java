package com.example.fah.FAHScreen.Main.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHCommon.FAHControl.FAHMessage;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHModel.Adapters.ListPostAdapter;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHScreen.Post.DetailPostActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    public View view;

    protected EditText cbbJob;
    protected EditText txtStartTime;
    protected EditText txtEndTime;
    protected Button btnSearch;
    protected ListView lvHotPost;

    Query myRef;
    ListPostAdapter listPostAdapter;
    List<Post> listPost = new ArrayList<>();

    private FAHCombobox fahComboboxJob;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_search, container, false);

        addControl();
        addEvent();

        // Inflate the layout for this fragment
        return view;
    }



    // Get and set all control on create form
    private void addControl(){
        cbbJob = view.findViewById(R.id.cbbJob);
        txtStartTime = view.findViewById(R.id.txtStartTime);
        txtEndTime = view.findViewById(R.id.txtEndTime);
        btnSearch = view.findViewById(R.id.btnSearch);
        lvHotPost = view.findViewById(R.id.lvHotPost);
        myRef = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, 1, FAHQueryParam.TypeInteger));
    }

    private void addEvent() {
        // Set list of job
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> listData = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                listPost = new ArrayList<>();
                if (listData != null && listData.size() > 0) {
                    for (Post item : listData) {
                        if (item.getTypeOfPost() != null && "1".equals(item.getTypeOfPost().getTypeID())) {
                            listPost.add(item);
                        }
                    }
                }

                lvHotPost.setAdapter(new SearchAdapter(getContext(), listPost));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        lvHotPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailPostActivity.class);
                intent.putExtra("key", listPost.get(position).getKey());
                intent.putExtra("keyAccount", listPost.get(position).getKeyAccount());
                startActivity(intent);
            }
        });

//        fahComboboxJob = new FAHCombobox(getActivity(), cbbJob, listJob, 1);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fahComboboxJob.getItemChoose() == -1){
                    FAHMessage.ToastMessage(getActivity(), "Chọn nghề giúp với");
                    return;
                }

                FAHMessage.ToastMessage(getActivity(), "OK rồi đó");
                return;
            }
        });
    }

    // Get and set Combobox Job
    private void ComboboxJobControl1(){
//        fahComboboxJob = new FAHCombobox(getActivity(), cbbJob, listJob, 1);
    }

    // Get and set Combobox Time
    private void ComboboxTimeControl1(){
//        cbbTime = view.findViewById(R.id.cbbTime);
    }

    private void ButtonSearchControl(){
        btnSearch = view.findViewById(R.id.btnSearch);


    }
}
