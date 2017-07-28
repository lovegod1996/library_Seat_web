package com.xoqao.web.controller;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.building.BuildingCusFloors;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class User_Controller {

    @Autowired
    private UserLearnService userLearnService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private FloorService floorService;

    @Autowired
    private WeekOpenService weekOpenService;

    @RequestMapping("/information_User")
    public String information_User(Model model) throws Exception {
        return "user_page/user_information/Information_User";
    }

    @RequestMapping("/userInformation_leftmenu")
    public String userInformation_leftmenu(Model model) throws Exception {
        return "user_page/user_information/UserInformation_leftmenu";
    }

    @RequestMapping("/userInformation_rightcontent")
    public String userInformation_rightcontent(Model model) throws Exception {
        return "user_page/user_information/UserInformation_rightcontent";
    }

    @RequestMapping("/study_List")
    public String study_List(Model model, Integer page, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");

        List<UserLearn> userLearnByUid = userLearnService.findUserLearnByUid(user.getUid());

        if (userLearnByUid.size() > 0) {

            int pageSize = 5;

            model.addAttribute("userLearnSize", userLearnByUid.size());
            int pageTims;
            if (userLearnByUid.size() % pageSize == 0) {
                pageTims = userLearnByUid.size() / pageSize;
            } else {
                pageTims = userLearnByUid.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (userLearnByUid.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<UserLearn> userLearnByUidPage = userLearnService.findUserLearnByUidPage(user.getUid(), startRow, pageSize);

            model.addAttribute("userLearns", userLearnByUidPage);
            return "user_page/user_information/Study_List";
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "你怎么还不去看书呢？");
        }
        return "user_page/user_information/Study_List";
    }

    @RequestMapping("/unpromise_List")
    public String unpromise_List(Model model, Integer page, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");

        List<UserLearn> userLearnPerByUid = userLearnService.findUserLearnPerByUid(user.getUid());

        if (userLearnPerByUid.size() > 0) {

            int pageSize = 5;

            model.addAttribute("userLearnSize", userLearnPerByUid.size());
            int pageTims;
            if (userLearnPerByUid.size() % pageSize == 0) {
                pageTims = userLearnPerByUid.size() / pageSize;
            } else {
                pageTims = userLearnPerByUid.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (userLearnPerByUid.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<UserLearn> userLearnByUidPage = userLearnService.findUserLearnPerByUidPage(user.getUid(), startRow, pageSize);

            model.addAttribute("userLearns", userLearnByUidPage);
            return "user_page/user_information/Unpromise_List";
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "你是最棒哒！");
        }

        return "user_page/user_information/Unpromise_List";
    }

    @RequestMapping("setNewPassword")
    public String setNewPassword(Model model) throws Exception {
        return "user_page/user_information/SetNewPassword";
    }

    /**
     * 设置新密码提交
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/setNewPassSub")
    public String setNewPassSub(Model model, String oldPwd, String newPwd, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");
        if (oldPwd.trim().equals(user.getPassword())) {
            userService.updatePhone(user.getUid(), newPwd);
            return "forward:/information_User_Self";
        } else {
            model.addAttribute("error_msg", "您的旧密码输入错误，请重新输入！");
            return "user_page/user_information/SetNewPassword";
        }

    }


    @RequestMapping("setNewPhonenumber")
    public String setNewPhonenumber(Model model) throws Exception {
        return "user_page/user_information/SetNewPhonenumber";
    }

    @RequestMapping("forgetPassword")
    public String forgetPassword(Model model) throws Exception {
        return "user_page/user_information/ForgetPassword";
    }

    @RequestMapping("bindingPhonenumber")
    public String bindingPhonenumber(Model model) throws Exception {
        return "user_page/user_information/BindingPhonenumber";
    }

    @RequestMapping("information_User_Self")
    public String information_User_Self(Model model, HttpSession httpSession) throws Exception {

        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("users", user);

        return "user_page/user_information/Information_User_Self";
    }

    @RequestMapping("choose_Building")
    public String choose_Building(Model model, Integer day) throws Exception {
        List<Building> allBuilding = buildingService.findAllBuilding();
        List<BuildingCusFloors> buildingCusFloorsList = new ArrayList<BuildingCusFloors>();
        for (int i = 0; i < allBuilding.size(); i++) {
            List<WeekOpen> findopenfloorsday = weekOpenService.findopenfloorsday(day+1, allBuilding.get(i).getBid());
            BuildingCusFloors buildingCusFloors=new BuildingCusFloors();
            BeanUtils.copyProperties(allBuilding.get(i),buildingCusFloors);
            List<Floor> floors=new ArrayList<Floor>();
            for (int j = 0; j <findopenfloorsday.size() ; j++) {
                Floor floor = floorService.findfloorByid(findopenfloorsday.get(j).getLid());
                floors.add(floor);
            }
            buildingCusFloors.setFloors(floors);
            buildingCusFloorsList.add(buildingCusFloors);
        }
        model.addAttribute("buildFloors",buildingCusFloorsList);
        model.addAttribute("day",day);
        return "user_page/Choose_Building";
    }

}
