package com.example.fah.TestControl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fah.Control.FAHSpinner;
import com.example.fah.TestControl.GridView.ObjectGrid;
import com.example.fah.TestControl.GridView.CustomGridAdapter;
import com.example.fah.FHADefine.FAHMessage;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {
    public View view;

    public Spinner spinner;
    public EditText editText;
    public Button button;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test, container, false);

        GetControl();

        return view;
    }

    private void GetControl(){
        SpinnerControl();
        EditTextControl();
        ButtonControl();
        GridViewControl();
    }

    private void SpinnerControl(){
        spinner = view.findViewById(R.id.spinner);

        // Set item source
        List<String> listJob = new ArrayList();
        listJob.add("Java");
        listJob.add("Android");
        listJob.add("PHP");
        listJob.add("C#");
        listJob.add("ASP.NET");
        FAHSpinner.setItemSource(getActivity(), spinner, listJob);
    }

    private void EditTextControl(){
        editText = view.findViewById(R.id.editText);
    }

    private void ButtonControl(){
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAHMessage.ToastMessage(getActivity(), "Click button");
            }
        });
    }

    private void GridViewControl(){
        List<ObjectGrid> image_details = getListData();
        final GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(new CustomGridAdapter(getActivity(), image_details));

        // Khi người dùng click vào các GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                ObjectGrid objectGrid = (ObjectGrid) o;
                Toast.makeText(getActivity(), "Selected :"
                        + " " + objectGrid, Toast.LENGTH_LONG).show();
            }
        });
    }

    private  List<ObjectGrid> getListData() {
        List<ObjectGrid> list = new ArrayList<ObjectGrid>();
        ObjectGrid vietnam = new ObjectGrid("Vietnam 111111111111111111111111111111111111111111111111111111111111111111111111111111 1111111111111111111111111111111111", "ic_arrow_down", 98000000);
        ObjectGrid usa = new ObjectGrid("United States", "ic_arrow_down", 320000000);
        ObjectGrid russia = new ObjectGrid("Russia", "ic_arrow_down", 142000000);
        ObjectGrid australia = new ObjectGrid("Australia", "ic_arrow_down", 23766305);
        ObjectGrid japan = new ObjectGrid("Japan", "ic_arrow_down", 126788677);

        list.add(vietnam);
        list.add(usa);
        list.add(russia);
        list.add(australia);
        list.add(japan);

        return list;
    }
}
