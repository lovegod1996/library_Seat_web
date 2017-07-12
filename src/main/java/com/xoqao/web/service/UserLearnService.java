package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import org.apache.ibatis.annotations.Param;

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

}
