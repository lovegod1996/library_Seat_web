package com.xoqao.web.dao;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
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
     *
     * @param floor
     * @return
     * @throws Exception
     */
    List<UserLearn> findfloorBookSeat(@Param("floor") String floor) throws Exception;

    List<UserLearn> findfloorBookSeatPage(@Param("floor") String floor, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 查询入座座位
     *
     * @param floor
     * @return
     * @throws Exception
     */
    List<UserLearn> findfloorAllSeat(@Param("floor") String floor) throws Exception;

    List<UserLearn> findfloorAllSeatPage(@Param("floor") String floor, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 查询空的座位
     *
     * @param floor
     * @return
     * @throws Exception
     */
    List<Seat> findAllNoSeat(@Param("floor") String floor) throws Exception;

    List<Seat> findAllNoSeatPage(@Param("floor") String floor, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 添加预约
     *
     * @param userid
     * @param seatid
     * @param date
     * @param period
     * @throws Exception
     */
    void insertBook(@Param("userid") Integer userid, @Param("seatid") Integer seatid, @Param("date") Date date, @Param("period") String period) throws Exception;

    /**
     * 删除预约
     * @param bid
     * @throws Exception
     */
    void deleteBookByid(@Param("bid") Integer bid) throws Exception;

    /**
     * 查找预约id
     * @param bid
     * @return
     * @throws Exception
     */
    UserLearn findBookByid(@Param("bid") Integer bid) throws Exception;

    /**
     * 更新失信情况
     * @param bid
     * @throws Exception
     */
    void updateUnpromise(@Param("bid") Integer bid)throws Exception;

    /**
     * 查找学习记录
     * @param uid
     * @return
     * @throws Exception
     */
    List<UserLearn> findUserLearnByUid(@Param("uid") Integer uid)throws Exception;

    List<UserLearn> findUserLearnByUidPage(@Param("uid") Integer uid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize)throws Exception;

    /**
     * 查找微信失信记录
     * @param uid
     * @return
     * @throws Exception
     */
    List<UserLearn> findUserLearnPerByUid(@Param("uid") Integer uid)throws Exception;

    List<UserLearn> findUserLearnPerByUidPage(@Param("uid") Integer uid, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize)throws Exception;

}
