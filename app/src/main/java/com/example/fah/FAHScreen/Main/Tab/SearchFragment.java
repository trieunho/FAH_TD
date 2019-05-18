package com.example.fah.FAHScreen.Main.Tab;

import android.app.ProgressDialog;
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
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHModel.Adapters.ListPostAdapter;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHScreen.Post.DetailPostActivity;
import com.example.fah.FAHScreen.Post.DetailSearchPostActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.fah.FAHCommon.FAHControl.FAHCombobox.VALUEDEFAULT;


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

    DatabaseReference myDbRef;
    Query myRefQuery;
    ListPostAdapter listPostAdapter;
    List<Post> listPost = new ArrayList<>();
    ProgressDialog progressDialog;
    int job = VALUEDEFAULT;

    private FAHCombobox fahComboboxJob;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_search, container, false);

        // progress dialog
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMax(100);
        progressDialog.setMessage("Đang tải dữ liệu...");
        progressDialog.setTitle("Waiting");

        addControl();
        addEvent();

        // Inflate the layout for this fragment
        return view;
    }


    // Get and set all control on create form
    private void addControl() {
        cbbJob = view.findViewById(R.id.cbbJob);
        txtStartTime = view.findViewById(R.id.txtStartTime);
        txtEndTime = view.findViewById(R.id.txtEndTime);
        btnSearch = view.findViewById(R.id.btnSearch);
        lvHotPost = view.findViewById(R.id.lvHotPost);
        myDbRef = FAHQuery.GetData("CATEGORY_OF_POST");
        myRefQuery = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, 1, FAHQueryParam.TypeInteger));
    }

    private void addEvent() {
        // Set list of job
        progressDialog.show();
        myRefQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> listData = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                listPost = new ArrayList<>();
                if (getContext() == null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    return;
                }
                if (listData == null) {
                    lvHotPost.setAdapter(new SearchAdapter(getContext(), listPost));
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    return;
                }

                if (listData.size() > 0) {
                    for (Post item : listData) {
                        if (item.getTypeOfPost() != null && "1".equals(item.getTypeOfPost().getTypeID())) {
                            listPost.add(item);
                        }
                    }
                }

                lvHotPost.setAdapter(new SearchAdapter(getContext(), listPost));
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

        //Set list combobox
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Category> data = (List<Category>) FAHQuery.GetDataObject(dataSnapshot, new Category());
                String[] list = new String[data.size() + 1];

                list[0] = "Tất cả";
                for (Category item : data) {
                    list[Integer.parseInt(item.getCategoryID())] = item.getCategoryName();
                }

                fahComboboxJob = new FAHCombobox(getActivity(), cbbJob, list, job);
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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailSearchPostActivity.class);
                intent.putExtra("fromScreen", "home");
                intent.putExtra("job", fahComboboxJob.getItemChoose());
                intent.putExtra("dtFrom", String.valueOf(txtStartTime.getText()));
                intent.putExtra("dtTo", String.valueOf(txtEndTime.getText()));

                startActivity(intent);
            }
        });
    }

    // Get and set Combobox Job
    private void ComboboxJobControl1() {
//        fahComboboxJob = new FAHCombobox(getActivity(), cbbJob, listJob, 1);
    }

    // Get and set Combobox Time
    private void ComboboxTimeControl1() {
//        cbbTime = view.findViewById(R.id.cbbTime);
    }

    private void ButtonSearchControl() {
        btnSearch = view.findViewById(R.id.btnSearch);


    }
}
