package com.xoqao.web.bean.weekopen;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/25.
 * Email:dx96_j@163.com
 */
public class WeekOpenCus extends WeekOpen {
    private String floor;
    private String building;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "WeekOpenCus{" +
                "floor='" + floor + '\'' +
                ", building='" + building + '\'' +
                "} " + super.toString();
    }
}
