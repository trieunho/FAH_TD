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
import java.util.Collections;

public class NotificationData {
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    public static ArrayList<Notification> listNotifications ;
    private  static  ArrayList<Notification> listNotifications1 ;
    private  static  ArrayList<Notification> listNotifications2 ;
    private  static boolean checkLoad = true;
    public static void setUpNotificationData(final IEventData iEventData) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        listNotifications = new ArrayList<Notification>();
        listNotifications1 = new ArrayList<Notification>();
        listNotifications2 = new ArrayList<Notification>();
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

    private static void  reload(IEventData eventData){
            NotificationData.listNotifications = new ArrayList<Notification>();
            for (Notification n:listNotifications1) {
                listNotifications.add(n);
            }
            for (Notification n:listNotifications2) {
                listNotifications.add(n);
            }
            checkLoad = true;
            Collections.sort(listNotifications);
            eventData.EventSuccess();
    }
    private static void reloadListNotification(final IEventData eventData){
        checkLoad = false;
           if(AccountData.userLogin!=null){
            if(AccountData.userLogin.getKey()!=null){
                //load data user
                FirebaseDatabase.getInstance().getReference().child("Notification")
                        .child(AccountData.userLogin.getKey())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                listNotifications1 = new ArrayList<Notification>();
                                if(dataSnapshot!=null){
                                    ArrayList<Notification> arrayList1  = (ArrayList<Notification>) FAHQuery.GetDataObject(dataSnapshot,new Notification());
                                   if(arrayList1!=null && arrayList1.size()>0){
                                       for (Notification n : arrayList1) {
                                           listNotifications1.add(n);
                                       }
                                   }
                                }
                                if(checkLoad){
                                    reload(eventData);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                eventData.EventFail("Lỗi khi truy cập thông báo.");
                            }
                        });
                //load data all user
                FirebaseDatabase.getInstance().getReference().child("Notification")
                        .child("0000")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                listNotifications2  = new ArrayList<Notification>();
                                if(dataSnapshot!=null){
                                    ArrayList<Notification> arrayList = (ArrayList<Notification>) FAHQuery.GetDataObject(dataSnapshot,new Notification());
                                    if(arrayList!=null && arrayList.size()>0){
                                        for (Notification n:arrayList) {
                                            listNotifications2.add(n);
                                        }
                                    }
                                }
                                reload(eventData);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                eventData.EventFail("Lỗi khi truy cập thông báo chung.");
                            }
                        });
            }else{
                eventData.EventFail("Không tồn tại Account");
            }
        }else{
            eventData.EventFail("Không tồn tại Account");
        }
    }
}
