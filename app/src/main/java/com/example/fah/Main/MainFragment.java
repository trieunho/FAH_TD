package com.example.fah.Main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fah.Control.FAHCombobox;
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
public class MainFragment extends Fragment {
    View view;

    public EditText editText;
    private FAHCombobox fahCombobox = new FAHCombobox();
    private String[] items = {"Ngọc Kim","Văn Linh","Tường Minh"};


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        EditTextControl();

        // Inflate the layout for this fragment
        return view;
    }

    private void EditTextControl(){
        editText = view.findViewById(R.id.editText);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        fahCombobox.ShowItemChoose(getActivity(), editText, items);
    }
}
