package com.example.fah.FAHDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FAHQuery {
    public static DatabaseReference GetData(String nameDB){
        return FirebaseDatabase.getInstance().getReference(nameDB);
    }

    public static List<?> GetDataObject(DataSnapshot dataSnapshot){
        List<Object> listData = new ArrayList<>();
        if (dataSnapshot.getValue() != null) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Object item = snapshot.getValue(Object.class);
                // CallMethod(item);
                listData.add(item);
            }
        }

        return listData;
    }

    public static void InsertData(Object data){
        FirebaseDatabase.getInstance().getReference(GetNameDB(data)).push().setValue(data);
    }

    public static void InsertData(List<?> data){
        for(Object item : data){
            FirebaseDatabase.getInstance().getReference(GetNameDB(item)).push().setValue(item);
        }
    }

    public static void UpdateData(Object data){
        FirebaseDatabase.getInstance().getReference(GetReferenceDB(data)).setValue(data);
    }

    public static void UpdateData(List<?> data){
        for(Object item : data){
            FirebaseDatabase.getInstance().getReference(GetReferenceDB(item)).setValue(item);
        }
    }

    public static void DeleteData(Object data){
        FirebaseDatabase.getInstance().getReference(GetReferenceDB(data)).removeValue();
    }

    public static void DeleteData(List<?> data){
        for(Object item : data){
            FirebaseDatabase.getInstance().getReference(GetReferenceDB(item)).removeValue();
        }
    }

    private static String GetReferenceDB(Object data){
        try {
            String key = (String) data.getClass().getMethod("getKey").invoke(data);

            return GetNameDB(data).concat("/").concat(key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void CallMethod(Object data){
        try {
            data.getClass().getMethod("setKey").invoke(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static String GetNameDB(Object data){
        return data.getClass().getSimpleName();
    }
}
