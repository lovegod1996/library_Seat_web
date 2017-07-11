package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class public_pageController {

    @RequestMapping("/header_Admin")
    public String header_Admin(Model model) throws Exception {
        return "public_page/Header_Admin";
    }

    @RequestMapping("/leftmenu")
    public String leftmenu(Model model) throws Exception {
        return "public_page/Leftmenu";
    }

    @RequestMapping("/login")
    public String login(Model model) throws Exception {
        return "public_page/Login";
    }

    @RequestMapping("/header_User")
    public String header_User(Model model) throws Exception {
        return "public_page/Header_User";
    }

    @RequestMapping("/news_Content")
    public String news_Content(Model model) throws Exception {
        return "public_page/News_Content";
    }
}
