package com.xoqao.web.service;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.dao.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
