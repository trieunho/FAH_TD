package com.example.fah.FAHModel.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

import java.util.ArrayList;
import java.util.Date;

public class Post extends FAHFieldCommon implements Comparable<Post> {
    private String titlePost;
    private String accountStr;
    private String companyName;
    private String address;
    private String deadLine;
    private String jobDescription;
    private String required;
    private String benifit;
    private String soLuong;
    private String email;
    private String phone;
    private int typeOfSalary;
    private String salary_from;
    private String salary_to;
    private Date approveDate;
    private Date dueDate;
    private int dtFrom;
    private int dtTo;

    private ArrayList<Account> listOfAccApply;
    private ArrayList<String> listAccount;
    private String keyAccount;
    private TypeOfPost typeOfPost;
    private Category category;
    private int status;

    public Post(){}

    public Post(String titlePost, String accountStr, String companyName, String accApply) {
        this.titlePost = titlePost;
        this.accountStr = accountStr;
        this.companyName = companyName;
        this.listAccount.add(accApply);
    }

    public Post(String titlePost, String accountStr, String companyName) {
        this.titlePost = titlePost;
        this.accountStr = accountStr;
        this.companyName = companyName;
    }

    public Post(
            String titlePost,
            String companyName,
            String address,
            int dtFrom,
            int dtTo,
            int typeOfSalary,
            String salary_from,
            String salary_to,
            String deadLine) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.address = address;
        this.dtFrom = dtFrom;
        this.dtTo = dtTo;
        this.setTypeOfSalary(typeOfSalary);
        this.setSalary_from(salary_from);
        this.setSalary_to(salary_to);
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
                int typeOfSalary,
                String salary_from,
                String salary_to,
                String email,
                String phone,
                TypeOfPost typeOfPost,
                String keyAccount) {
        this.setTitlePost(titlePost);
        this.setCompanyName(companyName);
        this.setCategory(category);
        this.setJobDescription(jobDescription);
        this.setAddress(address);
        this.setRequired(required);
        this.setBenifit(benifit);
        this.setSoLuong(soLuong);
        this.setDeadLine(deadLine);
        this.setDtFrom(dtFrom);
        this.setDtTo(dtTo);
        this.setTypeOfSalary(typeOfSalary);
        this.setSalary_from(salary_from);
        this.setSalary_to(salary_to);
        this.setEmail(email);
        this.setPhone(phone);
        this.setTypeOfPost(typeOfPost);
        this.setKeyAccount(keyAccount);
    }

    public int compareTo(Post post) {
        if (this.getTypeOfPost().getTypeID().equals(post.getTypeOfPost().getTypeID()))
            return 0;
        else if (Integer.parseInt(this.getTypeOfPost().getTypeID()) < Integer.parseInt(post.getTypeOfPost().getTypeID()))
            return -1;
        else
            return 1;
    }

    public ArrayList<String> getListAccount() {
        return listAccount;
    }

    public void setListAccount(ArrayList<String> listAccount) {
        this.listAccount = listAccount;
    }

    public String getKeyAccount() {
        return keyAccount;
    }

    public void setKeyAccount(String keyAccount) {
        this.keyAccount = keyAccount;
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

    public int getTypeOfSalary() {
        return typeOfSalary;
    }

    public void setTypeOfSalary(int typeOfSalary) {
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

    public ArrayList<Account> getListOfAccApply() {
        return listOfAccApply;
    }

    public void setListOfAccApply(ArrayList<Account> listOfAccApply) {
        this.listOfAccApply = listOfAccApply;
    }
}
