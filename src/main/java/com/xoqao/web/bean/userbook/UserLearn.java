package com.xoqao.web.bean.userbook;

import javafx.scene.chart.PieChart;

import java.util.Date;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
public class UserLearn {
private Integer uid;
private Integer sid;
private Integer bid;

private String name;
private String college;
private String major;
private String classes;
private String sno;
private String nick;
private String phone;

private String seatnumber;
private Date date;
private Integer unpromise;
private String period;

private Date downtime;
private Date uptime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUnpromise() {
        return unpromise;
    }

    public void setUnpromise(Integer unpromise) {
        this.unpromise = unpromise;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDowntime() {
        return downtime;
    }

    public void setDowntime(Date downtime) {
        this.downtime = downtime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserLearn{" +
                "uid=" + uid +
                ", sid=" + sid +
                ", bid=" + bid +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", classes='" + classes + '\'' +
                ", sno='" + sno + '\'' +
                ", nick='" + nick + '\'' +
                ", phone='" + phone + '\'' +
                ", seatnumber='" + seatnumber + '\'' +
                ", date=" + date +
                ", unpromise=" + unpromise +
                ", period='" + period + '\'' +
                ", downtime=" + downtime +
                ", uptime=" + uptime +
                '}';
    }
}
