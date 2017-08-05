package com.xoqao.web.dao;

import com.xoqao.web.bean.admin.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
@Service
public interface AdminMapper {

    Admin findAdminByCount(@Param("loginstr") String loginStr) throws Exception;

    Admin findAdminByid(@Param("aid") Integer aid) throws Exception;

    void updateLimit(@Param("notice") Integer notice, @Param("floor") Integer floor, @Param("user") Integer user, @Param("seat") Integer seat, @Param("aid") Integer aid) throws Exception;

    void insertAdmin(@Param("number")String number,@Param("pasword")String pasword,@Param("name")String name,@Param("employer")String employer) throws Exception;

    void deleteAdmin(@Param("aid") Integer aid)throws Exception;

    List<Admin> findAllAdmin()throws Exception;
}
