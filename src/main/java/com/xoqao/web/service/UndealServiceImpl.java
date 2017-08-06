package com.xoqao.web.service;

import com.xoqao.web.bean.deal.UnDeal;
import com.xoqao.web.dao.UndealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/6.
 * Email:dx96_j@163.com
 */
@Service
public class UndealServiceImpl implements UndealService {
    @Autowired
    private UndealMapper undealMapper;

    public void insertUndeal(String sno, Date record) throws Exception {
        undealMapper.insertUndeal(sno, record);

    }

    public List<UnDeal> findUnDealCord(String sno) throws Exception {
        List<UnDeal> unDealCord = undealMapper.findUnDealCord(sno);
        return unDealCord;
    }
}
