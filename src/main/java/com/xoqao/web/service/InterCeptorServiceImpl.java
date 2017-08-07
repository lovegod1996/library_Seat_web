package com.xoqao.web.service;

import com.xoqao.web.bean.interceptor.Intercepter;
import com.xoqao.web.dao.InterCeptorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/7.
 * Email:dx96_j@163.com
 */
@Service
public class InterCeptorServiceImpl implements InterCeptorService {
    @Autowired
    private InterCeptorMapper interCeptorMapper;

    public List<Intercepter> findAllIntoceptor() throws Exception {
        List<Intercepter> allIntoceptor = interCeptorMapper.findAllIntoceptor();
        return allIntoceptor;
    }
}
