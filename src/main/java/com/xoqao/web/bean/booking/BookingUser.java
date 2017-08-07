package com.xoqao.web.bean.booking;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/3.
 * Email:dx96_j@163.com
 */
public class BookingUser extends BookingSeat {
    private String name;
    private String floor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
