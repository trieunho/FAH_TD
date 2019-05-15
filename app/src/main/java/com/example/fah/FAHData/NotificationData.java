package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHModel.Models.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class NotificationData {
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    public static ArrayList<Notification> listNotifications;
    private static boolean checkLoad = true;

    public static void setUpNotificationData(final IEventData iEventData) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        listNotifications = new ArrayList<>();
        reloadListNotification(new IEventData() {
            @Override
            public void EventSuccess() {
                iEventData.EventSuccess();
            }

            @Override
            public void EventFail(String message) {
                iEventData.EventFail(message);
            }
        });
    }

    private static void reload(IEventData eventData) {
        checkLoad = true;
        Collections.sort(listNotifications);
        eventData.EventSuccess();
    }

    private static void reloadListNotification(final IEventData eventData) {
        checkLoad = false;
        if (AccountData.userLogin != null) {
            if (AccountData.userLogin.getKey() != null) {
                //load data user
                FAHQueryParam queryParam = new FAHQueryParam("Notification", "accountKey", FAHQueryParam.EQUAL, AccountData.userLogin.getKey(), FAHQueryParam.TypeString);

                FAHQuery.GetDataQuery(queryParam).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listNotifications = (ArrayList<Notification>) FAHQuery.GetDataObject(dataSnapshot, new Notification());
                        if (checkLoad) {
                            reload(eventData);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        eventData.EventFail("Lỗi khi truy cập thông báo.");
                    }
                });
            }
        }
    }
}
