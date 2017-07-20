package com.xoqao.web.dao;

import com.xoqao.web.bean.building.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
@Service
public interface BuildingMapper {

    Building findBuildAdminByCount(@Param("loginstr") String loginstr)throws Exception;
}
