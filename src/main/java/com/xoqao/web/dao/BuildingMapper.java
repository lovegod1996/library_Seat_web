package com.xoqao.web.dao;

import com.xoqao.web.bean.building.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
@Service
public interface BuildingMapper {

    Building findBuildAdminByCount(@Param("loginstr") String loginstr) throws Exception;

    Building findBuildingById(@Param("bid") Integer bid) throws Exception;

    List<Building> findAllBuilding() throws Exception;

    void insertLibaray(@Param("building") Building building) throws Exception;

    void deleteLibaray(@Param("bid") Integer bid) throws Exception;

    void updateBuilding(@Param("building") Building building) throws Exception;

    void updatePassword(@Param("password") String password, @Param("bid") Integer bid) throws Exception;
}
