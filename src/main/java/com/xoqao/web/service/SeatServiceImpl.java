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


    public void insertSeat(Seat seat) throws Exception {
        seatMapper.insertSeat(seat);
    }

    public List<Seat> findSeatsByFid(Integer fid) throws Exception {
        List<Seat> seatsByFid = seatMapper.findSeatsByFid(fid);
        return seatsByFid;
    }

    public List<Seat> findSeatsByFidPage(Integer fid, Integer startRow, Integer pageSize) throws Exception {
        List<Seat> seatsByFidPage = seatMapper.findSeatsByFidPage(fid, startRow, pageSize);
        return seatsByFidPage;
    }

    public void updateSeatSatue(Integer statue, Integer sid) throws Exception {
        seatMapper.updateSeatSatue(statue, sid);
    }

    public Seat findByid(Integer sid) throws Exception {
        Seat byid = seatMapper.findByid(sid);
        return byid;
    }

    public void deleteSeat(Integer sid) throws Exception {
        seatMapper.deleteSeat(sid);
    }

    public Seat findSeatBynumber(String seatnumber) throws Exception {
        Seat seatBynumber = seatMapper.findSeatBynumber(seatnumber);
        return seatBynumber;
    }
}
