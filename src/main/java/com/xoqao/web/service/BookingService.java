package com.xoqao.web.service;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
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

    List<Seat> findCanBookingToday(Integer fid) throws Exception;

    List<Seat> findCanBookingTodayPage(Integer fid, Integer startRow, Integer pageSize) throws Exception;

    List<BookingSeat> findSeatInSeat(Integer fid) throws Exception;

    List<BookingSeat> findSeatInSeatPage(Integer fid, Integer startRow, Integer pageSize) throws Exception;

    List<Seat> findBookSeat(Integer fid) throws Exception;

    List<Booking> findBookSeatBooking(Integer sid) throws Exception;

    List<Seat> findBookSeatpage(Integer fid, Integer startRow, Integer pageSize) throws Exception;
}
