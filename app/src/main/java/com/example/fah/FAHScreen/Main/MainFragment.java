package com.example.fah.FAHScreen.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.fah.FAHControl.FAHCombobox;
import com.example.fah.FHADefine.FAHMessage;
import com.example.fah.FAHScreen.Notification.GridView.GridListPostMainAdapter;
import com.example.fah.FAHScreen.Notification.GridView.Post;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    public View view;

    protected EditText cbbJob;
    protected EditText cbbTime;
    protected Button btnSearch;
    protected GridView gvPost;

    private FAHCombobox fahComboboxJob = new FAHCombobox();
    private String[] listJob = {"Đứng đường", "Đứng trụ điện", "Đứng gốc cây", "Đứng canteen", "Mệt thì ngồi"};

    private FAHCombobox fahComboboxTime = new FAHCombobox();
    private String[] listTime = {"Buổi sáng", "Buổi chiều", "Buổi tối", "Cả ngày", "Cả đêm", "Xuyên màn đêm"};

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        GetControl();

        // Inflate the layout for this fragment
        return view;
    }

    // Get and set all control on create form
    private void GetControl(){
        ComboboxControl();
        ButtonControl();
    }

    // Get and set control Combobox
    private void ComboboxControl(){
        ComboboxJobControl();
        ComboboxTimeControl();
        GridViewControl();
    }

    private void ButtonControl(){
        ButtonSearchControl();
    }

    // Get and set Combobox Job
    private void ComboboxJobControl(){
        cbbJob = view.findViewById(R.id.cbbJob);

        cbbJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fahComboboxJob.ShowItemChoose(getActivity(), cbbJob, listJob);
            }
        });
    }

    // Get and set Combobox Time
    private void ComboboxTimeControl(){
        cbbTime = view.findViewById(R.id.cbbTime);

        cbbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fahComboboxTime.ShowItemChoose(getActivity(), cbbTime, listTime);
            }
        });
    }

    private void ButtonSearchControl(){
        btnSearch = view.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fahComboboxJob.GetItemChoose() == -1){
                    FAHMessage.ToastMessage(getActivity(), "Chọn nghề giúp với");
                    return;
                }

                if(fahComboboxTime.GetItemChoose() == -1){
                    FAHMessage.ToastMessage(getActivity(), "Chọn thời gian giúp với");
                    return;
                }

                FAHMessage.ToastMessage(getActivity(), "OK rồi đó");
                return;
            }
        });
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
        Post vietnam = new Post("Vietnam", "ic_arrow_down", "111111");
        Post usa = new Post("Russia", "ic_arrow_down", "33333");
        Post australia = new Post("Australia", "ic_arrow_down", "2222");
        Post japan = new Post("Japan", "ic_arrow_down", "44444444");

        list.add(vietnam);
        list.add(usa);
        list.add(australia);
        list.add(japan);

        return list;
    }
}
