package com.xoqao.web.bean.seat;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
public class Seat {
    private Integer sid;
    private String seatnumber;
    private Integer seatstate;
    private Integer userid;
    private String seattype;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }

    public Integer getSeatstate() {
        return seatstate;
    }

    public void setSeatstate(Integer seatstate) {
        this.seatstate = seatstate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSeattype() {
        return seattype;
    }

    public void setSeattype(String seattype) {
        this.seattype = seattype;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "sid=" + sid +
                ", seatnumber='" + seatnumber + '\'' +
                ", seatstate=" + seatstate +
                ", userid=" + userid +
                ", seattype='" + seattype + '\'' +
                '}';
    }
}
