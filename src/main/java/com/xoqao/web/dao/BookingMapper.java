package com.xoqao.web.dao;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/25.
 * Email:dx96_j@163.com
 */
@Service
public interface BookingMapper {

    /**
     * 查询当天未预约的座位
     *
     * @param fid
     * @return
     * @throws Exception
     */
    List<Seat> findCanBookingToday(@Param("fid") Integer fid) throws Exception;

    List<Seat> findCanBookingTodayPage(@Param("fid") Integer fid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 查询当前已经入座的座位
     * @param fid
     * @return
     * @throws Exception
     */
    List<BookingSeat> findSeatInSeat(@Param("fid") Integer fid)throws Exception;
    List<BookingSeat> findSeatInSeatPage(@Param("fid") Integer fid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize)throws Exception;


    /**
     * 查询当天右预约的座位
     * @param fid
     * @return
     * @throws Exception
     */
    List<Seat> findBookSeat(@Param("fid") Integer fid)throws Exception;

    List<Seat> findBookSeatpage(@Param("fid") Integer fid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize)throws Exception;
    /**
     * 根据座位id查询预约情况
     * @param sid
     * @return
     * @throws Exception
     */
    List<Booking> findBookSeatBooking(@Param("sid") Integer sid)throws Exception;

}
