package com.xoqao.web.service;


import com.xoqao.web.bean.user.User;
import com.xoqao.web.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: lovegod
 * Created by 123 on 2017/2/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public User findUserByphoneOrSno(String loginstr) throws Exception {
        User userByphoneOrSno = userMapper.findUserByphoneOrSno(loginstr);
        return userByphoneOrSno;
    }

    public List<User> findAllUsers(String year, String college, String major) throws Exception {
        List<User> allUsers = userMapper.findAllUsers(year, college, major);
        return allUsers;
    }

    public List<User> findAllUsersPage(String year, String college, String major, Integer startRow, Integer pageSize) throws Exception {
        List<User> allUsersPage = userMapper.findAllUsersPage(year, college, major, startRow, pageSize);
        return allUsersPage;
    }


    public void deleteUserByID(Integer uid) throws Exception {
        userMapper.deleteUserByID(uid);
    }

    public void insertUser(User user) throws Exception {
        userMapper.insertUser(user);
    }

    public List<String> findAllCollege() throws Exception {
        List<String> allCollege = userMapper.findAllCollege();
        return allCollege;
    }

    public List<String> findMajorByCollege(String college) throws Exception {
        List<String> majorByCollege = userMapper.findMajorByCollege(college);
        return majorByCollege;
    }

    public User findUserBySno(String sno) throws Exception {
        User userBySno = userMapper.findUserBySno(sno);
        return userBySno;
    }

    public void updatePass(Integer uid, String password) throws Exception {
        userMapper.updatePass(uid, password);
    }

    public void updatePhone(Integer uid, String phone) throws Exception {
userMapper.updatePhone(uid, phone);
    }
}
