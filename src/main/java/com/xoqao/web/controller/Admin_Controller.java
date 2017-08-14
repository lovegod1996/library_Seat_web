package com.xoqao.web.controller;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.code.CodeModel;
import com.xoqao.web.bean.data.MonthData;
import com.xoqao.web.bean.data.WeekData;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.seat.SeatCus;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.CodeCreator;
import com.xoqao.web.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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


    @Autowired
    private WeekOpenService weekOpenService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/seat_Now")
    public String seat_Now(Model model) throws Exception {
        return "admin_page/Seat_Now";
    }


    @RequestMapping("/setNewTime")
    public String setNewTime(Model model) throws Exception {
        return "admin_page/SetNewTime";
    }

    @RequestMapping("/managing_Seat")
    public String managing_Seat(Model model, Integer fid) throws Exception {
        model.addAttribute("fid", fid);
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
    public String seatList(Model model, Integer page, HttpSession httpSession, Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
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
        model.addAttribute("fid", fid);
        return "admin_page/Seat_Floor_List_Admin";
    }


    @RequestMapping("/floorSeatsList")
    public String seatFloorList(Model model, Integer page, HttpSession httpSession, Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
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
        model.addAttribute("fid", fid);
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
    public String changeSeatSatue(Model model, Integer statue, Integer sid, Integer fid) throws Exception {
        if (statue == 0) {
            seatService.updateSeatSatue(1, sid);
        } else {
            seatService.updateSeatSatue(0, sid);
        }
        return "redirect:/view/floorSeatsList?page=1&fid=" + fid;
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
    public String deleteSeat(Model model, Integer sid, Integer fid) throws Exception {
        seatService.deleteSeat(sid);
        return "redirect:/view/floorSeatsList?page=1&fid=" + fid;
    }

    /**
     * 查看下载二维码
     *
     * @param model
     * @param seatNumber
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/getSeatPic")
    public void GetSeatPic(Model model, String seatNumber, HttpServletResponse httpServletResponse) throws Exception {
        Seat seatBynumber = seatService.findSeatBynumber(seatNumber);
        Floor floor = floorService.findfloorByid(seatBynumber.getFid());
        Building buildingById = buildingService.findBuildingById(floor.getBid());
        //生成二维码并下载到本地
        CodeCreator creator = new CodeCreator();
        CodeModel info = new CodeModel();
        info.setWidth(550);
        info.setHeight(550);
        info.setFontSize(24);
        //info.setContents("<a href='http://www.sohu.com'>人生就是拼搏</a>");
        //info.setContents("http://www.sohu.com");
        info.setContents(seatBynumber.getSeatnumber());
        info.setLogoFile(new File(CommenValue.SCHOOL_EMBLEM));
        String leftside = null;
        if (seatBynumber.getLeftside() == 0) {
            leftside = "左";
        } else {
            leftside = "右";
        }
        info.setDesc(buildingById.getEmployer() + "\n       " + floor.getEmployer() + "\n                    " + leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
        //info.setLogoDesc("一叶浮萍归大海，adsasfbhtjg人生何处不相逢");
        //info.setLogoDesc("一叶浮萍");
//        creator.createCodeImage(info, CommenValue.CODEPATH + seatBynumber.getSeatnumber() + "." + info.getFormat());
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment;fileName=" + new String((seatBynumber.getSeatnumber() + "." + info.getFormat()).getBytes("gbk"), "ISO8859-1"));
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        creator.createCodeImage(info, outputStream);
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 提交座位添加
     *
     * @param model
     * @param left
     * @param row
     * @param column
     * @param mark
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSeatSub")
    public String Seatadd(Model model, Integer left, Integer row, Integer column, String mark, Integer fid, HttpServletResponse httpServletResponse, RedirectAttributes redirectAttributes) throws Exception {

        if(left!=null&&row!=null&&column!=null&&fid!=null){


        Floor floor = floorService.findfloorByid(fid);
        //添加座位前需要先查看每周的开放时间是否已经设置完成
        List<WeekOpen> weekOpens = weekOpenService.findweekByfid(floor.getFid());
        if (weekOpens.size() != 7) {
            model.addAttribute("error_msg", "请先添加每周的开放时间段.");
            redirectAttributes.addFlashAttribute("error_msg", "请先添加每周的开放时间段");
            return "redirect:/view/managing_Floor?fid=" + fid;
        }
        Building buildingById = buildingService.findBuildingById(floor.getBid());
        Seat seat = new Seat();
        seat.setColumns(column);
        seat.setFid(floor.getFid());
        seat.setLeftside(left);
        seat.setRow(row);
        String number = floor.getBid() + String.format("%02d", floor.getFloor()) + left + String.format("%02d", row) + String.format("%02d", column);
        seat.setSeatnumber(number);
        try {
            seatService.insertSeat(seat);

            //生成二维码并下载到本地
            CodeCreator creator = new CodeCreator();
            CodeModel info = new CodeModel();
            info.setWidth(550);
            info.setHeight(550);
            info.setFontSize(24);
            //info.setContents("<a href='http://www.sohu.com'>人生就是拼搏</a>");
            //info.setContents("http://www.sohu.com");
            info.setContents(number);
            info.setLogoFile(new File(CommenValue.SCHOOL_EMBLEM));
            String leftside = null;
            if (seat.getLeftside() == 0) {
                leftside = "左";
            } else {
                leftside = "右";
            }
            info.setDesc(buildingById.getEmployer() + "\n       " + floor.getEmployer() + "\n                  " + leftside + "侧" + seat.getRow() + "排" + seat.getColumns() + "列");
            //info.setLogoDesc("一叶浮萍归大海，adsasfbhtjg人生何处不相逢");
            //info.setLogoDesc("一叶浮萍");
//            creator.createCodeImage(info, CommenValue.CODEPATH + number + "." + info.getFormat());
            httpServletResponse.setContentType("image/jpeg");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setHeader("Content-Disposition", "attachment;fileName=" + new String((number + "." + info.getFormat()).getBytes("gbk"), "ISO8859-1"));
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            creator.createCodeImage(info, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("fid", fid);
            model.addAttribute("error_msg", "该座位已存在");
            return "admin_page/Managing_Seat";
        }
        return "redirect:/view/floorSeat?page=1&fid=" + fid;
        }else{
            model.addAttribute("fid", fid);
            model.addAttribute("error_msg", "请选择必要的参数");
            return "admin_page/Managing_Seat";
        }
    }

    /**
     * 进入设置开放时间
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/managing_Floor")
    public String mnaging_Floor(Model model, Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        List<WeekOpen> weekOpens = weekOpenService.findweekByfid(floor.getFid());
        if (weekOpens.size() == 0) {
            model.addAttribute("NullList", "暂无一周当中的预约日期设置");
        }
        model.addAttribute("weekopens", weekOpens);
        model.addAttribute("fid", fid);
        return "admin_page/Managing_Floor";
    }

    /**
     * 提交完成添加
     *
     * @param model
     * @param httpSession
     * @param week
     * @param param1
     * @param param2
     * @return
     * @throws Exception
     */
    @RequestMapping("/addWeekOpenSub")
    public String addWeekOpen(Model model, HttpSession httpSession, Integer week, String param1, String param2, Integer fid, RedirectAttributes redirectAttributes) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        WeekOpen weekOpen = new WeekOpen();
        weekOpen.setLid(floor.getFid());
        weekOpen.setWeek(week);
        weekOpen.setParam1(param1);
        weekOpen.setParam2(param2);
        try {
            weekOpenService.insertweek(weekOpen);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "周" + week + "的已存在");
            redirectAttributes.addFlashAttribute("error_msg", "周" + week + "的已存在");
        }
        return "redirect:/view/managing_Floor?fid=" + fid;
    }

    /**
     * 改变一周中可预约状态
     *
     * @param model
     * @param woid
     * @param statue
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeWeekStatue")
    public String changeWeekSatue(Model model, Integer woid, Integer statue, Integer fid) throws Exception {
        if (statue == 0) {
            weekOpenService.updatestatue(1, woid);
        } else {
            weekOpenService.updatestatue(0, woid);
        }
        return "redirect:/view/managing_Floor?fid=" + fid;
    }


    /**
     * 删除周开放设置
     *
     * @param model
     * @param woid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteWeek")
    public String deleteWeekopen(Model model, Integer woid, Integer fid) throws Exception {
        weekOpenService.deletestatue(woid);
        return "redirect:/view/managing_Floor?fid=" + fid;
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
        user.setPassword("123456");
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

    /**
     * 进入到密码修改界面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/intoUpdatePassword")
    public String intoUpdatePassword(Model model) throws Exception {
        return "public_page/ResetPassword_ForAdmin";
    }


    //    学习统计
    @RequestMapping("/study_DataStatistics")
    public String study_DataStatistics(Model model, HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数
        List<WeekData> weekDataList = new ArrayList<WeekData>();
        for (int i = 0; i < findweekofbook.size(); i++) {
            List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floor.getFid(), findweekofbook.get(i));  //查看某楼层在某州的预约情况
            WeekData weekData = new WeekData();
            weekData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofweek.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofweek.get(j).getStime(), findbookfloorofweek.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofweek.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofweek.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int) dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }

        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        List<MonthData> monthDataList = new ArrayList<MonthData>();
        for (int i = 0; i < findmonthofbook.size(); i++) {
            List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floor.getFid(), findmonthofbook.get(i));
            MonthData monthData = new MonthData();
            monthData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofmonth.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofmonth.get(j).getStime(), findbookfloorofmonth.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofmonth.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofmonth.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int) dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        model.addAttribute("weekdatas", weekDataList);
        model.addAttribute("monthdatas", monthDataList);

        return "admin_page/Study_DataStatistics";
    }

    /**
     * 获取每月的学习情况
     *
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmonthlearntime")
    public @ResponseBody
    List<MonthData> findmonthlearntim(HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        List<MonthData> monthDataList = new ArrayList<MonthData>();
        for (int i = 0; i < findmonthofbook.size(); i++) {
            List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floor.getFid(), findmonthofbook.get(i));
            MonthData monthData = new MonthData();
            monthData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofmonth.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofmonth.get(j).getStime(), findbookfloorofmonth.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofmonth.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofmonth.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int) dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        return monthDataList;
    }


    /**
     * 获取每周的学习情况
     *
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/getweeklearntime")
    public @ResponseBody
    List<WeekData> findweeklearntim(HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数
        List<WeekData> weekDataList = new ArrayList<WeekData>();
        for (int i = 0; i < findweekofbook.size(); i++) {
            List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floor.getFid(), findweekofbook.get(i));  //查看某楼层在某州的预约情况
            WeekData weekData = new WeekData();
            weekData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofweek.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofweek.get(j).getStime(), findbookfloorofweek.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofweek.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofweek.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int) dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }
        return weekDataList;
    }

}
