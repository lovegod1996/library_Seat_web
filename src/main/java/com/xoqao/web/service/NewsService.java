package com.xoqao.web.service;

import com.xoqao.web.bean.news.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
public interface NewsService {

    void insertNews( News news) throws Exception;

    List<News> findAllNews()throws Exception;

    List<News> findAllNewsPage( Integer startRow  ,Integer pageSize)throws Exception;

    News findNewsByid(Integer nid)throws Exception;

    void deleteNews(Integer nid)throws Exception;

}
