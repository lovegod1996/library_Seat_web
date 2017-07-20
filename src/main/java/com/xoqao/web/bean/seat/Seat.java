package com.xoqao.web.bean.seat;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
public class Seat {
    private Integer sid;
    private Integer fid;
    private Integer row;
    private Integer column;
    private String seatnumber;
    private Integer statue;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "sid=" + sid +
                ", fid=" + fid +
                ", row=" + row +
                ", column=" + column +
                ", seatnumber='" + seatnumber + '\'' +
                ", statue=" + statue +
                '}';
    }
}
