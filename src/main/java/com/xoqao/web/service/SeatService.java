package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
public interface SeatService {
    void insertSeat( Seat seat) throws Exception;

    List<Seat> findSeatsByFid(Integer fid) throws Exception;

    List<Seat> findSeatsByFidPage(Integer fid,Integer startRow, Integer pageSize) throws Exception;

    void updateSeatSatue(Integer statue,  Integer sid) throws Exception;

    Seat findByid(Integer sid)throws Exception;

    void deleteSeat( Integer sid) throws Exception;

    Seat findSeatBynumber(String seatnumber)throws Exception;

    List<Seat> findOpenSeatsByFid( Integer fid) throws Exception;
}
