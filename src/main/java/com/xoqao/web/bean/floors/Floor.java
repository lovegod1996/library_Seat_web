package com.xoqao.web.bean.floors;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public class Floor {
    private Integer fid;
    private Integer bid;
    private String accountnumber;
    private String password;
    private String name;
    private String employer;
    private String floor;
    private Integer statue;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "fid=" + fid +
                ", bid=" + bid +
                ", accountnumber='" + accountnumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", employer='" + employer + '\'' +
                ", floor='" + floor + '\'' +
                ", statue=" + statue +
                '}';
    }
}
