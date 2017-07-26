package com.xoqao.web.service;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.dao.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/26.
 * Email:dx96_j@163.com
 */
@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingMapper bookingMapper;

    public List<Seat> findCanBookingToday(Integer fid) throws Exception {
        List<Seat> canBookingToday = bookingMapper.findCanBookingToday(fid);
        return canBookingToday;
    }

    public List<Seat> findCanBookingTodayPage(Integer fid, Integer startRow, Integer pageSize) throws Exception {
        List<Seat> canBookingTodayPage = bookingMapper.findCanBookingTodayPage(fid, startRow, pageSize);
        return canBookingTodayPage;
    }

    public List<BookingSeat> findSeatInSeat(Integer fid) throws Exception {
        List<BookingSeat> seatInSeat = bookingMapper.findSeatInSeat(fid);
        return seatInSeat;
    }

    public List<BookingSeat> findSeatInSeatPage(Integer fid, Integer startRow, Integer pageSize) throws Exception {
        List<BookingSeat> seatInSeatPage = bookingMapper.findSeatInSeatPage(fid, startRow, pageSize);
        return seatInSeatPage;
    }

    public List<Seat> findBookSeat(Integer fid) throws Exception {
        List<Seat> bookSeat = bookingMapper.findBookSeat(fid);
        return bookSeat;
    }

    public List<Booking> findBookSeatBooking(Integer sid) throws Exception {
        List<Booking> bookSeatBooking = bookingMapper.findBookSeatBooking(sid);
        return bookSeatBooking;
    }

    public List<Seat> findBookSeatpage(Integer fid, Integer startRow, Integer pageSize) throws Exception {
        List<Seat> bookSeatpage = bookingMapper.findBookSeatpage(fid, startRow, pageSize);
        return bookSeatpage;
    }
}
