package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/19.
 */
@Controller
@RequestMapping("/view")
public class SuperAdmin_Controller {

    @RequestMapping("/index_SuperAdmin")
    public String index_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Index_SuperAdmin";
    }

    @RequestMapping("/leftmenu_SuperAdmin")
    public String leftmenu_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Leftmenu_SuperAdmin";
    }

    @RequestMapping("/main_SuperAdmin")
    public String main_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Main_SuperAdmin";
    }

    @RequestMapping("/managing_Floor_SuperAdmin")
    public String managing_Floor_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    @RequestMapping("/managing_User_SuperAdmin")
    public String managing_User_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Managing_User_SuperAdmin";
    }

    @RequestMapping("/managing_Powers_SuperAdmin")
    public String managing_Powers_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Managing_Powers_SuperAdmin";
    }
}
