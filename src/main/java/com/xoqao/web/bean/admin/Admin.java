package com.xoqao.web.bean.admin;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
public class Admin {
    private Integer aid;
    private String acountnumber;
    private String password;
    private String name;
    private String employer;
    private Integer admin;
    private Integer notice;
    private Integer floor;
    private Integer user;
    private Integer seat;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAcountnumber() {
        return acountnumber;
    }

    public void setAcountnumber(String acountnumber) {
        this.acountnumber = acountnumber;
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

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getNotice() {
        return notice;
    }

    public void setNotice(Integer notice) {
        this.notice = notice;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", acountnumber='" + acountnumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", employer='" + employer + '\'' +
                ", admin=" + admin +
                ", notice=" + notice +
                ", floor=" + floor +
                ", user=" + user +
                ", seat=" + seat +
                '}';
    }
}
