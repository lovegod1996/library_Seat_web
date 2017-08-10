package com.xoqao.web.dao;

import com.xoqao.web.bean.floors.Floor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
@Service
public interface FloorsMapper {

    Floor findFloorAdminByCount(@Param("loginstr") String acount) throws Exception;

    Floor findfloorByid(@Param("fid") Integer fid) throws Exception;

    List<Floor> findfloorsBybid(@Param("bid") Integer bid) throws Exception;

    void insertFloors(@Param("floor") Floor floor) throws Exception;

    void deletefloor(@Param("fid") Integer fid) throws Exception;

    void updateFloor(@Param("floor") Floor floor) throws Exception;

    void updateStatueByid(@Param("statue") Integer statue, @Param("fid") Integer fid) throws Exception;

    void updatePassword(@Param("password")String password, @Param("fid") Integer fid)throws Exception;
}
