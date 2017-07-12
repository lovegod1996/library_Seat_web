package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.dao.UserLearnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
@Service
public class UserLearnServiceImpl implements UserLearnService {

    @Autowired
    UserLearnMapper userLearnMapper;

    public List<UserLearn> findfloorBookSeat(String floor) throws Exception {

        List<UserLearn> userLearns = userLearnMapper.findfloorBookSeat(floor);
        return userLearns;
    }

    public List<UserLearn> findfloorBookSeatPage(String floor, Integer startRow, Integer pageSize) throws Exception {
        List<UserLearn> userLearns = userLearnMapper.findfloorBookSeatPage(floor, startRow, pageSize);
        return userLearns;
    }

    public List<UserLearn> findfloorAllSeat(String floor) throws Exception {
        List<UserLearn> userLearns = userLearnMapper.findfloorAllSeat(floor);
        return userLearns;
    }

    public List<UserLearn> findfloorAllSeatPage(String floor, Integer startRow, Integer pageSize) throws Exception {
        List<UserLearn> userLearns = userLearnMapper.findfloorAllSeatPage(floor, startRow, pageSize);
        return userLearns;
    }

    public List<Seat> findAllNoSeat(String floor) throws Exception {
        List<Seat> allNoSeat = userLearnMapper.findAllNoSeat(floor);
        return allNoSeat;
    }

    public List<Seat> findAllNoSeatPage(String floor, Integer startRow, Integer pageSize) throws Exception {
        List<Seat> allNoSeatPage = userLearnMapper.findAllNoSeatPage(floor, startRow, pageSize);
        return allNoSeatPage;
    }
}
