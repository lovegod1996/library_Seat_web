package com.xoqao.web.service;

import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.dao.WeekOpenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/25.
 * Email:dx96_j@163.com
 */
@Service
public class WeekOpenServiceImpl implements WeekOpenService {

    @Autowired
    private WeekOpenMapper weekOpenMapper;

    public List<WeekOpen> findweekByfid(Integer fid) throws Exception {
        List<WeekOpen> weekOpens = weekOpenMapper.findweekByfid(fid);
        return weekOpens;
    }

    public void updatestatue(Integer statue, Integer woid) throws Exception {
        weekOpenMapper.updatestatue(statue, woid);
    }

    public void deletestatue(Integer woid) throws Exception {
        weekOpenMapper.deletestatue(woid);
    }

    public void insertweek(WeekOpen weekOpen) throws Exception {
        weekOpenMapper.insertweek(weekOpen);
    }

    public List<WeekOpen> findopentody() throws Exception {
        List<WeekOpen> findopentody = weekOpenMapper.findopentody();
        return findopentody;
    }

    public WeekOpen findopenFloortoday(Integer fid) throws Exception {
        WeekOpen weekOpen = weekOpenMapper.findopenFloortoday(fid);
        return weekOpen;
    }

    public WeekOpen findopenFloorday(Integer fid, Integer day) throws Exception {
        WeekOpen weekOpen = weekOpenMapper.findopenFloorday(fid, day);
        return weekOpen;
    }

    public List<WeekOpen> findopenfloorsday(Integer day, Integer bid) throws Exception {
        List<WeekOpen> findopenfloorsday = weekOpenMapper.findopenfloorsday(day, bid);
        return findopenfloorsday;
    }

}
