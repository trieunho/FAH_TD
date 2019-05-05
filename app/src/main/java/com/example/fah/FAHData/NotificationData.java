package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHModel.Models.ListNotification;
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

    public static void setUpNotificationData() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public static void addNotificationData( Notification notification,final IEventData event) {
        final  Notification notificationadd = notification;
        loadListNotification(notification.getAccountKey(), new IEventData() {
            @Override
            public void EventSuccess() {
                loadListNotificationAllUser(new IEventData() {
                    @Override
                    public void EventSuccess() {
                        listNotifications.add(notificationadd);
                        FirebaseDatabase.getInstance().getReference().child("Notification")
                                .child(notificationadd.getAccountKey())
                                .setValue(listNotifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    event.EventSuccess();
                                } else {
                                    event.EventFail();
                                }
                            }
                        });
                    }
                    @Override
                    public void EventFail() {
                        event.EventFail();
                    }
                });
            }
            @Override
            public void EventFail() {
                event.EventFail();
            }
        });

    }
    // if add for all user then set accoutKey="0000"
    public static void addNotificationDataAllUser( Notification notification,final IEventData event) {
        final  Notification notificationadd = notification;
        listNotifications = new ArrayList<Notification>();
        loadListNotificationAllUser(new IEventData() {
            @Override
            public void EventSuccess() {
                listNotifications.add(notificationadd);
                FirebaseDatabase.getInstance().getReference().child("Notification")
                        .child(notificationadd.getAccountKey())
                        .setValue(listNotifications).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            event.EventSuccess();
                        } else {
                            event.EventFail();
                        }
                    }
                });
            }

            @Override
            public void EventFail() {
            }
        });

    }

    private static void loadListNotification(String accountKey, final IEventData eventData) {
        FirebaseDatabase.getInstance().getReference().child("Notification")
                .child(accountKey)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot!=null){
                            ListNotification listNotification = dataSnapshot.getValue(ListNotification.class);
                            if(listNotification!=null){
                                NotificationData.listNotifications = listNotification.getListNotification();
                                eventData.EventSuccess();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private static void loadListNotificationAllUser(final IEventData eventData) {
        FirebaseDatabase.getInstance().getReference().child("Notification")
                .child("0000")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot!=null){
                            ListNotification listNotification = dataSnapshot.getValue(ListNotification.class);
                            if(listNotification!=null){
                               if(listNotification!=null && listNotification.getListNotification().size()>0){
                                   for (Notification notification: listNotification.getListNotification() ) {
                                       NotificationData.listNotifications.add(notification);
                                   }
                                   eventData.EventSuccess();
                               }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        eventData.EventFail();
                    }
                });
    }
}
