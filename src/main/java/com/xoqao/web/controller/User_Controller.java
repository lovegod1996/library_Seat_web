package com.xoqao.web.controller;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingCusFloor;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.building.BuildingCusFloors;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.seat.Seat;
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
    @Autowired
    private SeatService seatService;

    @RequestMapping("/information_User")
    public String information_User(Model model) throws Exception {

        return "user_page/user_information/Information_User";
    }

    /**
     * 左侧导航菜单
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/userInformation_leftmenu")
    public String userInformation_leftmenu(Model model) throws Exception {


        return "user_page/user_information/UserInformation_leftmenu";
    }

    @RequestMapping("/userInformation_rightcontent")
    public String userInformation_rightcontent(Model model) throws Exception {


        return "user_page/user_information/UserInformation_rightcontent";
    }

    /**
     * 查询学习记录
     * @param model
     * @param page
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/study_List")
    public String study_List(Model model, Integer page, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");

        List<Booking> finduserbook = bookingService.finduserbook(user.getSno());
        if(finduserbook.size()>0){
            int pageSize = 5;
            model.addAttribute("userbookSize", finduserbook.size());
            int pageTims;
            if (finduserbook.size() % pageSize == 0) {
                pageTims = finduserbook.size() / pageSize;
            } else {
                pageTims = finduserbook.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (finduserbook.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Booking> finduserbookpage = bookingService.finduserbookpage(user.getSno(), startRow, pageSize);

            List<BookingCusFloor> bookingCusFloorList = new ArrayList<BookingCusFloor>();
            for (int i = 0; i < finduserbookpage.size(); i++) {
                BookingCusFloor bookingCusFloor = new BookingCusFloor();
                BeanUtils.copyProperties(finduserbookpage.get(i), bookingCusFloor);
                Seat byid = seatService.findByid(finduserbookpage.get(i).getSid());
                bookingCusFloor.setColumns(byid.getColumns());
                bookingCusFloor.setFid(byid.getFid());
                bookingCusFloor.setLeftside(byid.getLeftside());
                bookingCusFloor.setRow(byid.getRow());
                bookingCusFloor.setSeatnumber(byid.getSeatnumber());
                Floor floor = floorService.findfloorByid(byid.getFid());
                bookingCusFloor.setFloor(floor.getEmployer());
                Building buildingById = buildingService.findBuildingById(floor.getBid());
                bookingCusFloor.setBuilding(buildingById.getEmployer());
                bookingCusFloorList.add(bookingCusFloor);
            }
            model.addAttribute("userbooks", bookingCusFloorList);
        }else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "你怎么还不去看书呢？");
        }
        return "user_page/user_information/Study_List";
    }

    @RequestMapping("/unpromise_List")
    public String unpromise_List(Model model, Integer page, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");

        List<Booking> finduserbookpromise = bookingService.finduserbookpromise(user.getSno(), 1);


        if (finduserbookpromise.size() > 0) {

            int pageSize = 5;

            model.addAttribute("userbookSize", finduserbookpromise.size());
            int pageTims;
            if (finduserbookpromise.size() % pageSize == 0) {
                pageTims = finduserbookpromise.size() / pageSize;
            } else {
                pageTims = finduserbookpromise.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (finduserbookpromise.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Booking> finduserbookpromisepage = bookingService.finduserbookpromisepage(user.getSno(), 1, startRow, pageSize);

            List<BookingCusFloor> bookingCusFloorList = new ArrayList<BookingCusFloor>();
            for (int i = 0; i < finduserbookpromisepage.size(); i++) {
                BookingCusFloor bookingCusFloor = new BookingCusFloor();
                BeanUtils.copyProperties(finduserbookpromisepage.get(i), bookingCusFloor);
                Seat byid = seatService.findByid(finduserbookpromisepage.get(i).getSid());
                bookingCusFloor.setColumns(byid.getColumns());
                bookingCusFloor.setFid(byid.getFid());
                bookingCusFloor.setLeftside(byid.getLeftside());
                bookingCusFloor.setRow(byid.getRow());
                bookingCusFloor.setSeatnumber(byid.getSeatnumber());
                Floor floor = floorService.findfloorByid(byid.getFid());
                bookingCusFloor.setFloor(floor.getEmployer());
                Building buildingById = buildingService.findBuildingById(floor.getBid());
                bookingCusFloor.setBuilding(buildingById.getEmployer());
                bookingCusFloorList.add(bookingCusFloor);
            }

            model.addAttribute("userbooks", bookingCusFloorList);
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
            List<WeekOpen> findopenfloorsday = weekOpenService.findopenfloorsday(day + 1, allBuilding.get(i).getBid());
            BuildingCusFloors buildingCusFloors = new BuildingCusFloors();
            BeanUtils.copyProperties(allBuilding.get(i), buildingCusFloors);
            List<Floor> floors = new ArrayList<Floor>();
            for (int j = 0; j < findopenfloorsday.size(); j++) {
                Floor floor = floorService.findfloorByid(findopenfloorsday.get(j).getLid());
                floors.add(floor);
            }
            buildingCusFloors.setFloors(floors);
            buildingCusFloorsList.add(buildingCusFloors);
        }
        model.addAttribute("buildFloors", buildingCusFloorsList);
        model.addAttribute("day", day);
        return "user_page/Choose_Building";
    }

}
