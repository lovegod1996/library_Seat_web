package com.xoqao.web.service;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.dao.BuildingMapper;
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
public class BulidingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    public Building findBuildAdminByCount(String loginstr) throws Exception {
        Building buildAdminByCount = buildingMapper.findBuildAdminByCount(loginstr);
        return buildAdminByCount;
    }

    public Building findBuildingById(Integer bid) throws Exception {
        Building buildingById = buildingMapper.findBuildingById(bid);
        return buildingById;
    }

    public List<Building> findAllBuilding() throws Exception {
        List<Building> allBuilding = buildingMapper.findAllBuilding();
        return allBuilding;
    }

    public void insertLibaray(Building building) throws Exception {
        buildingMapper.insertLibaray(building);
    }

    public void deleteLibaray(Integer bid) throws Exception {
        buildingMapper.deleteLibaray(bid);
    }

    public void updateBuilding(Building building) throws Exception {
        buildingMapper.updateBuilding(building);
    }

    public void updatePassword(String password, Integer bid) throws Exception {
        buildingMapper.updatePassword(password, bid);
    }
}
