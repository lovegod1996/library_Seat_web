package com.xoqao.web.controller;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.seat.SeatCus;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.service.BuildingService;
import com.xoqao.web.service.FloorService;
import com.xoqao.web.service.SeatService;
import com.xoqao.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class Admin_Controller {


    @Autowired
    private UserService userService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;

    @RequestMapping("/seat_Now")
    public String seat_Now(Model model) throws Exception {
        return "admin_page/Seat_Now";
    }


    @RequestMapping("/setNewTime")
    public String setNewTime(Model model) throws Exception {
        return "admin_page/SetNewTime";
    }

    @RequestMapping("/managing_Seat")
    public String managing_Seat(Model model) throws Exception {
        return "admin_page/Managing_Seat";
    }

    /**
     * 查看管理楼层内座位
     *
     * @param model
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/floorSeat")
    public String seatList(Model model, Integer page, HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Seat> seatsByFid = seatService.findSeatsByFid(floor.getFid());
        Integer pageSize = 10;
        if (seatsByFid.size() > 0) {
            model.addAttribute("seatSize", seatsByFid.size());
            int pageTims;
            if (seatsByFid.size() % pageSize == 0) {
                pageTims = seatsByFid.size() / pageSize;
            } else {
                pageTims = seatsByFid.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值

            if (null == page) {
                page = 1;
            }

            //每页开始的第几条记录
            int startRow;
            if (seatsByFid.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Seat> seatsByFidPage = seatService.findSeatsByFidPage(floor.getFid(), startRow, pageSize);
            List<SeatCus> seatCuses = new ArrayList<SeatCus>();
            for (int i = 0; i < seatsByFidPage.size(); i++) {
                SeatCus seatCus = new SeatCus();
                //将列表中的seat中的属性复制到seatcus中
                BeanUtils.copyProperties(seatsByFidPage.get(i), seatCus);
                Floor floor1 = floorService.findfloorByid(seatsByFidPage.get(i).getFid());

                Building buildingById = buildingService.findBuildingById(floor1.getBid());
                seatCus.setFloor(floor1.getEmployer());
                seatCus.setBuilding(buildingById.getEmployer());
                seatCuses.add(seatCus);
            }
            model.addAttribute("seats", seatCuses);
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "暂无座位信息");
        }
        return "admin_page/Seat_List_Admin";
    }

    /**
     * 更改座位状态
     *
     * @param model
     * @param statue
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeSeatStatue")
    public String changeSeatSatue(Model model, Integer statue, Integer sid) throws Exception {
        if (statue == 0) {
            seatService.updateSeatSatue(1, sid);
        } else {
            seatService.updateSeatSatue(0, sid);
        }
        return "redirect:/view/floorSeat?page=1";
    }

    /**
     * 删除某个座位
     *
     * @param model
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteSeat")
    public String deleteSeat(Model model, Integer sid) throws Exception {
        seatService.deleteSeat(sid);
        return "redirect:/view/floorSeat?page=1";
    }

    /**
     * 提交座位添加
     *
     * @param model
     * @param left
     * @param row
     * @param column
     * @param mark
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSeatSub")
    public String Seatadd(Model model, Integer left, Integer row, Integer column, String mark, HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        Seat seat = new Seat();
        seat.setColumns(column);
        seat.setFid(floor.getFid());
        seat.setLeftside(left);
        seat.setRow(row);
        String number = floor.getBid() + String.format("%02d", floor.getFloor()) + left + String.format("%02d", row) + String.format("%02d", column);
        seat.setSeatnumber(number);
        try {
            seatService.insertSeat(seat);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg","该座位已存在");
            return "admin_page/Managing_Seat";
        }
        return "redirect:/view/floorSeat?page=1";
    }

    /**
     * 进入设置开放时间
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/managing_Floor")
    public String mnaging_Floor(Model model,HttpSession httpSession) throws Exception {



        return "admin_page/Managing_Floor";
    }

    @RequestMapping("/managing_Users")
    public String managing_Users(Model model, Integer page, String year, String college, String major, HttpSession httpSession) throws Exception {
        int pageSize = 10;
        List<String> colleges = userService.findAllCollege();
        model.addAttribute("colleges", colleges);
        if (college != null && college.length() > 0) {
            if (college.contains("%")) {
                college = URLDecoder.decode(college, "utf-8");
            }
        }
        if (major != null && major.length() > 0) {
            if (major.contains("%")) {
                major = URLDecoder.decode(major, "utf-8");
            }
        }
        List<User> allUsers = userService.findAllUsers(year, college, major);

        model.addAttribute("usersSize", allUsers.size());
        int pageTims;
        if (allUsers.size() % pageSize == 0) {
            pageTims = allUsers.size() / pageSize;
        } else {
            pageTims = allUsers.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allUsers.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);
        if (major != null) {
            model.addAttribute("major", major);
            model.addAttribute("college", college);
        }
        if (year != null) {
            model.addAttribute("year", year);
        }

        List<User> allUsersPage = userService.findAllUsersPage(year, college, major, startRow, pageSize);

        if (allUsersPage.size() > 0) {
            model.addAttribute("users", allUsersPage);
            return "admin_page/Managing_Users";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Managing_Users";
        }
    }

    @RequestMapping("/add_User")
    public String add_User(Model model) throws Exception {
        return "admin_page/Add_User";
    }

    /**
     * 添加用户提交
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/aduserSub")
    public String adUserSub(Model model, String name, String sno, String college, String major, String classes, HttpSession httpSession) throws Exception {
        User user = new User();
        user.setClasses(classes);
        user.setMajor(major);
        user.setCollege(college);
        user.setSno(sno);
        user.setName(name);
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "添加失败！");
        }


        String year = null;
        Integer page = null;
        int pageSize = 10;
        List<String> colleges = userService.findAllCollege();
        model.addAttribute("colleges", colleges);
        if (college != null && college.length() > 0) {
            if (college.contains("%")) {
                college = URLDecoder.decode(college, "utf-8");
            }
        }
        if (major != null && major.length() > 0) {
            if (major.contains("%")) {
                major = URLDecoder.decode(major, "utf-8");
            }
        }
        List<User> allUsers = userService.findAllUsers(year, college, major);

        model.addAttribute("usersSize", allUsers.size());
        int pageTims;
        if (allUsers.size() % pageSize == 0) {
            pageTims = allUsers.size() / pageSize;
        } else {
            pageTims = allUsers.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allUsers.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);
        if (major != null) {
            model.addAttribute("major", major);
            model.addAttribute("college", college);
        }
        if (year != null) {
            model.addAttribute("year", year);
        }

        List<User> allUsersPage = userService.findAllUsersPage(year, college, major, startRow, pageSize);

        if (allUsersPage.size() > 0) {
            model.addAttribute("users", allUsersPage);
            return "admin_page/Managing_Users";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Managing_Users";
        }
    }

    @RequestMapping("/userDelete")
    public String userDelte(Model model, Integer uid, HttpSession httpSession) throws Exception {
        try {
            userService.deleteUserByID(uid);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "删除失败！");
        }

        String year = null;
        Integer page = null;
        int pageSize = 10;
        List<String> colleges = userService.findAllCollege();
        model.addAttribute("colleges", colleges);

        List<User> allUsers = userService.findAllUsers(null, null, null);

        model.addAttribute("usersSize", allUsers.size());
        int pageTims;
        if (allUsers.size() % pageSize == 0) {
            pageTims = allUsers.size() / pageSize;
        } else {
            pageTims = allUsers.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allUsers.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);

        model.addAttribute("major", null);
        model.addAttribute("college", null);


        model.addAttribute("year", null);


        List<User> allUsersPage = userService.findAllUsersPage(year, null, null, startRow, pageSize);

        if (allUsersPage.size() > 0) {
            model.addAttribute("users", allUsersPage);
            return "admin_page/Managing_Users";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Managing_Users";
        }

    }

    /**
     * 根据学院查询专业
     *
     * @param college
     * @return
     * @throws Exception
     */
    @RequestMapping("/collegeMajor")
    public
    @ResponseBody
    List<String> getCollegeMajor(String college) throws Exception {
        List<String> majorByCollege = userService.findMajorByCollege(college);
        return majorByCollege;
    }

    //    座位使用情况统计
    @RequestMapping("/seat_DataStatistics")
    public String seat_DataStatistics(Model model) throws Exception {
        return "admin_page/Seat_DataStatistics";
    }

    //    学习统计
    @RequestMapping("/study_DataStatistics")
    public String study_DataStatistics(Model model) throws Exception {
        return "admin_page/Study_DataStatistics";
    }


}
