package com.example.fah.FAHCommon.FAHDatabase;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FAHQuery {
    private static FirebaseDatabase reference = FirebaseDatabase.getInstance();

    public static DatabaseReference GetData(String url){
        return reference.getReference(url);
    }

    public static Query GetDataQuery(FAHQueryParam query){
        Query result = reference.getReference(query.getTable()).orderByChild(query.getField());

        switch (query.getTypeQuery()){
            case FAHQueryParam.EQUAL:
                result = Equal(result, query.getParam(), query.getTypeParam());
        }

        return result;
    }

    public static List<?> GetDataObject(DataSnapshot dataSnapshot, Object typeObject){
        List<Object> listData = new ArrayList<>();
        if (dataSnapshot.getValue() != null) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Object item = snapshot.getValue(typeObject.getClass());
                CallMethodVoid(item, "setKey", new Class[]{String.class}, new Object[]{snapshot.getKey()});
                listData.add(item);
            }
        }

        return listData;
    }

    public static void InsertData(Object data, String url){
        reference.getReference(url).push().setValue(data);
    }

    public static void InsertData(Object data){
        InsertData(data, GetNameDB(data));
    }

    public static void InsertData(List<?> data){
        for(Object item : data){
            InsertData(item);
        }
    }

    public static void UpdateData(Object data, String url){
        reference.getReference(url).setValue(data);
    }

    public static void UpdateData(Object data){
        UpdateData(data, GetReferenceDB(data));
    }

    public static void UpdateData(List<?> data){
        for(Object item : data){
            UpdateData(item);
        }
    }

    public static void DeleteData(String url){
        reference.getReference(url).removeValue();
    }

    public static void DeleteData(Object data){
        DeleteData(GetReferenceDB(data));
    }

    public static void DeleteData(List<?> data){
        for(Object item : data){
            DeleteData(item);
        }
    }

    private static String GetReferenceDB(Object data){
        String key = (String) CallMethodObject(data, "getKey", new Class[]{}, new Object[]{});

        return GetNameDB(data).concat("/").concat(key);
    }

    private static Object CallMethodObject(Object data, String methoud, Class[] typeClass, Object[] param){
        try {
            Object value = data.getClass().getMethod(methoud, typeClass).invoke(data, param);

            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void CallMethodVoid(Object data, String methoud, Class[] typeClass, Object[] param){
        try {
            data.getClass().getMethod(methoud, typeClass).invoke(data, param);
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

    private static Query Equal(Query data, Object value, String type){
        switch (type){
            case FAHQueryParam.TypeString: {
                data = data.equalTo((String) value);
                break;
            }
            case FAHQueryParam.TypeDouble: {
                data = data.equalTo((Double) value);
                break;
            }
            case FAHQueryParam.TypeBoolean: {
                data = data.equalTo((Boolean) value);
                break;
            }
        }

        return data;
    }
}
