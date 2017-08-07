package com.xoqao.web.service;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Admin findAdminByCount(String loginstr) throws Exception {
        Admin adminByNameOrId = adminMapper.findAdminByCount(loginstr);
        return adminByNameOrId;
    }

    public Admin findAdminByid(Integer aid) throws Exception {
        Admin adminByid = adminMapper.findAdminByid(aid);
        return adminByid;
    }

    public void updateLimit(Integer notice, Integer floor, Integer user, Integer seat, Integer aid) throws Exception {
        adminMapper.updateLimit(notice, floor, user, seat, aid);
    }

    public void insertAdmin(String number, String pasword, String name, String employer) throws Exception {
        adminMapper.insertAdmin(number, pasword, name, employer);
    }

    public void deleteAdmin(Integer aid) throws Exception {
        adminMapper.deleteAdmin(aid);
    }

    public List<Admin> findAllAdmin() throws Exception {
        List<Admin> allAdmin = adminMapper.findAllAdmin();
        return allAdmin;
    }

    public void updatePassword(String password, Integer aid) throws Exception {
        adminMapper.updatePassword(password, aid);
    }
}
