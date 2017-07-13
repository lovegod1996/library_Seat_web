package com.xoqao.web.dao;

import com.xoqao.web.bean.news.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/12.
 * Email:dx96_j@163.com
 */
@Service
public interface NewsMapper {

    void insertNews(@Param("news") News news) throws Exception;

    List<News> findAllNews() throws Exception;

    List<News> findAllNewsTop() throws Exception;

    List<News> findAllNewsPage(@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    News findNewsByid(@Param("nid") Integer nid) throws Exception;

    void deleteNews(@Param("nid") Integer nid) throws Exception;

    void updateNewsByid(@Param("news") News news) throws Exception;
}
