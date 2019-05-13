package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHData.NotificationData;
import com.example.fah.FAHModel.Adapters.NotificationAdapter;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHModel.Models.Notification;
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
                final ListView listView = view.findViewById(R.id.notificationList);
                listView.setAdapter(new NotificationAdapter(getContext(), R.layout.list_notification_item, NotificationData.listNotifications));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        Notification notification = NotificationData.listNotifications.get(position);
                        Toast.makeText(getContext(), notification.getBody(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void EventFail(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
