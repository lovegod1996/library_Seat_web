package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
public interface SeatService {
    Seat findSeatByNum(String number) throws Exception;

    void updateSeat(Integer state, Integer userid, Integer sid) throws Exception;

    /**
     * 根据楼区查找楼层
     * @param floor
     * @return
     * @throws Exception
     */
    List<String> findFloor( String floor)throws Exception;

    /**
     * 根据楼层和状态查找数量
     * @param floor
     * @param state
     * @return
     * @throws Exception
     */
    Integer findCountBystate(String floor,Integer state)throws Exception;
}
