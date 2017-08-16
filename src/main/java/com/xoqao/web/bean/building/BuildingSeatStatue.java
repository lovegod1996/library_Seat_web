package com.xoqao.web.bean.building;

import com.xoqao.web.bean.data.FloorData;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/16.
 * Email:dx96_j@163.com
 */
public class BuildingSeatStatue {

    private Integer bid;
    private String building;
    private List<FloorData> floorDataList;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<FloorData> getFloorDataList() {
        return floorDataList;
    }

    public void setFloorDataList(List<FloorData> floorDataList) {
        this.floorDataList = floorDataList;
    }
}
