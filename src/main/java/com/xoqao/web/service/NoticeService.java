package com.xoqao.web.service;

import com.xoqao.web.bean.news.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/21.
 * Email:dx96_j@163.com
 */
public interface NoticeService {

    void insertNotice( Notice notice) throws Exception;

    List<Notice> findAllNotice() throws Exception;

    List<Notice> findAllNoticetop() throws Exception;

    List<Notice> findAllNoticepage( Integer startRow,  Integer pageSize) throws Exception;

    Notice findNoticeByid( Integer nid) throws Exception;

    void deleteNotice( Integer nid) throws Exception;

    void updateNotice(Notice notice) throws Exception;

}
