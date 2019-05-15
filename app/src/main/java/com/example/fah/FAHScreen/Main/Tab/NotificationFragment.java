package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHCommon.FAHDatabase.Table.TestDB;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHData.NotificationData;
import com.example.fah.FAHModel.Adapters.NotificationAdapter;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHModel.Models.Notification;
import com.example.fah.R;
import com.example.fah.TestControl.Sample_Add_Edit_Delete_Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_notification, container, false);

        GetControl();

        return view;
    }

    private void GetControl(){
        GridControl();
    }

    private void GridControl(){
        final ListView listView = view.findViewById(R.id.notificationList);
        if (AccountData.userLogin != null) {
            if (AccountData.userLogin.getKey() != null) {
                //load data user
                FAHQueryParam queryParam = new FAHQueryParam("Notification", "accountKey", FAHQueryParam.EQUAL, AccountData.userLogin.getKey(), FAHQueryParam.TypeString);

                FAHQuery.GetDataQuery(queryParam).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<Notification> listNotifications = (ArrayList<Notification>) FAHQuery.GetDataObject(dataSnapshot, new Notification());
                        listView.setAdapter(new NotificationAdapter(getContext(), R.layout.list_notification_item, listNotifications));
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
