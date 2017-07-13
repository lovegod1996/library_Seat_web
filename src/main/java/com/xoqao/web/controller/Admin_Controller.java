package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class Admin_Controller {

    @RequestMapping("/setNewTime")
    public String setNewTime(Model model)throws Exception{
        return "admin_page/SetNewTime";
    }

    @RequestMapping("/managing_Users")
    public String managing_Users(Model model)throws Exception{
        return "admin_page/Managing_Users";
    }

    @RequestMapping("/add_User")
    public String add_User(Model model)throws Exception{
        return "admin_page/Add_User";
    }
}
