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

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", acountnumber='" + acountnumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", employer='" + employer + '\'' +
                '}';
    }
}
