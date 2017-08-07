package com.xoqao.web.dao;

import com.xoqao.web.bean.deal.UnDeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/5.
 * Email:dx96_j@163.com
 */
@Service
public interface UndealMapper {

    void insertUndeal(@Param("sno") String sno, @Param("record") Date record) throws Exception;

    List<UnDeal> findUnDealCord(@Param("sno") String sno) throws Exception;

}
