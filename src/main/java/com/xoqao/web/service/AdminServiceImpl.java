package com.xoqao.web.service;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    public Admin findadminBynameOrid(String loginstr) throws Exception {
        Admin adminByNameOrId = adminMapper.findAdminByNameOrId(loginstr);
        return adminByNameOrId;
    }
}
