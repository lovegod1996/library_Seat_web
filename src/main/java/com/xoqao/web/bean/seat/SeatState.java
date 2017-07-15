package com.xoqao.web.bean.seat;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/15.
 * Email:dx96_j@163.com
 */
public class SeatState {
    private String floor;
    private Integer nobook;
    private Integer bookNum;
    private Integer seatedNum;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getNobook() {
        return nobook;
    }

    public void setNobook(Integer nobook) {
        this.nobook = nobook;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Integer getSeatedNum() {
        return seatedNum;
    }

    public void setSeatedNum(Integer seatedNum) {
        this.seatedNum = seatedNum;
    }

}
