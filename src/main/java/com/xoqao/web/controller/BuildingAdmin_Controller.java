package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/19.
 */
@Controller
@RequestMapping("/view")
public class BuildingAdmin_Controller {

    @RequestMapping("/index_BuildingAdmin")
    public String index_BuildingAdmin(Model model) throws Exception{
        return "buildingadmin_page/Index_BuildingAdmin";
    }

    @RequestMapping("/leftmenu_BuildingAdmin")
    public String leftmenu_BuildingAdmin(Model model) throws Exception{
        return "buildingadmin_page/Leftmenu_BuildingAdmin";
    }

    @RequestMapping("/main_BuildingAdmin")
    public String main_BuildingAdmin(Model model) throws Exception{
        return "buildingadmin_page/Main_BuildingAdmin";
    }

    @RequestMapping("/managing_Floor_BuildingAdmin")
    public String managing_Floor_BuildingAdmin(Model model) throws Exception{
        return "buildingadmin_page/Managing_Floor_BuildingAdmin";
    }

    @RequestMapping("/managing_User_BuildingAdmin")
    public String managing_User_BuildingAdmin(Model model) throws Exception{
        return "buildingadmin_page/Managing_User_BuildingAdmin";
    }

}
