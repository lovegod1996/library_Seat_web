package com.xoqao.web.service;


import com.xoqao.web.bean.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: lovegod
 * Created by 123 on 2017/2/20.
 */
public interface UserService {

    User findUserByphoneOrSno( String loginstr) throws Exception;


    List<User> findAllUsers( String year, String college,String major)throws Exception;

    List<User> findAllUsersPage( String year,String college, String major, Integer startRow  ,Integer pageSize)throws Exception;

    void deleteUserByID( Integer uid)throws Exception;

    void insertUser( User user)throws Exception;

    List<String> findAllCollege()throws Exception;

    List<String> findMajorByCollege(String college)throws Exception;

    User findUserBySno(String sno)throws Exception;

    void updatePass(Integer uid,String password)throws Exception;

    void updatePhone(Integer uid,String phone)throws Exception;


    List<String> findcolleges() throws Exception;

    List<User> findallStudentbyCollege( String college) throws Exception;

    List<String> findmajorByCollege( String college) throws Exception;

    List<User> findstudentbycollegeandmajor( String college,String major)throws Exception;

    List<String> findclassbymajorcollege( String college, String major)throws Exception;

    List<User> findstudentbyclassmajor(String college,String major,String classes)throws Exception;
}
