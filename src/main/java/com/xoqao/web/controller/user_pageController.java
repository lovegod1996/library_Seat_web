package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class user_pageController {

    @RequestMapping("/main_User")
    public String main_User(Model model) throws Exception {
        return "user_page/Main_User";
    }
}
