package com.xoqao.web.bean.deal;

import java.util.Date;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/5.
 * Email:dx96_j@163.com
 */
public class UnDeal {
    private Integer did;
    private String sno;
    private Date record;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Date getRecord() {
        return record;
    }

    public void setRecord(Date record) {
        this.record = record;
    }
}
