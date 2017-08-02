package com.xoqao.web.bean.data;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/2.
 * Email:dx96_j@163.com
 */
public class UserData extends WeekData {
    private String username;
    private String sno;
    private Integer sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
