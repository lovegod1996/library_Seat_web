package com.xoqao.web.bean.booking;

import java.util.Date;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public class Booking {

    private Integer bid;
    private Integer sid;
    private Integer statue;
    private Integer deal;
    private String sno;
    private Date bstime;
    private Date betime;
    private Date stime;
    private Date etime;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public Integer getDeal() {
        return deal;
    }

    public void setDeal(Integer deal) {
        this.deal = deal;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Date getBstime() {
        return bstime;
    }

    public void setBstime(Date bstime) {
        this.bstime = bstime;
    }

    public Date getBetime() {
        return betime;
    }

    public void setBetime(Date betime) {
        this.betime = betime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bid=" + bid +
                ", sid=" + sid +
                ", statue=" + statue +
                ", deal=" + deal +
                ", sno='" + sno + '\'' +
                ", bstime=" + bstime +
                ", betime=" + betime +
                ", stime=" + stime +
                ", etime=" + etime +
                '}';
    }
}
