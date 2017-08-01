package com.xoqao.web.bean.user;


import java.io.Serializable;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/5/9.
 */

public class User implements Serializable {
    private Integer uid;
    private String nick;
    private String sno;
    private String password;
    private String college;
    private String classes;
    private String phone;
    private String name;
    private String major;
    private Integer sex;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", nick='" + nick + '\'' +
                ", sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", college='" + college + '\'' +
                ", classes='" + classes + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", sex=" + sex +
                '}';
    }
}
