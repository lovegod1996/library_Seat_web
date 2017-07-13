package com.xoqao.web.controller;

import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class Admin_Controller {

    @Autowired
    private UserService userService;


    @RequestMapping("/seat_Now")
    public String seat_Now(Model model) throws Exception {
        return "admin_page/Seat_Now";
    }

    @RequestMapping("/setNewTime")
    public String setNewTime(Model model) throws Exception {
        return "admin_page/SetNewTime";
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
        }catch (Exception e){
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


}
