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

    public User findUserByphone(String phone) throws Exception {
        User userByphone = userMapper.findUserByphone(phone);
        return userByphone;
    }

    public List<User> findAllUsers() throws Exception {
        List<User> allUsers = userMapper.findAllUsers();
        return allUsers;
    }


}
