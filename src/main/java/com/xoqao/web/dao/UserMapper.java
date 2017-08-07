package com.xoqao.web.dao;


import com.xoqao.web.bean.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.util.List;

@Service
public interface UserMapper {

    User findUserByphoneOrSno(@Param("loginstr") String loginstr) throws Exception;

    List<User> findAllUsers(@Param("year") String year, @Param("college") String college, @Param("major") String major) throws Exception;

    List<User> findAllUsersPage(@Param("year") String year, @Param("college") String college, @Param("major") String major, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize) throws Exception;

    void deleteUserByID(@Param("uid") Integer uid) throws Exception;

    void insertUser(@Param("user") User user) throws Exception;

    List<String> findAllCollege() throws Exception;

    List<String> findMajorByCollege(@Param("college") String college) throws Exception;

    User findUserBySno(@Param("sno") String sno) throws Exception;


    void updatePass(@Param("uid") Integer uid, @Param("password") String password) throws Exception;

    void updatePhone(@Param("uid") Integer uid, @Param("phone") String phone) throws Exception;

    List<String> findcolleges() throws Exception;

    List<User> findallStudentbyCollege(@Param("college") String college) throws Exception;

    List<String> findmajorByCollege(@Param("college") String college) throws Exception;

    List<User> findstudentbycollegeandmajor(@Param("college") String college,@Param("major") String major)throws Exception;

    List<String> findclassbymajorcollege(@Param("college") String college,@Param("major") String major)throws Exception;

    List<User> findstudentbyclassmajor(@Param("college") String college,@Param("major") String major,@Param("classes") String classes)throws Exception;


}