package com.xoqao.web.bean.booking;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/7.
 * Email:dx96_j@163.com
 */
public class BookingUserCusFloor extends BookingUser {

    private Integer floorHigh;
    private String building;
    private Integer seatstatue;

    public Integer getFloorHigh() {
        return floorHigh;
    }

    public void setFloorHigh(Integer floorHigh) {
        this.floorHigh = floorHigh;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getSeatstatue() {
        return seatstatue;
    }

    public void setSeatstatue(Integer seatstatue) {
        this.seatstatue = seatstatue;
    }
}
