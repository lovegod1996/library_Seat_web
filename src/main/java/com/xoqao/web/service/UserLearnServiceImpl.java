package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.dao.UserLearnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void insertBook(Integer userid, Integer seatid, Date date, String period) throws Exception {
        userLearnMapper.insertBook(userid, seatid, date, period);
    }

    public void deleteBook(Integer bid) throws Exception {
        userLearnMapper.deleteBookByid(bid);
    }

    public UserLearn findBookByid(Integer bid) throws Exception {
        UserLearn bookByid = userLearnMapper.findBookByid(bid);
        return bookByid;
    }

    public void updateUnpromise(Integer bid) throws Exception {
        userLearnMapper.updateUnpromise(bid);
    }

    public List<UserLearn> findUserLearnByUid(Integer uid) throws Exception {
        List<UserLearn> userLearnByUid = userLearnMapper.findUserLearnByUid(uid);
        return userLearnByUid;
    }

    public List<UserLearn> findUserLearnByUidPage(Integer uid, Integer startRow, Integer pageSize) throws Exception {
        List<UserLearn> userLearnByUidPage = userLearnMapper.findUserLearnByUidPage(uid, startRow, pageSize);
        return userLearnByUidPage;
    }

    public List<UserLearn> findUserLearnPerByUid(Integer uid) throws Exception {
        List<UserLearn> userLearnPerByUid = userLearnMapper.findUserLearnPerByUid(uid);
        return userLearnPerByUid;
    }

    public List<UserLearn> findUserLearnPerByUidPage(Integer uid, Integer startRow, Integer pageSize) throws Exception {
        List<UserLearn> userLearnPerByUidPage = userLearnMapper.findUserLearnPerByUidPage(uid, startRow, pageSize);
        return userLearnPerByUidPage;
    }
}
