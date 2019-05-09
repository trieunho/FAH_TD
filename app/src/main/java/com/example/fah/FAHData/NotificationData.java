package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
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

public class NotificationData {
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    public static ArrayList<Notification> listNotifications=new ArrayList<Notification>();

    public static void setUpNotificationData(IEventData eventData) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reloadListNotification(eventData);
    }

    public static void addNotificationData( Notification notification,final IEventData event) {
        if(notification!=null && notification.getAccountKey()!=null && !notification.getAccountKey().equals("")){
            FirebaseDatabase.getInstance().getReference().child("Notification").child(notification.getAccountKey()).push().setValue(notification).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        event.EventSuccess();
                    }else {
                        event.EventFail("Lỗi khi thêm thông báo, vui lòng kiểm tra lại!");
                    }
                }
            });
        }else {
            event.EventFail("Vui lòng kiểm tra lại AccountID");
        }
    }
    // if add for all user then set accoutKey="0000"
    public static void addNotificationDataAllUser( Notification notification,final IEventData event) {
        FirebaseDatabase.getInstance().getReference().child("Notification").child("0000").push().setValue(notification).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if(task.isSuccessful()){
                    event.EventSuccess();
              }else {
                    event.EventFail("Lỗi khi thêm thông báo, vui lòng kiểm tra lại!");
              }
            }
        });
    }
    public static void reloadListNotification(final IEventData eventData){
        listNotifications = new ArrayList<Notification>();
        if(AccountData.userLogin!=null){
            if(AccountData.userLogin.getKey()!=null){
                loadListNotification(AccountData.userLogin.getKey(), new IEventData() {
                    @Override
                    public void EventSuccess() {
                        loadListNotificationAllUser(new IEventData() {
                            @Override
                            public void EventSuccess() {
                                eventData.EventSuccess();
                            }

                            @Override
                            public void EventFail(String message) {
                                eventData.EventFail(message);
                            }
                        });
                    }

                    @Override
                    public void EventFail(String message) {
                        loadListNotificationAllUser(new IEventData() {
                            @Override
                            public void EventSuccess() {
                                eventData.EventSuccess();
                            }

                            @Override
                            public void EventFail(String message) {
                                eventData.EventFail(message);
                            }
                        });
                    }
                });
            }else{
                eventData.EventFail("Không tồn tại Account");
            }
        }else{
            eventData.EventFail("Không tồn tại Account");
        }


    }
    private static void loadListNotification(String accountKey, final IEventData eventData) {
        FirebaseDatabase.getInstance().getReference().child("Notification")
                .child(accountKey)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot!=null){
                          listNotifications = (ArrayList<Notification>) FAHQuery.GetDataObject(dataSnapshot,new Notification());
                        }
                        eventData.EventSuccess();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        eventData.EventFail("Lỗi khi truy cập thông báo.");
                    }
                });

    }

    private static void loadListNotificationAllUser(final IEventData iEventData) {
        FirebaseDatabase.getInstance().getReference().child("Notification")
                .child("0000")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot!=null){
                            ArrayList<Notification> arrayList = (ArrayList<Notification>) FAHQuery.GetDataObject(dataSnapshot,new Notification());
                            if(arrayList.size()>0){
                                for (Notification n:arrayList) {
                                    listNotifications.add(n);
                                }
                            }
                        }
                        iEventData.EventSuccess();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        iEventData.EventFail("Lỗi khi truy cập thông báo chung.");
                    }
                });
    }
}
