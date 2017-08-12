package com.xoqao.web.dao;

import com.xoqao.web.bean.weekopen.WeekOpen;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/25.
 * Email:dx96_j@163.com
 */
@Service
public interface WeekOpenMapper {

    List<WeekOpen> findweekByfid(@Param("fid") Integer fid) throws Exception;

    void updatestatue(@Param("statue") Integer statue, @Param("woid") Integer woid) throws Exception;

    void deletestatue(@Param("woid") Integer woid) throws Exception;

    void insertweek(@Param("weekOpen") WeekOpen weekOpen) throws Exception;

    List<WeekOpen> findopen(@Param("day") Integer day) throws Exception;

    WeekOpen findopenFloortoday(@Param("fid") Integer fid) throws Exception;

    WeekOpen findopenFloorday(@Param("fid") Integer fid, @Param("day") Integer day) throws Exception;

    List<WeekOpen> findopenfloorsday(@Param("day") Integer day, @Param("bid") Integer bid) throws Exception;


}
