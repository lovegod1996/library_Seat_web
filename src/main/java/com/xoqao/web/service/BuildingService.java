package com.xoqao.web.service;

import com.xoqao.web.bean.building.Building;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public interface BuildingService {

    Building findBuildAdminByCount( String loginstr)throws Exception;

    Building findBuildingById( Integer bid)throws Exception;

    List<Building> findAllBuilding()throws Exception;


    void insertLibaray(Building building) throws Exception;

    void deleteLibaray(Integer bid) throws Exception;
}
