package com.xoqao.web.bean.booking;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/26.
 * Email:dx96_j@163.com
 */
public class BookingSeat extends Booking {
    private Integer fid;
    private Integer leftside;
    private Integer row;
    private Integer columns;
    private String seatnumber;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getLeftside() {
        return leftside;
    }

    public void setLeftside(Integer leftside) {
        this.leftside = leftside;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public String getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(String seatnumber) {
        this.seatnumber = seatnumber;
    }

    @Override
    public String toString() {
        return "BookingSeat{" +
                "fid=" + fid +
                ", leftside=" + leftside +
                ", row=" + row +
                ", columns=" + columns +
                ", seatnumber='" + seatnumber + '\'' +
                "} " + super.toString();
    }
}
