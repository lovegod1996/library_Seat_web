package com.xoqao.web.service;

import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/21.
 * Email:dx96_j@163.com
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void insertNotice(Notice notice) throws Exception {
        noticeMapper.insertNotice(notice);
    }

    public List<Notice> findAllNotice() throws Exception {
        List<Notice> allNotice = noticeMapper.findAllNotice();
        return allNotice;
    }

    public List<Notice> findAllNoticetop() throws Exception {
        List<Notice> allNoticetop = noticeMapper.findAllNoticetop();
        return allNoticetop;
    }

    public List<Notice> findAllNoticepage(Integer startRow, Integer pageSize) throws Exception {
        List<Notice> allNoticepage = noticeMapper.findAllNoticepage(startRow, pageSize);
        return allNoticepage;
    }

    public Notice findNoticeByid(Integer nid) throws Exception {
        Notice noticeByid = noticeMapper.findNoticeByid(nid);
        return noticeByid;
    }

    public void deleteNotice(Integer nid) throws Exception {
        noticeMapper.deleteNotice(nid);
    }

    public void updateNotice(Notice notice) throws Exception {
        noticeMapper.updateNotice(notice);
    }
}
