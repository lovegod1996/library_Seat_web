package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/26.
 * Email:dx96_j@163.com
 */
public interface BookingService {

    List<Seat> findCanBookingToday(Integer fid)throws Exception;

    List<Seat> findCanBookingTodayPage( Integer fid,  Integer startRow,  Integer pageSize) throws Exception;

}
