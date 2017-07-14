package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
public interface SeatService {
    Seat findSeatByNum(String number) throws Exception;

    void updateSeat(Integer state, Integer userid, Integer sid) throws Exception;
}
