package com.example.fah.FAHCommon.FAHDatabase;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHCommon.FAHExcuteData.ExcuteString;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FAHQuery {
    private static FirebaseDatabase reference = FirebaseDatabase.getInstance();

    /**
     * Get data theo url
     *
     * @param url
     * @return
     */
    public static DatabaseReference GetData(String url) {
        return reference.getReference(url);
    }

    /**
     * Query data firebase
     *
     * @param query
     * @return
     */
    public static Query GetDataQuery(FAHQueryParam query) {
        Query result = GetData(query.getTable()).orderByChild(query.getField());

        switch (query.getTypeQuery()) {
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
     *
     * @param dataSnapshot
     * @param typeObject
     * @return
     */
    public static List<?> GetDataObject(DataSnapshot dataSnapshot, Object typeObject) {
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
     *
     * @param dataSnapshot
     * @param typeObject
     * @return
     */
    public static Object GetDataObjectByKey(DataSnapshot dataSnapshot, Object typeObject) {
        if (dataSnapshot.getValue() != null) {
            Object item = dataSnapshot.getValue(typeObject.getClass());
            CallMethodVoid(item, "setKey", new Class[]{String.class}, new Object[]{dataSnapshot.getKey()});
            return item;
        } else {
            return null;
        }
    }

    /**
     * Insert 1 data with url and get key
     *
     * @param data
     * @param url
     */
    public static String InsertData(Object data, String url) {
        String urlResult = ExcuteString.GetUrlData(url, reference.getReference(url).push().getKey());
        UpdateData(data, urlResult);
        String[] keyTable = urlResult.split("/");
        if(keyTable.length == 2){
            UpdateDataDetail(new Date(), ExcuteString.GetUrlData(keyTable[0], keyTable[1], "addDate"));
        }

        return keyTable[keyTable.length - 1];
    }

    /**
     * Insert 1 list data with url and get key
     *
     * @param data
     * @param url
     */
    public static ArrayList<String> InsertData(List<?> data, String url) {
        ArrayList<String> listKey = new ArrayList<>();
        for (Object item : data) {
            listKey.add(InsertData(item, url));
        }

        return listKey;
    }

    /**
     * Insert 1 data with name object database
     *
     * @param data
     */
    public static String InsertData(Object data) {
        SetDateInsertData(data);
        return InsertData(data, GetNameDB(data));
    }

    /**
     * Insert 1 list data with name object database
     *
     * @param data
     */
    public static ArrayList<String> InsertData(List<?> data) {
        ArrayList<String> listKey = new ArrayList<>();
        for (Object item : data) {
            InsertData(item);
        }

        return listKey;
    }

    /**
     * Update 1 data with url
     *
     * @param data
     * @param url
     */
    public static void UpdateData(Object data, String url) {
        UpdateDataDetail(data, url);
        String[] keyTable = url.split("/");
        UpdateDataDetail(new Date(), ExcuteString.GetUrlData(keyTable[0], keyTable[1], "updDate"));
    }

    /**
     * Update 1 list data with url
     *
     * @param data
     * @param url
     */
    public static void UpdateData(List<?> data, String url) {
        for (Object item : data) {
            UpdateData(item, url);
        }
    }

    /**
     * Update 1 data with name object
     *
     * @param data
     */
    public static void UpdateData(Object data) {
        UpdateData(data, GetReferenceDB(data));
    }

    /**
     * Update 1 list data with name object
     *
     * @param data
     */
    public static void UpdateData(List<?> data) {
        for (Object item : data) {
            UpdateData(item);
        }
    }

    /**
     * Delete data with 1 url
     *
     * @param url
     */
    public static void DeleteData(String url) {
        String[] keyTable = url.split("/");
        if(keyTable.length > 2){
            UpdateDataDetail(new Date(), ExcuteString.GetUrlData(keyTable[0], keyTable[1], "updDate"));
        }
        reference.getReference(url).removeValue();
    }

    /**
     * Delete data with 1 url or 1 list url
     *
     * @param url
     */
    public static void DeleteData(String... url) {
        for (String item : url) {
            DeleteData(item);
        }
    }

    /**
     * Delete data with name object, get key from 1 data
     *
     * @param data
     */
    public static void DeleteData(Object data) {
        DeleteData(GetReferenceDB(data));
    }

    /**
     * Delete data with name object, get key from 1 list data
     *
     * @param data
     */
    public static void DeleteData(List<?> data) {
        for (Object item : data) {
            DeleteData(item);
        }
    }

    private static void UpdateDataDetail(Object data, String url) {
        reference.getReference(url).setValue(data);
    }

    private static String GetReferenceDB(Object data) {
        String key = (String) CallMethodObject(data, "getKey", new Class[]{}, new Object[]{});

        return ExcuteString.GetUrlData(GetNameDB(data), key);
    }

    private static void SetDateInsertData(Object data) {
        CallMethodVoid(data, "setAddDate", new Class[]{Date.class}, new Object[]{new Date()});
    }

    private static void SetDateUpdateData(Object data) {
        CallMethodVoid(data, "setUpdDate", new Class[]{Date.class}, new Object[]{new Date()});
    }

    private static Object CallMethodObject(Object data, String methoud, Class[] typeClass, Object[] param) {
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

    private static void CallMethodVoid(Object data, String methoud, Class[] typeClass, Object[] param) {
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

    private static String GetNameDB(Object data) {
        return data.getClass().getSimpleName();
    }

    private static Query equalTo(Query data, FAHQueryParam query) {
        switch (query.getTypeParam()) {
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

    private static Query startAt(Query data, FAHQueryParam query) {
        switch (query.getTypeParam()) {
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

    private static Query endAt(Query data, FAHQueryParam query) {
        switch (query.getTypeParam()) {
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

    private static Query between(Query data, FAHQueryParam query) {
        switch (query.getTypeParam()) {
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
}
