package com.xoqao.web.dao;

import com.xoqao.web.bean.admin.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
@Service
public interface AdminMapper {

    Admin findAdminByCount(@Param("loginstr") String loginStr)throws Exception;
}
