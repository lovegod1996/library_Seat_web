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

    @RequestMapping("/seat_In_Use")
    public String seat_In_Use(Model model) throws Exception {
        return "admin_page/Seat_In_Use";
    }

    @RequestMapping("/seat_In_Book")
    public String seat_In_Book(Model model) throws Exception {
        return "admin_page/Seat_In_Book";
    }

    @RequestMapping("/seat_In_Empty")
    public String seat_In_Empty(Model model) throws Exception {
        return "admin_page/Seat_In_Empty";
    }

    @RequestMapping("/add_News")
    public String add_News(Model model) throws Exception {
        return "admin_page/Add_News";
    }

    @RequestMapping("/news_List_Admin")
    public String news_List_Admin(Model model) throws Exception {
        return "admin_page/News_List_Admin";
    }
}
