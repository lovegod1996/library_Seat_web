package com.xoqao.web.service;

import com.xoqao.web.bean.deal.UnDeal;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/6.
 * Email:dx96_j@163.com
 */
public interface UndealService {

    void insertUndeal(String sno, Date record) throws Exception;

    List<UnDeal> findUnDealCord(String sno) throws Exception;
}
