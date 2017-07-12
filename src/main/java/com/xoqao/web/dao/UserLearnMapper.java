package com.xoqao.web.dao;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
@Service
public interface UserLearnMapper {
    /**
     * 查询预约的座位
     * @param floor
     * @return
     * @throws Exception
     */
    List<UserLearn> findfloorBookSeat(@Param("floor") String floor)throws Exception;

    List<UserLearn> findfloorBookSeatPage(@Param("floor") String floor,@Param("startRow") Integer startRow  ,@Param("pageSize")Integer pageSize)throws Exception;

    /**
     * 查询入座座位
     * @param floor
     * @return
     * @throws Exception
     */
    List<UserLearn> findfloorAllSeat(@Param("floor") String floor)throws Exception;

    List<UserLearn> findfloorAllSeatPage(@Param("floor") String floor,@Param("startRow") Integer startRow  ,@Param("pageSize")Integer pageSize)throws Exception;

    /**
     * 查询空的座位
     * @param floor
     * @return
     * @throws Exception
     */
    List<Seat> findAllNoSeat(@Param("floor") String floor)throws Exception;

    List<Seat> findAllNoSeatPage(@Param("floor") String floor,@Param("startRow") Integer startRow  ,@Param("pageSize")Integer pageSize)throws Exception;

}
