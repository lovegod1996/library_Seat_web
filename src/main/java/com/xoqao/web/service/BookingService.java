package com.xoqao.web.service;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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


    /**
     * 添加预约
     *
     * @param booking
     * @throws Exception
     */
    void insertbooking( Booking booking) throws Exception;

    /**
     * 添加直接预约入座
     *
     * @param booking
     * @throws Exception
     */
    void insertbookingnow( Booking booking) throws Exception;

    /**
     * 更新入座位
     *
     * @param now
     * @param bid
     * @throws Exception
     */
    void updateStime( Date now,  Integer bid) throws Exception;

    /**
     * 更新离开座位
     *
     * @param etime  离开时间
     * @param statue 座位状态
     * @param deal   失信标记
     * @param bid    预约id
     * @throws Exception
     */
    void updateEtime( Date etime,  Integer statue, Integer delay,  Integer deal,  Integer bid) throws Exception;

    /**
     * 修改违约状态
     *
     * @param deal
     * @param bid
     * @throws Exception
     */
    void updateDeal( Integer deal, Integer  statue, Integer bid) throws Exception;

    /**
     * 根据学校和天数查看预约记录
     * @param sno
     * @param date
     * @return
     * @throws Exception
     */
    List<Booking> findBookingBySno( String sno, Integer date) throws Exception;

    Booking findByid( Integer bid)throws Exception;

    List<Booking> findBookSeatBookingday(Integer sid,Integer day)throws Exception;


    void deleteByid( Integer bid)throws Exception;

    /**
     * 根据学号查询预约记录
     * @param sno
     * @return
     * @throws Exception
     */
    List<Booking> finduserbook(String sno)throws Exception;

    List<Booking> finduserbookpage( String sno, Integer startRow, Integer pageSize)throws Exception;

    List<Booking> finduserbookpromise( String sno,  Integer deal) throws Exception;

    List<Booking> finduserbookpromisepage( String sno, Integer deal, Integer startRow, Integer pageSize) throws Exception;


    /**
     * 查询本年度有预约记录的所有周
     * @return
     * @throws Exception
     */
    List<Integer> findweekofbook() throws Exception;

    /**
     * 查询制定周的预约情况
     * @param fid
     * @param week
     * @return
     * @throws Exception
     */
    List<Booking>  findbookfloorofweek(Integer fid,Integer week)throws Exception;

    /**
     * 查询预约内所有月份
     * @return
     * @throws Exception
     */
    List<Integer> findmonthofbook()throws Exception;

    /**
     * 查询某层指定预约内的预约情况
     * @param fid
     * @param month
     * @return
     * @throws Exception
     */
    List<Booking> findbookfloorofmonth( Integer fid, Integer month)throws Exception;


    /**
     * 根据院系名称查询预约
     * @param college
     * @return
     * @throws Exception
     */
    List<Booking> findbookofCollege( String college)throws Exception;

    /**
     * 根据院系名称和专业名称查询预约
     * @param college
     * @param major
     * @return
     * @throws Exception
     */
    List<Booking> findbookwithCollegeAndMajor( String college,String major)throws Exception;

    /**
     * 根据院系名称专业名称班级查询预约
     * @param college
     * @param major
     * @param classes
     * @return
     * @throws Exception
     */
    List<Booking> findbookWithCollegeMajorClass( String college,String major, String classes)throws Exception;
}
