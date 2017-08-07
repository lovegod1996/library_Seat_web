package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class Public_Controller {

    @RequestMapping("/page404")
    public String page404 (Model model) throws Exception{
        return "public_page/404";
    }

    @RequestMapping("/page500")
    public String page500 (Model model) throws Exception{
        return "public_page/500";
    }

    @RequestMapping("/login_ForAdmin")
    public String login_ForAdmin (Model model) throws Exception{
        return "public_page/Login_ForAdmin";
    }

    @RequestMapping("/resetPassword_ForAdmin")
    public String resetPassword_ForAdmin (Model model) throws Exception{
        return "public_page/ResetPassword_ForAdmin";
    }
}
