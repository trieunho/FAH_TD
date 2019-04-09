package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHCommon.FAHMessage;
import com.example.fah.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    public View view;

    protected EditText cbbJob;
    protected EditText cbbTime;
    protected Button btnSearch;

    private FAHCombobox fahComboboxJob = new FAHCombobox();
    private String[] listJob = {"Đứng đường", "Đứng trụ điện", "Đứng gốc cây", "Đứng canteen", "Mệt thì ngồi"};

    private FAHCombobox fahComboboxTime = new FAHCombobox();
    private String[] listTime = {"Buổi sáng", "Buổi chiều", "Buổi tối", "Cả ngày", "Cả đêm", "Xuyên màn đêm"};

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_search, container, false);

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
}
