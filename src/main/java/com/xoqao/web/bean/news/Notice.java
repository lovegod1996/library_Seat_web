package com.xoqao.web.bean.news;

import java.util.Date;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public class Notice {
    private Integer nid;
    private String titile;
    private String content;
    private Date creattime;
    private Integer type;
    private Integer uid;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "nid=" + nid +
                ", titile='" + titile + '\'' +
                ", content='" + content + '\'' +
                ", creattime=" + creattime +
                ", type=" + type +
                ", uid=" + uid +
                '}';
    }
}
