package com.example.fah.FAHModel.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

import java.util.ArrayList;

public class Account extends FAHFieldCommon {
    private String accountID;
    private String accountName;
    private String sex;
    private String dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private int role;
    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private String companyEmail;
    private String companyIntro;
    private int coin;
    private int statusBlock;
    private   boolean isLogin;
    private String avata;

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private Category category;
    private ArrayList<Post> listPost;
    private ArrayList<Post> listFavoritePost;

    /**
     * Constructor with candidate
     * @param accountID
     * @param accountName
     * @param sex
     * @param dateOfBirth
     * @param address
     * @param phone
     * @param email
     * @param role
     * @param statusBlock
     * @param category
     */
    public Account(String accountID, String accountName, String sex,
                   String dateOfBirth, String address, String phone,
                   String email, int role, int statusBlock, Category category, String avata) {
        super();
        this.accountID = accountID;
        this.accountName = accountName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.statusBlock = statusBlock;
        this.category = category;
        this.avata = avata;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Constructor with Company
     * @param accountID
     * @param accountName
     * @param sex
     * @param dateOfBirth
     * @param address
     * @param phone
     * @param email
     * @param role
     * @param companyName
     * @param companyAddress
     * @param companyPhone
     * @param companyEmail
     * @param companyIntro
     * @param coin
     * @param statusBlock
     */
    public Account(String accountID, String accountName, String sex, String dateOfBirth, String address, String phone, String email, int role, String companyName, String companyAddress, String companyPhone, String companyEmail, String companyIntro, int coin, int statusBlock) {
        super();
        this.accountID = accountID;
        this.accountName = accountName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhone = companyPhone;
        this.companyEmail = companyEmail;
        this.companyIntro = companyIntro;
        this.coin = coin;
        this.statusBlock = statusBlock;
    }

    /**
     * Constructor with admin
     * @param accountID
     * @param accountName
     * @param email
     * @param role
     */
    public Account(String accountID, String accountName, String email, int role) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.email = email;
        this.role = role;
    }

    public Account() {
    }

    public Account(String accountName, String email, int statusBlock) {
        this.accountName = accountName;
        this.email = email;
        this.statusBlock = statusBlock;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getStatusBlock() {
        return statusBlock;
    }

    public void setStatusBlock(int statusBlock) {
        this.statusBlock = statusBlock;
    }

    public ArrayList<Post> getListPost() {
        return listPost;
    }

    public void setListPost(ArrayList<Post> listPost) {
        this.listPost = listPost;
    }

    public ArrayList<Post> getListFavoritePost() {
        return listFavoritePost;
    }

    public void setListFavoritePost(ArrayList<Post> listFavoritePost) {
        this.listFavoritePost = listFavoritePost;
    }
}
