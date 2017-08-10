package com.xoqao.web.service;

import com.xoqao.web.bean.interceptor.Intercepter;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/7.
 * Email:dx96_j@163.com
 */
public interface InterCeptorService {
    List<Intercepter> findAllIntoceptor() throws Exception;
}
