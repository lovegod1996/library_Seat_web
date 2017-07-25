package com.xoqao.web.service;

import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.dao.FloorsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorsMapper floorsMapper;

    public Floor findFloorBycount(String count) throws Exception {
        Floor floorAdminByCount = floorsMapper.findFloorAdminByCount(count);
        return floorAdminByCount;
    }

    public Floor findfloorByid(Integer fid) throws Exception {
        Floor floor = floorsMapper.findfloorByid(fid);
        return floor;
    }

    public List<Floor> findfloorsBybid(Integer bid) throws Exception {

        List<Floor> floors = floorsMapper.findfloorsBybid(bid);
        return floors;
    }

    public void insertFloors(Floor floor) throws Exception {
        floorsMapper.insertFloors(floor);
    }

    public void deletefloor(Integer fid) throws Exception {
        floorsMapper.deletefloor(fid);
    }

    public void updateFloor(Floor floor) throws Exception {
        floorsMapper.updateFloor(floor);
    }

    public void updateStatueByid(Integer statue, Integer fid) throws Exception {
        floorsMapper.updateStatueByid(statue, fid);
    }


}
