package com.xoqao.web.bean.building;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public class Building {
    private Integer bid;
    private String accountnumber;
    private String password;
    private String name;
    private String employer;
    private String manage;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    @Override
    public String toString() {
        return "Building{" +
                "bid=" + bid +
                ", accountnumber='" + accountnumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", employer='" + employer + '\'' +
                ", manage='" + manage + '\'' +
                '}';
    }
}
