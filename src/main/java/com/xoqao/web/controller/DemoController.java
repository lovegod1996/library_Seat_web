package com.xoqao.web.controller;

import com.xoqao.web.bean.user.User;
import com.xoqao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/9.
 * Email:dx96_j@163.com
 */
@Controller
@RequestMapping("/test")
public class DemoController {

//    @Autowired
//    UserService userService;
//
//    @RequestMapping("/login")
//    public String backlogin(Model model) throws Exception {
//        return "backmanage/backlogin";
//    }
//
//    @RequestMapping("/test")
//    public String test(Model model) throws Exception {
//        return "test/backlogin";
//    }
//
//    @RequestMapping("/postManage")
//    public String postmanage(Model model) throws Exception {
//        return "backmanage/postmanage";
//    }
//
//    @RequestMapping("/intoSecAdd")
//    public String sectionAdd(Model model) throws Exception {
//        return "backmanage/sectionAdd";
//    }
//
//    @RequestMapping("/sectionList")
//    public String deleteSec(Model model) {
//        List<User> allUsers = null;
//        try {
//            allUsers = userService.findAllUsers();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("user",allUsers);
//        return "backmanage/sectionList";
//    }

}
