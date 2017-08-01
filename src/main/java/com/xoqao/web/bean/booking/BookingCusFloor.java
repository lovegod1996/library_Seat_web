package com.xoqao.web.bean.booking;

import java.awt.print.Book;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/30.
 * Email:dx96_j@163.com
 */
public class BookingCusFloor extends BookingSeat {

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
}
