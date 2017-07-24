package com.xoqao.web.service;

import com.xoqao.web.bean.floors.Floor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public interface FloorService {
    Floor findFloorBycount(String count)throws Exception;

    Floor findfloorByid( Integer fid)throws Exception;


    List<Floor> findfloorsBybid(Integer bid)throws Exception;

    void insertFloors(Floor floor)throws Exception;

    void deletefloor( Integer fid)throws Exception;
}
