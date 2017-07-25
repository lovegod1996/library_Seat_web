package com.xoqao.web.dao;

import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
@Service
public interface SeatMapper {

    void insertSeat(@Param("seat") Seat seat) throws Exception;

    List<Seat> findSeatsByFid(@Param("fid") Integer fid) throws Exception;

    List<Seat> findSeatsByFidPage(@Param("fid") Integer fid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    void updateSeatSatue(@Param("statue") Integer statue, @Param("sid") Integer sid) throws Exception;

    Seat findByid(@Param("sid") Integer sid) throws Exception;

    void deleteSeat(@Param("sid") Integer sid) throws Exception;
}
