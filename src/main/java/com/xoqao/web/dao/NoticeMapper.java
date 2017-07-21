package com.xoqao.web.dao;

import com.xoqao.web.bean.news.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/21.
 * Email:dx96_j@163.com
 */
@Service
public interface NoticeMapper {

    void insertNotice(@Param("notice") Notice notice) throws Exception;

    List<Notice> findAllNotice() throws Exception;

    List<Notice> findAllNoticetop() throws Exception;

    List<Notice> findAllNoticepage(@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    Notice findNoticeByid(@Param("nid") Integer nid) throws Exception;

    void deleteNotice(@Param("nid") Integer nid) throws Exception;

    void updateNotice(@Param("notice") Notice notice) throws Exception;

}
