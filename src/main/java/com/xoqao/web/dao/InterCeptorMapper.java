package com.xoqao.web.dao;

import com.xoqao.web.bean.interceptor.Intercepter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/7.
 * Email:dx96_j@163.com
 */
@Service
public interface InterCeptorMapper {

    List<Intercepter> findAllIntoceptor() throws Exception;
}
