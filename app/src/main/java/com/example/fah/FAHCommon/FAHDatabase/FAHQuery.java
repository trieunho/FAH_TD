package com.example.fah.FAHCommon.FAHDatabase;

import com.example.fah.FAHCommon.FAHControl.FAHMessage;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHCommon.FAHExcuteData.ExcuteString;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FAHQuery {
    private static FirebaseDatabase reference = FirebaseDatabase.getInstance();

    /**
     * Get data theo url
     * @param url
     * @return
     */
    public static DatabaseReference GetData(String url){
        return reference.getReference(url);
    }

    /**
     * Query data firebase
     * @param query
     * @return
     */
    public static Query GetDataQuery(FAHQueryParam query){
        Query result = GetData(query.getTable()).orderByChild(query.getField());

        switch (query.getTypeQuery()){
            case FAHQueryParam.EQUAL:
                result = equalTo(result, query);
                break;
            case FAHQueryParam.START:
                result = startAt(result, query);
                break;
            case FAHQueryParam.END:
                result = endAt(result, query);
                break;
            case FAHQueryParam.BETWEEN:
                result = between(result, query);
                break;
        }

        return result;
    }

    /**
     * Get 1 list data from child data
     * @param dataSnapshot
     * @param typeObject
     * @return
     */
    public static List<?> GetDataObject(DataSnapshot dataSnapshot, Object typeObject){
        List<Object> listData = null;
        if (dataSnapshot.getValue() != null) {
            listData = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Object item = snapshot.getValue(typeObject.getClass());
                CallMethodVoid(item, "setKey", new Class[]{String.class}, new Object[]{snapshot.getKey()});
                listData.add(item);
            }
        }

        return listData;
    }

    /**
     * Get object data by key
     * @param dataSnapshot
     * @param typeObject
     * @return
     */
    public static Object GetDataObjectByKey(DataSnapshot dataSnapshot, Object typeObject){
        if (dataSnapshot.getValue() != null) {
            Object item = dataSnapshot.getValue(typeObject.getClass());
            CallMethodVoid(item, "setKey", new Class[]{String.class}, new Object[]{dataSnapshot.getKey()});
            return item;
        }else{
            return null;
        }
    }

    /**
     * Insert 1 data with url
     * @param data
     * @param url
     */
    public static void InsertData(Object data, String url){
        reference.getReference(url).push().setValue(data);
    }

    /**
     * Insert 1 list data with url
     * @param data
     * @param url
     */
    public static void InsertData(List<?> data, String url){
        for(Object item : data){
            InsertData(item, url);
        }
    }

    /**
     * Insert 1 data with name object database
     * @param data
     */
    public static void InsertData(Object data){
        InsertData(data, GetNameDB(data));
    }

    /**
     * Insert 1 list data with name object database
     * @param data
     */
    public static void InsertData(List<?> data){
        for(Object item : data){
            InsertData(item);
        }
    }

    /**
     * Insert 1 data with url and get key
     * @param data
     * @param url
     */
    public static String InsertDataGetKey(Object data, String url){
        String key = reference.getReference(url).push().getKey();
        UpdateData(data, ExcuteString.GetUrlData(url, key));

        return key;
    }

    /**
     * Insert 1 list data with url and get key
     * @param data
     * @param url
     */
    public static ArrayList<String> InsertDataGetKey(List<?> data, String url){
        ArrayList<String> listKey = new ArrayList<>();
        for(Object item : data) {
            listKey.add(InsertDataGetKey(item, url));
        }

        return listKey;
    }

    /**
     * Insert 1 data with name object and get key
     * @param data
     */
    public static String InsertDataGetKey(Object data){
        return InsertDataGetKey(data, GetNameDB(data));
    }

    /**
     * Insert 1 list data with name object and get key
     * @param data
     */
    public static ArrayList<String> InsertDataGetKey(List<?> data){
        ArrayList<String> listKey = new ArrayList<>();
        for(Object item : data) {
            listKey.add(InsertDataGetKey(item));
        }

        return listKey;
    }

    /**
     * Update 1 data with url
     * @param data
     * @param url
     */
    public static String UpdateData(Object data, String url){
        try {
            reference.getReference(url).setValue(data);
            return "";
        } catch (Exception ex) {
            return ex.getMessage();
        }

    }

    /**
     * Update 1 list data with url
     * @param data
     * @param url
     */
    public static void UpdateData(List<?> data, String url){
        for(Object item : data){
            UpdateData(item, url);
        }
    }

    /**
     * Update 1 data with name object
     * @param data
     */
    public static void UpdateData(Object data){
        UpdateData(data, GetReferenceDB(data));
    }

    /**
     * Update 1 list data with name object
     * @param data
     */
    public static void UpdateData(List<?> data){
        for(Object item : data){
            UpdateData(item);
        }
    }

    /**
     * Delete data with 1 url or 1 list url
     * @param url
     */
    public static String DeleteData(String... url){
        try {
            for (String item : url){
                reference.getReference(item).removeValue();
            }
            return "";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Delete data with name object, get key from 1 data
     * @param data
     */
    public static void DeleteData(Object data){
        DeleteData(GetReferenceDB(data));
    }

    /**
     * Delete data with name object, get key from 1 list data
     * @param data
     */
    public static void DeleteData(List<?> data){
        for (Object item : data){
            DeleteData(item);
        }
    }

    private static String GetReferenceDB(Object data){
        String key = (String) CallMethodObject(data, "getKey", new Class[]{}, new Object[]{});

        return ExcuteString.GetUrlData(GetNameDB(data), key);
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

    private static Query equalTo(Query data, FAHQueryParam query){
        switch (query.getTypeParam()){
            case FAHQueryParam.TypeInteger: {
                data = data.equalTo((Integer) query.getParam());
                break;
            }
            case FAHQueryParam.TypeString: {
                data = data.equalTo((String) query.getParam());
                break;
            }
            case FAHQueryParam.TypeDouble: {
                data = data.equalTo((Double) query.getParam());
                break;
            }
            case FAHQueryParam.TypeBoolean: {
                data = data.equalTo((Boolean) query.getParam());
                break;
            }
        }

        return data;
    }

    private static Query startAt(Query data, FAHQueryParam query){
        switch (query.getTypeParam()){
            case FAHQueryParam.TypeString: {
                data = data.startAt((String) query.getParam());
                break;
            }
            case FAHQueryParam.TypeDouble: {
                data = data.startAt((Double) query.getParam());
                break;
            }
            case FAHQueryParam.TypeBoolean: {
                data = data.startAt((Boolean) query.getParam());
                break;
            }
        }

        return data;
    }

    private static Query endAt(Query data, FAHQueryParam query){
        switch (query.getTypeParam()){
            case FAHQueryParam.TypeString: {
                data = data.endAt((String) query.getParam());
                break;
            }
            case FAHQueryParam.TypeDouble: {
                data = data.endAt((Double) query.getParam());
                break;
            }
            case FAHQueryParam.TypeBoolean: {
                data = data.endAt((Boolean) query.getParam());
                break;
            }
        }

        return data;
    }

    private static Query between(Query data, FAHQueryParam query){
        switch (query.getTypeParam()){
            case FAHQueryParam.TypeString: {
                data = data.startAt((String) query.getParam()).endAt((String) query.getParam2());
                break;
            }
            case FAHQueryParam.TypeDouble: {
                data = data.startAt((Double) query.getParam()).endAt((Double) query.getParam2());
                break;
            }
            case FAHQueryParam.TypeBoolean: {
                data = data.startAt((Boolean) query.getParam()).endAt((Boolean) query.getParam2());
                break;
            }
        }

        return data;
    }

    private static void UpdateListData(Object data, String url){
        try {
            for(Field field : data.getClass().getDeclaredFields()){
                if(field.getType().getSimpleName().equals("ArrayList")){
//                    String keyValue = ExcuteString.GetUrlData(url, field.getName());
//                    List<?> value = (List<?>) field.get(new Object());
//                    UpdateListDetail(value, keyValue);
                }else{
                    field.setAccessible(true);
//                    Object value = field.get(field.getType());
                    UpdateOrInsertData("1", url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void UpdateListDetail(List<?> data, String url){
        for(Object item : data){
            String key = (String) CallMethodObject(item, "getKey", new Class[]{}, new Object[]{});
            UpdateData(item, ExcuteString.GetUrlData(url, key));
        }
    }

    private static void UpdateOrInsertData(Object data, String url){
        String key = (String) CallMethodObject(data, "getKey", new Class[]{}, new Object[]{});
        if(ExcuteString.IsNullOrEmpty(key)){
            InsertData(data, ExcuteString.GetUrlData(url, key));
        }else{
            UpdateData(data, ExcuteString.GetUrlData(url, key));
        }
    }
}
