package com.xoqao.web.controller;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.service.BuildingService;
import com.xoqao.web.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 1Q84 on 2017/7/19.
 */
@Controller
@RequestMapping("/view")
public class SuperAdmin_Controller {

    @Autowired
    private BuildingService buildingService;


    @Autowired
    private FloorService floorService;

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

    /**
     * 提交添加图书馆
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/adbuilding")
    public String adBuilding(Model model, String libaray, String admin) throws Exception {
        List<Building> allBuilding = buildingService.findAllBuilding();
        Building building1=allBuilding.get(allBuilding.size()-1);
        Calendar a= Calendar.getInstance();
        Integer year= a.get(Calendar.YEAR);
        Random random=new Random();
        Building building = new Building();
        building.setAccountnumber(year+""+ random.nextInt(100)+""+(building1.getBid()+1));
        building.setEmployer(libaray);
        building.setName(admin);
        building.setPassword("123456");
        try {
            buildingService.insertLibaray(building);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Building> allBuilding1 = buildingService.findAllBuilding();
        model.addAttribute("allbuidings", allBuilding1);
        return "superadmin_page/Managing_Building_SuperAdmin";
    }

    /**
     * 删除图书馆信息
     * @param model
     * @param bid
     * @return
     * @throws Exception
     */
    @RequestMapping("/buidingdelete")
    public String deleteBuliding(Model model,Integer bid)throws Exception{
        buildingService.deleteLibaray(bid);
        List<Building> allBuilding = buildingService.findAllBuilding();
        model.addAttribute("allbuidings", allBuilding);
        return "superadmin_page/Managing_Building_SuperAdmin";
    }

    /**
     * 进入楼层管理
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/managing_Floor_SuperAdmin")
    public String managing_Floor_SuperAdmin(Model model,Integer bid) throws Exception {

        Building buildingById = buildingService.findBuildingById(bid);

        List<Floor> floors = floorService.findfloorsBybid(bid);

        model.addAttribute("floors",floors);
        model.addAttribute("building",buildingById);
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    /**
     * 删除楼层
     * @param model
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping("deletefloor")
    public String floorsDelete(Model model,Integer bid,Integer fid)throws Exception{
        floorService.deletefloor(fid);
        Building buildingById = buildingService.findBuildingById(bid);
        List<Floor> floors = floorService.findfloorsBybid(bid);
        model.addAttribute("floors",floors);
        model.addAttribute("building",buildingById);
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }


    @RequestMapping("/managing_User_SuperAdmin")
    public String managing_User_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Managing_User_SuperAdmin";
    }

    @RequestMapping("/seat_DataStatistics_SuperAdmin")
    public String seat_DataStatistics_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Seat_DataStatistics_SuperAdmin";
    }

    @RequestMapping("/study_DataStatistics_SuperAdmin")
    public String study_DataStatistics_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Study_DataStatistics_SuperAdmin";
    }

    @RequestMapping("/seat_DataStatistics_ForEachBuilding")
    public String seat_DataStatistics_ForEachBuilding(Model model) throws Exception {
        return "superadmin_page/Seat_DataStatistics_ForEachBuilding";
    }

    @RequestMapping("/study_DataStatistics_ForEachBuilding")
    public String study_DataStatistics_ForEachBuilding(Model model) throws Exception {
        return "superadmin_page/Study_DataStatistics_ForEachBuilding";
    }

}
