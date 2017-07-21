package com.xoqao.web.controller;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by 1Q84 on 2017/7/19.
 */
@Controller
@RequestMapping("/view")
public class SuperAdmin_Controller {

    @Autowired
    private BuildingService buildingService;

    @RequestMapping("/index_SuperAdmin")
    public String index_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Index_SuperAdmin";
    }

    @RequestMapping("/leftmenu_SuperAdmin")
    public String leftmenu_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Leftmenu_SuperAdmin";
    }

    @RequestMapping("/main_SuperAdmin")
    public String main_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Main_SuperAdmin";
    }

    /**
     * 添加图书馆
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/managing_Building_SuperAdmin")
    public String managing_Building_SuperAdmin(Model model) throws Exception {

        List<Building> allBuilding = buildingService.findAllBuilding();
        model.addAttribute("allbuidings", allBuilding);
        return "superadmin_page/Managing_Building_SuperAdmin";
    }

    @RequestMapping("/managing_Floor_SuperAdmin")
    public String managing_Floor_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    @RequestMapping("/managing_User_SuperAdmin")
    public String managing_User_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Managing_User_SuperAdmin";
    }

    @RequestMapping("/seat_DataStatistics_SuperAdmin")
    public String seat_DataStatistics_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Seat_DataStatistics_SuperAdmin";
    }

    @RequestMapping("/study_DataStatistics_SuperAdmin")
    public String study_DataStatistics_SuperAdmin(Model model) throws Exception{
        return "superadmin_page/Study_DataStatistics_SuperAdmin";
    }

    @RequestMapping("/seat_DataStatistics_ForEachBuilding")
    public String seat_DataStatistics_ForEachBuilding(Model model) throws Exception{
        return "superadmin_page/Seat_DataStatistics_ForEachBuilding";
    }

    @RequestMapping("/study_DataStatistics_ForEachBuilding")
    public String study_DataStatistics_ForEachBuilding(Model model) throws Exception{
        return "superadmin_page/Study_DataStatistics_ForEachBuilding";
    }

}
