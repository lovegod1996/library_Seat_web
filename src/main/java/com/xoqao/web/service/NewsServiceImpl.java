package com.xoqao.web.service;

import com.xoqao.web.bean.news.News;
import com.xoqao.web.dao.NewsMapper;
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
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    public void insertNews(News news) throws Exception {
        newsMapper.insertNews(news);
    }

    public List<News> findAllNews() throws Exception {
        List<News> allNews = newsMapper.findAllNews();
        return allNews;
    }

    public List<News> findAllNewsTop() throws Exception {
        List<News> allNewsTop = newsMapper.findAllNewsTop();
        return allNewsTop;
    }

    public List<News> findAllNewsPage(Integer startRow, Integer pageSize) throws Exception {
        List<News> allNewsPage = newsMapper.findAllNewsPage(startRow, pageSize);
        return allNewsPage;
    }

    public News findNewsByid(Integer nid) throws Exception {
        News newsByid = newsMapper.findNewsByid(nid);
        return newsByid;
    }

    public void deleteNews(Integer nid) throws Exception {
        newsMapper.deleteNews(nid);
    }

    public void updateNewsByid(News news) throws Exception {
        newsMapper.updateNewsByid(news);
    }
}
