package com.xoqao.web.bean.data;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/4.
 * Email:dx96_j@163.com
 */
public class FloorData {

    private String floor;
    private Integer seatCount;
    private Integer hasBook;
    private Integer noBook;
    private Double upWeekUsePro;
    private Double man;
    private Integer mybook;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getHasBook() {
        return hasBook;
    }

    public void setHasBook(Integer hasBook) {
        this.hasBook = hasBook;
    }

    public Integer getNoBook() {
        return noBook;
    }

    public void setNoBook(Integer noBook) {
        this.noBook = noBook;
    }

    public Double getUpWeekUsePro() {
        return upWeekUsePro;
    }

    public void setUpWeekUsePro(Double upWeekUsePro) {
        this.upWeekUsePro = upWeekUsePro;
    }

    public Double getMan() {
        return man;
    }

    public void setMan(Double man) {
        this.man = man;
    }

    public Integer getMybook() {
        return mybook;
    }

    public void setMybook(Integer mybook) {
        this.mybook = mybook;
    }
}
