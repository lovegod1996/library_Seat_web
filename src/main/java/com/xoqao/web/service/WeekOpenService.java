package com.xoqao.web.service;

import com.xoqao.web.bean.weekopen.WeekOpen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/25.
 * Email:dx96_j@163.com
 */
public interface WeekOpenService {
    List<WeekOpen> findweekByfid(Integer fid)throws Exception;

    void updatestatue(Integer statue,Integer woid)throws Exception;

    void deletestatue( Integer woid)throws Exception;

    void insertweek( WeekOpen weekOpen)throws Exception;

    List<WeekOpen> findopentody()throws Exception;

    WeekOpen findopenFloortoday(Integer fid) throws Exception;

    WeekOpen findopenFloorday(Integer fid,  Integer day) throws Exception;

    List<WeekOpen> findopenfloorsday(Integer day, Integer bid) throws Exception;
}
