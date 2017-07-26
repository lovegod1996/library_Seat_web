package com.xoqao.web.bean.booking;

import com.xoqao.web.bean.seat.Seat;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/26.
 * Email:dx96_j@163.com
 */
public class SeatBookings extends Seat {
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "SeatBookings{" +
                "bookings=" + bookings +
                "} " + super.toString();
    }
}
