package com.xoqao.web.dao;

import com.xoqao.web.bean.seat.Seat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
@Service
public interface SeatMapper {

    Seat findSeatByNum(@Param("number") String number) throws Exception;

   void updateSeat(@Param("state") Integer state,@Param("userid")Integer userid,@Param("sid")Integer sid)throws Exception;

    /**
     * 根据楼区查找楼层
     * @param floor
     * @return
     * @throws Exception
     */
   List<String> findFloor(@Param("floor") String floor)throws Exception;

    /**
     * 根据楼层和状态查找数量
     * @param floor
     * @param state
     * @return
     * @throws Exception
     */
   Integer findCountBystate(@Param("floor") String floor,@Param("state") Integer state)throws Exception;
}
