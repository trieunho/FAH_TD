package com.example.fah.FAHModel.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

import java.util.ArrayList;
import java.util.Date;

public class Post extends FAHFieldCommon {
    private String titlePost;
    private String accountStr;
    private String companyName;
    private String address;
    private String salary;
    private String deadLine;
    private String jobDescription;
    private String required;
    private String field;
    private String benifit;
    private String soLuong;
    private String email;
    private String phone;
    private String typeOfSalary;
    private String salary_from;
    private String salary_to;
    private Date approveDate;
    private Date dueDate;

    private ArrayList<Account> listOfAccApply;
    private Account account;
    private TypeOfPost typeOfPost;
    private String timeOfWork;
    private Category category;

    public Post(){}

    public Post(String titlePost, String accountStr, String companyName) {
        this.titlePost = titlePost;
        this.accountStr = accountStr;
        this.companyName = companyName;
    }

    public Post(String titlePost, String companyName, String address, String tow, String luong, String deadLine) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.address = address;
        this.timeOfWork = tow;
        this.salary = luong;
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
                String deadLine,
                String tow,
                String typeOfSalary,
                String salary_from,
                String salary_to,
                String email,
                String phone,
                TypeOfPost typeOfPost,
                Account account) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.field = field;
        this.jobDescription = jobDescription;
        this.address = address;
        this.required = required;
        this.benifit = benifit;
        this.soLuong = soLuong;
        this.deadLine = deadLine;
        this.timeOfWork = tow;
        this.typeOfSalary = typeOfSalary;
        this.salary_from = salary_from;
        this.salary_to = salary_to;
        this.email = email;
        this.phone = phone;
        this.setTypeOfPost(typeOfPost);
        this.account = account;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String luong) {
        this.salary = luong;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ArrayList<Account> getListOfAccApply() {
        return listOfAccApply;
    }

    public void setListOfAccApply(ArrayList<Account> listOfAccApply) {
        this.listOfAccApply = listOfAccApply;
    }

    public TypeOfPost getTypeOfPost() {
        return typeOfPost;
    }

    public void setTypeOfPost(TypeOfPost typeOfPost) {
        this.typeOfPost = typeOfPost;
    }

    public String getTimeOfWork() {
        return timeOfWork;
    }

    public void setTimeOfWork(String timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
