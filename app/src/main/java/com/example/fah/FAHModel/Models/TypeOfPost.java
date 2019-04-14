package com.example.fah.FAHModel.Models;

public class TypeOfPost {
    private String typeID;
    private String typeName;
    private String typeCoin;
    private String typeTime;

    public TypeOfPost() {
    }

    public TypeOfPost(String typeID, String typeName, String typeCoin, String typeTime) {
        this.typeID = typeID;
        this.typeName = typeName;
        this.typeCoin = typeCoin;
        this.typeTime = typeTime;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCoin() {
        return typeCoin;
    }

    public void setTypeCoin(String typeCoin) {
        this.typeCoin = typeCoin;
    }

    public String getTypeTime() {
        return typeTime;
    }

    public void setTypeTime(String typeTime) {
        this.typeTime = typeTime;
    }
}
