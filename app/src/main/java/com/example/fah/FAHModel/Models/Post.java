package com.example.fah.FAHModel.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

import java.util.ArrayList;
import java.util.Date;

public class Post extends FAHFieldCommon implements Comparable<Post> {
    private String titlePost;
    private String accountStr;
    private String companyName;
    private String address;
    private String salary;
    private String deadLine;
    private String jobDescription;
    private String required;
    private String benifit;
    private String soLuong;
    private String email;
    private String phone;
    private String typeOfSalary;
    private String salary_from;
    private String salary_to;
    private Date approveDate;
    private Date dueDate;
    private int dtFrom;
    private int dtTo;

    private ArrayList<Account> listOfAccApply;
    private Account account;
    private TypeOfPost typeOfPost;
    private Category category;
    private int status;

    public Post(){}

    public Post(String titlePost, String accountStr, String companyName,ArrayList <Account> listOfAccApply) {
        this.titlePost = titlePost;
        this.accountStr = accountStr;
        this.companyName = companyName;
        this.listOfAccApply = listOfAccApply;
    }

    public Post(String titlePost, String accountStr, String companyName) {
        this.titlePost = titlePost;
        this.accountStr = accountStr;
        this.companyName = companyName;
    }

    public Post(String titlePost, String companyName, String address, int dtFrom, int dtTo, String luong, String deadLine) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.address = address;
        this.dtFrom = dtFrom;
        this.dtTo = dtTo;
        this.salary = luong;
        this.deadLine = deadLine;
    }

    public Post(String titlePost,
                String companyName,
                Category category,
                String jobDescription,
                String required,
                String benifit,
                String soLuong,
                String address,
                String deadLine,
                int dtFrom,
                int dtTo,
                String typeOfSalary,
                String salary_from,
                String salary_to,
                String email,
                String phone,
                TypeOfPost typeOfPost,
                Account account) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.setCategory(category);
        this.jobDescription = jobDescription;
        this.address = address;
        this.required = required;
        this.benifit = benifit;
        this.soLuong = soLuong;
        this.deadLine = deadLine;
        this.dtFrom = dtFrom;
        this.dtTo = dtTo;
        this.typeOfSalary = typeOfSalary;
        this.salary_from = salary_from;
        this.salary_to = salary_to;
        this.email = email;
        this.phone = phone;
        this.setTypeOfPost(typeOfPost);
        this.account = account;
    }

    public int compareTo(Post post) {
        if (this.getTypeOfPost().getTypeID().equals(post.getTypeOfPost().getTypeID()))
            return 0;
        else if (Integer.parseInt(this.getTypeOfPost().getTypeID()) < Integer.parseInt(post.getTypeOfPost().getTypeID()))
            return 1;
        else
            return -1;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDtFrom() {
        return dtFrom;
    }

    public void setDtFrom(int dtFrom) {
        this.dtFrom = dtFrom;
    }

    public int getDtTo() {
        return dtTo;
    }

    public void setDtTo(int dtTo) {
        this.dtTo = dtTo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
