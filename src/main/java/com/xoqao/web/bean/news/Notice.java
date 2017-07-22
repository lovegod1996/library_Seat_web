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
    private String title;
    private String content;
    private Date creattime;
    private Integer type;
    private Integer uid;
    private String workstation;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creattime=" + creattime +
                ", type=" + type +
                ", uid=" + uid +
                ", workstation='" + workstation + '\'' +
                '}';
    }
}
