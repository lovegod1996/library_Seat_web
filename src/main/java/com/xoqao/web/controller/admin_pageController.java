package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class admin_pageController {

    @RequestMapping("/index_Admin")
    public String index_Admin(Model model) throws Exception {
        return "admin_page/Index_Admin";
    }

    @RequestMapping("/main_Admin")
    public String main_Admin(Model model) throws Exception {
        return "admin_page/Main_Admin";
    }

    @RequestMapping("/seatInUse")
    public String seatInUse(Model model) throws Exception {
        return "admin_page/SeatInUse";
    }

    @RequestMapping("/add_News")
    public String add_News(Model model) throws Exception {
        return "admin_page/Add_News";
    }
}
