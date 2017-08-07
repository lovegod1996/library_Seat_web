package com.xoqao.web.bean.seat;

import java.util.Date;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/5.
 * Email:dx96_j@163.com
 */
public class SeatCusBookTime extends Seat {
    private Date stime;
    private Date etime;
    private String building;
    private String floorTheme;
    private Integer floor;

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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloorTheme() {
        return floorTheme;
    }

    public void setFloorTheme(String floorTheme) {
        this.floorTheme = floorTheme;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
