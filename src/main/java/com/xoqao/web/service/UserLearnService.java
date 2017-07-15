package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
public interface UserLearnService {

    List<UserLearn> findfloorBookSeat( String floor)throws Exception;

    List<UserLearn> findfloorBookSeatPage( String floor,Integer startRow  ,Integer pageSize)throws Exception;

    List<UserLearn> findfloorAllSeat(String floor)throws Exception;

    List<UserLearn> findfloorAllSeatPage( String floor, Integer startRow  ,Integer pageSize)throws Exception;

    List<Seat> findAllNoSeat( String floor)throws Exception;

    List<Seat> findAllNoSeatPage( String floor, Integer startRow  ,Integer pageSize)throws Exception;

    void insertBook(Integer userid,Integer seatid, Date date, String period)throws Exception;

    /**
     * 删除预约
     * @param bid
     * @throws Exception
     */
    void deleteBook( Integer bid) throws Exception;

    /**
     * 查找预约id
     * @param bid
     * @return
     * @throws Exception
     */
    UserLearn findBookByid( Integer bid) throws Exception;
    /**
     * 更新失信情况
     * @param bid
     * @throws Exception
     */
    void updateUnpromise( Integer bid)throws Exception;

    List<UserLearn> findUserLearnByUid( Integer uid)throws Exception;

    List<UserLearn> findUserLearnByUidPage( Integer uid, Integer startRow, Integer pageSize)throws Exception;

    List<UserLearn> findUserLearnPerByUid( Integer uid)throws Exception;

    List<UserLearn> findUserLearnPerByUidPage( Integer uid,  Integer startRow,  Integer pageSize)throws Exception;

    /**
     * 查找用户最新预约
     * @param uid
     * @return
     * @throws Exception
     */
    UserLearn findUserLearnNew(Integer uid)throws Exception;
}
