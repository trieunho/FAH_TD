package com.example.fah.TestControl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fah.Control.FAHSpinner;
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

    public EditText editText1;
    public Button button;
    public Button button1;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test, container, false);

        getControl();

        return view;
    }

    public void onClickButton(View view){
        if(editText1.isEnabled() || button.isEnabled()){
            editText1.setEnabled(false);
            button.setEnabled(false);
        }else{
            editText1.setEnabled(true);
            button.setEnabled(true);
        }
    }

    private void getControl(){
        // User design
        Spinner spTime = view.findViewById(R.id.spTime);
        Spinner spJob = view.findViewById(R.id.spJob);

        List<String> listJob = new ArrayList();
        listJob.add("Java");
        listJob.add("Android");
        listJob.add("PHP");
        listJob.add("C#");
        listJob.add("ASP.NET");

        // Sử dụng
        FAHSpinner.setItemSource(getActivity(), spJob, listJob);

        List<String> listTime = new ArrayList();
        listTime.add("Buổi sáng");
        listTime.add("Buổi chiều");
        listTime.add("Buổi tối");
        listTime.add("Tất cả");

        FAHSpinner.setItemSource(getActivity(), spTime, listTime);

        editText1 = view.findViewById(R.id.name_edit_text1);
        button = view.findViewById(R.id.button);
        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FAHMessage.AlertDialogMessage(getActivity());
            }
        });
    }
}
