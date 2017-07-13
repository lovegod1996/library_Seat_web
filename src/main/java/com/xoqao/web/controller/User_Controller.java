package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class User_Controller {

    @RequestMapping("/information_User")
    public String information_User(Model model)throws Exception{
        return "user_page/user_information/Information_User";
    }

    @RequestMapping("/userInformation_leftmenu")
    public String userInformation_leftmenu (Model model) throws Exception{
        return "user_page/user_information/UserInformation_leftmenu";
    }

    @RequestMapping("/userInformation_rightcontent")
    public String userInformation_rightcontent (Model model) throws Exception{
        return "user_page/user_information/UserInformation_rightcontent";
    }

    @RequestMapping("study_List")
    public String study_List(Model model) throws Exception{
        return "user_page/user_information/Study_List";
    }

    @RequestMapping("study_Statistics")
    public String study_Statistics(Model model) throws Exception{
        return "user_page/user_information/Study_Statistics";
    }

    @RequestMapping("unpromise_List")
    public String unpromise_List(Model model) throws Exception{
        return "user_page/user_information/Unpromise_List";
    }

    @RequestMapping("setNewPassword")
    public String setNewPassword(Model model) throws Exception{
        return "user_page/user_information/SetNewPassword";
    }

    @RequestMapping("setNewPhonenumber")
    public String setNewPhonenumber(Model model) throws Exception{
        return "user_page/user_information/SetNewPhonenumber";
    }

    @RequestMapping("forgetPassword")
    public String forgetPassword(Model model) throws Exception{
        return "user_page/user_information/ForgetPassword";
    }

}
