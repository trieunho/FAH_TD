package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fah.FAHData.NotificationData;
import com.example.fah.FAHModel.Adapters.NotificationAdapter;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.R;

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
         NotificationData.setUpNotificationData(new IEventData() {
            @Override
            public void EventSuccess() {
                ListView listView = view.findViewById(R.id.notificationList);
                listView.setAdapter(new NotificationAdapter(getContext(),R.layout.list_notification_item, NotificationData.listNotifications));
            }

            @Override
            public void EventFail(String message) {

            }
        });

    }
}
