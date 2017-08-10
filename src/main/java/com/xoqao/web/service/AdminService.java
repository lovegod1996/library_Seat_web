package com.xoqao.web.service;

import com.xoqao.web.bean.admin.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
public interface AdminService {
    Admin findAdminByCount(String loginstr) throws Exception;

    Admin findAdminByid(Integer aid) throws Exception;

    void updateLimit(Integer notice, Integer floor, Integer user, Integer seat, Integer aid) throws Exception;

    void insertAdmin(String number, String pasword, String name, String employer) throws Exception;

    void deleteAdmin(Integer aid) throws Exception;

    List<Admin> findAllAdmin() throws Exception;

    void updatePassword( String password,Integer aid)throws Exception;
}
