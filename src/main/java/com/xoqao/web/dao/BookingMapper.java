package com.xoqao.web.dao;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
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
     *
     * @param fid
     * @return
     * @throws Exception
     */
    List<BookingSeat> findSeatInSeat(@Param("fid") Integer fid) throws Exception;

    List<BookingSeat> findSeatInSeatPage(@Param("fid") Integer fid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;


    /**
     * 查询当天右预约的座位
     *
     * @param fid
     * @return
     * @throws Exception
     */
    List<Seat> findBookSeat(@Param("fid") Integer fid) throws Exception;

    List<Seat> findBookSeatpage(@Param("fid") Integer fid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 根据座位id查询预约情况
     *
     * @param sid
     * @return
     * @throws Exception
     */
    List<Booking> findBookSeatBooking(@Param("sid") Integer sid) throws Exception;

    List<Booking> findBookSeatBookingday(@Param("sid") Integer sid, @Param("day") Integer day) throws Exception;

    /**
     * 添加预约
     *
     * @param booking
     * @throws Exception
     */
    void insertbooking(@Param("booking") Booking booking) throws Exception;

    /**
     * 添加直接预约入座
     *
     * @param booking
     * @throws Exception
     */
    void insertbookingnow(@Param("booking") Booking booking) throws Exception;

    /**
     * 更新入座位
     *
     * @param now
     * @param bid
     * @throws Exception
     */
    void updateStime(@Param("stime") Date now, @Param("bid") Integer bid) throws Exception;

    /**
     * 更新离开座位
     *
     * @param etime  离开时间
     * @param statue 座位状态
     * @param deal   失信标记
     * @param bid    预约id
     * @throws Exception
     */
    void updateEtime(@Param("etime") Date etime, @Param("statue") Integer statue, @Param("delay") Integer delay, @Param("deal") Integer deal, @Param("bid") Integer bid) throws Exception;

    /**
     * 修改违约状态
     *
     * @param deal
     * @param bid
     * @throws Exception
     */
    void updateDeal(@Param("deal") Integer deal, @Param("statue") Integer statue, @Param("bid") Integer bid) throws Exception;

    /**
     * 根据学校和天数查看预约记录
     *
     * @param sno
     * @param date
     * @return
     * @throws Exception
     */
    List<Booking> findBookingBySno(@Param("sno") String sno, @Param("date") Integer date) throws Exception;

    /**
     * 根据bid查找预约
     *
     * @param bid
     * @return
     * @throws Exception
     */
    Booking findByid(@Param("bid") Integer bid) throws Exception;


    void deleteByid(@Param("bid") Integer bid) throws Exception;

    /**
     * 根据学号查询预约记录
     *
     * @param sno
     * @return
     * @throws Exception
     */
    List<Booking> finduserbook(@Param("sno") String sno) throws Exception;

    List<Booking> finduserbookpage(@Param("sno") String sno, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    List<Booking> finduserbookpromise(@Param("sno") String sno, @Param("deal") Integer deal) throws Exception;

    List<Booking> finduserbookpromisepage(@Param("sno") String sno, @Param("deal") Integer deal, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;
}
