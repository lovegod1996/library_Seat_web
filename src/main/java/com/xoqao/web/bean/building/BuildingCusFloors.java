package com.xoqao.web.bean.building;

import com.xoqao.web.bean.floors.Floor;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/28.
 * Email:dx96_j@163.com
 */
public class BuildingCusFloors extends Building {

    private List<Floor> floors;

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

}
