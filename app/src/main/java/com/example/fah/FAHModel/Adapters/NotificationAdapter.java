package com.example.fah.FAHModel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fah.FAHModel.Models.Notification;
import com.example.fah.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Notification> notificationList;

    public NotificationAdapter(Context context, int layout, List<Notification> notificationList) {
        this.context = context;
        this.layout = layout;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        // mapping view
        TextView txtNotification = convertView.findViewById(R.id.txtNotification);
        TextView txtTime = convertView.findViewById(R.id.txtTime);
        ImageView imageView = convertView.findViewById(R.id.imageNotification);

        // set values view
        Notification notification = notificationList.get(position);
        txtNotification.setText(notification.getTitle());
        if(notification.getAddDate()!=null){
            SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd/mm/yyyy");
            txtTime.setText(dt.format(notification.getAddDate()));
        }
        imageView.setImageResource(R.drawable.play);
        return convertView;
    }
}
