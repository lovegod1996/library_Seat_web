package com.xoqao.web.bean.weekopen;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/25.
 * Email:dx96_j@163.com
 */
public class WeekOpenCus extends WeekOpen implements Comparable<WeekOpenCus>{
    private String floor;
    private String building;
    private Integer allSeat;
    private Integer userPro;
    private Integer fl;
    private Integer women;

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

    public Integer getAllSeat() {
        return allSeat;
    }

    public void setAllSeat(Integer allSeat) {
        this.allSeat = allSeat;
    }


    public Integer getFl() {
        return fl;
    }

    public void setFl(Integer fl) {
        this.fl = fl;
    }

    public Integer getUserPro() {
        return userPro;
    }

    public void setUserPro(Integer userPro) {
        this.userPro = userPro;
    }

    public Integer getWomen() {
        return women;
    }

    public void setWomen(Integer women) {
        this.women = women;
    }

    @Override
    public String toString() {
        return "WeekOpenCus{" +
                "floor='" + floor + '\'' +
                ", building='" + building + '\'' +
                ", allSeat=" + allSeat +
                ", userPro=" + userPro +
                ", fl=" + fl +
                ", women=" + women +
                "} " + super.toString();
    }

    public int compareTo(WeekOpenCus o) {
        return (int) (o.userPro-this.userPro);
    }
}
