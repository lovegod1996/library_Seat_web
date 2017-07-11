package com.xoqao.web.service;

import com.xoqao.web.bean.admin.Admin;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
public interface AdminService {
    Admin findadminBynameOrid(String loginstr)throws Exception;
}
