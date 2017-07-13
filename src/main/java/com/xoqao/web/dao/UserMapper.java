package com.xoqao.web.dao;


import com.xoqao.web.bean.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

    User findUserByphoneOrSno(@Param("loginstr") String loginstr) throws Exception;

    List<User> findAllUsers(@Param("year") String year,@Param("college") String college,@Param("major") String major)throws Exception;

    List<User> findAllUsersPage(@Param("year") String year,@Param("college") String college,@Param("major") String major,@Param("startRow") Integer startRow  ,@Param("pageSize")Integer pageSize)throws Exception;

    void deleteUserByID(@Param("uid") Integer uid)throws Exception;

    void insertUser(@Param("user") User user)throws Exception;

    List<String> findAllCollege()throws Exception;

    List<String> findMajorByCollege(@Param("college") String college)throws Exception;
}