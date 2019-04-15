package com.example.fah.FAHCommon.FAHDatabase.Table;

public class FAHQueryParam {
    public static final String EQUAL = "=";
    public static final String START = ">=";
    public static final String END = "<=";
    public static final String BETWEEN = "X < value < Y";
    public static final String ALIKE = "A%";
    public static final String LIKEA = "%A";
    public static final String TypeString = "String";
    public static final String TypeDouble = "Double";
    public static final String TypeBoolean = "Boolean";
    private String table;
    private String field;
    private String typeQuery;
    private Object param;
    private Object param2;
    private String typeParam;

    public FAHQueryParam(String table, String field, String typeQuery, Object param, String typeParam) {
        this.table = table;
        this.field = field;
        this.typeQuery = typeQuery;
        this.param = param;
        this.typeParam = typeParam;
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
