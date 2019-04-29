package com.example.fah.FAHScreen.Main.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fah.FAHModel.Adapters.NotificationAdapter;
import com.example.fah.FAHModel.Models.Notification;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

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
        List<Notification> notificationList = getListData();
        ListView listView = view.findViewById(R.id.notificationList);
        listView.setAdapter(new NotificationAdapter(getContext(),R.layout.list_notification_item, notificationList));
    }

    private List<Notification> getListData() {
        List<Notification> list = new ArrayList<Notification>();

     Notification notification6 = new Notification("Handle action bar item clicks here. The action bar willHandle action bar item clicks here. The action123567890987654 bar willHandle action bar item clicks here. The action bar willHandle action bar item clicks here. The aion bar item clicks here. The afsfdsgfgfgđfction bar will","5 phút trước",R.drawable.ic_arrow_down+"");
        list.add(notification6);

        return list;
    }
}
