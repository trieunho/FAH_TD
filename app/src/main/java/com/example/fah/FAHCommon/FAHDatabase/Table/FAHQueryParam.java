package com.example.fah.FAHCommon.FAHDatabase.Table;

public class FAHQueryParam {
    private String table;
    private String field;
    private String typeQuery;
    private Object param;
    private Object param2;
    private String typeParam;

    public static final String EQUAL = "==";
    public static final String START = ">=";
    public static final String END = "<=";
    public static final String BETWEEN = "<<";
    public static final String ALIKE = "A%";
    public static final String LIKEA = "%A";
    public static final String TypeInteger = "Integer";
    public static final String TypeString = "String";
    public static final String TypeDouble = "Double";
    public static final String TypeBoolean = "Boolean";

    public FAHQueryParam(String table, String field, String typeQuery, Object param, String typeParam) {
        this.table = table;  // Tên table hoặc url cần query
        this.field = field;  // Tên field , có thể dùng parentField/childField để query thằng con
        this.typeQuery = typeQuery; // Có 4 cái ở trên đó EQUAL, START, .. không có LIKE
        this.param = param;   // Giá trị, BETWEEN thì dùng 2 param phía dưới
        this.typeParam = typeParam;  // Loại dữ liệu TypeString, TypeDouble, TypeBoolean
    }

    public FAHQueryParam(String table, String field, String typeQuery, Object param, Object param2, String typeParam) {
        this.table = table;
        this.field = field;
        this.typeQuery = typeQuery;
        this.param = param;
        this.param2 = param2;
        this.typeParam = typeParam;
    }

    public String getTable() {
        return table;
    }

    public String getField() {
        return field;
    }

    public String getTypeQuery() {
        return typeQuery;
    }

    public Object getParam() {
        return param;
    }

    public Object getParam2() {
        return param2;
    }

    public String getTypeParam() {
        return typeParam;
    }
}
