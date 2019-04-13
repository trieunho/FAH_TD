package com.example.fah.FAHExcuteData.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

import java.util.ArrayList;
import java.util.Date;

public class Post extends FAHFieldCommon {
    private String titlePost;
    private String accountStr;
    private String companyName;
    private String address;
    private String luong;
    private Date deadLine;
    private String jobDescription;
    private String required;
    private String field;
    private String benifit;
    private String soLuong;
    private String email;
    private String phone;
    private String workingTime;
    private String typeOfSalary;
    private String salary_from;
    private String salary_to;
    private Date approveDate;
    private Date dueDate;
    private int typeOfArticle;

    private ArrayList<Account> listOfAccApply;
    private Account account;
    private TypeOfPost typeOfPost;
    private TimeOfWork timeOfWork;
    private Category category;

    public Post(){}

    public Post(String titlePost, String accountStr, String companyName) {
        this.titlePost = titlePost;
        this.accountStr = accountStr;
        this.companyName = companyName;
    }

    public Post(String titlePost, String companyName, String address, String workingTime, String luong, Date deadLine) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.address = address;
        this.workingTime = workingTime;
        this.luong = luong;
        this.deadLine = deadLine;
    }

    public Post(String titlePost,
                String companyName,
                String field,
                String jobDescription,
                String required,
                String benifit,
                String soLuong,
                String address,
                Date deadLine,
                String workingTime,
                String typeOfSalary,
                String salary_from,
                String salary_to,
                String email,
                String phone,
                int typeOfArticle) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.field = field;
        this.jobDescription = jobDescription;
        this.address = address;
        this.required = required;
        this.benifit = benifit;
        this.soLuong = soLuong;
        this.deadLine = deadLine;
        this.workingTime = workingTime;
        this.typeOfSalary = typeOfSalary;
        this.salary_from = salary_from;
        this.salary_to = salary_to;
        this.email = email;
        this.phone = phone;
        this.typeOfArticle = typeOfArticle;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountStr() {
        return accountStr;
    }

    public void setAccountStr(String accountStr) {
        this.accountStr = accountStr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getAboutCompany() {
        return field;
    }

    public void setAboutCompany(String field) {
        this.field = field;
    }

    public String getBenifit() {
        return benifit;
    }

    public void setBenifit(String benifit) {
        this.benifit = benifit;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeOfSalary() {
        return typeOfSalary;
    }

    public void setTypeOfSalary(String typeOfSalary) {
        this.typeOfSalary = typeOfSalary;
    }

    public String getSalary_from() {
        return salary_from;
    }

    public void setSalary_from(String salary_from) {
        this.salary_from = salary_from;
    }

    public String getSalary_to() {
        return salary_to;
    }

    public void setSalary_to(String salary_to) {
        this.salary_to = salary_to;
    }

    public int getTypeOfArticle() {
        return typeOfArticle;
    }

    public void setTypeOfArticle(int getTypeOfArticle) {
        this.typeOfArticle = getTypeOfArticle;
    }
}
