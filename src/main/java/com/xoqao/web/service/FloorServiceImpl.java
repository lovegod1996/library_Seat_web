package com.xoqao.web.service;

import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.dao.FloorsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
