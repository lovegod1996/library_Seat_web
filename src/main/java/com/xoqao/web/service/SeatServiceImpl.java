package com.xoqao.web.service;

import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.dao.SeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatMapper seatMapper;

    public Seat findSeatByNum(String number) throws Exception {
        Seat seatByNum = seatMapper.findSeatByNum(number);
        return seatByNum;
    }

    public void updateSeat(Integer state, Integer userid, Integer sid) throws Exception {
        seatMapper.updateSeat(state, userid, sid);
    }

    public List<String> findFloor(String floor) throws Exception {
        List<String> floor1 = seatMapper.findFloor(floor);
        return floor1;
    }

    public Integer findCountBystate(String floor, Integer state) throws Exception {
        Integer countBystate = seatMapper.findCountBystate(floor, state);
        return countBystate;
    }
}
