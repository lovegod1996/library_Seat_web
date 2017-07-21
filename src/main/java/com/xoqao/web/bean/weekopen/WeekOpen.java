package com.xoqao.web.bean.weekopen;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public class WeekOpen {

    private Integer woid;
    private Integer lid;
    private String param1;
    private String param2;
    private Integer week;
    private Integer statue;

    public Integer getWoid() {
        return woid;
    }

    public void setWoid(Integer woid) {
        this.woid = woid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    @Override
    public String toString() {
        return "WeekOpen{" +
                "woid=" + woid +
                ", lid=" + lid +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", week=" + week +
                ", statue=" + statue +
                '}';
    }
}
