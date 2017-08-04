package com.xoqao.web.controller;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.booking.BookingUser;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.building.BuildingCusFloors;
import com.xoqao.web.bean.data.CollgeData;
import com.xoqao.web.bean.data.MonthData;
import com.xoqao.web.bean.data.UserData;
import com.xoqao.web.bean.data.WeekData;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.*;

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
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private SeatService seatService;

    @RequestMapping("/index_SuperAdmin")
    public String index_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Index_SuperAdmin";
    }

    @RequestMapping("/leftmenu_SuperAdmin")
    public String leftmenu_SuperAdmin(Model model) throws Exception {
        List<Building> allBuilding = buildingService.findAllBuilding();
        List<BuildingCusFloors> buildingCusFloorsList = new ArrayList<BuildingCusFloors>();
        for (int i = 0; i < allBuilding.size(); i++) {
            BuildingCusFloors buildingCusFloors = new BuildingCusFloors();
            BeanUtils.copyProperties(allBuilding.get(i), buildingCusFloors);
            List<Floor> floors = floorService.findfloorsBybid(allBuilding.get(i).getBid());
            buildingCusFloors.setFloors(floors);
            buildingCusFloorsList.add(buildingCusFloors);
        }
        model.addAttribute("buidingfloors", buildingCusFloorsList);
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
        Building building1 = allBuilding.get(allBuilding.size() - 1);
        Calendar a = Calendar.getInstance();
        Integer year = a.get(Calendar.YEAR);
        Random random = new Random();
        Building building = new Building();
        building.setAccountnumber(year + "" + random.nextInt(100) + "" + (building1.getBid() + 1));
        building.setEmployer(libaray);
        building.setName(admin);
        building.setPassword("123456");
        try {
            buildingService.insertLibaray(building);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Building> allBuilding1 = buildingService.findAllBuilding();
        model.addAttribute("allbuidings", allBuilding1);
        return "superadmin_page/Managing_Building_SuperAdmin";
    }

    /**
     * 删除图书馆信息
     *
     * @param model
     * @param bid
     * @return
     * @throws Exception
     */
    @RequestMapping("/buidingdelete")
    public String deleteBuliding(Model model, Integer bid) throws Exception {
        buildingService.deleteLibaray(bid);
        List<Building> allBuilding = buildingService.findAllBuilding();
        model.addAttribute("allbuidings", allBuilding);
        return "superadmin_page/Managing_Building_SuperAdmin";
    }

    /**
     * 修改图书馆信息
     *
     * @param model
     * @param bid
     * @param libaray
     * @param admin
     * @return
     * @throws Exception
     */
    @RequestMapping("/editBuilding")
    public String editBuilding(Model model, Integer bid, String libaray, String admin) throws Exception {
        Building buildingById = buildingService.findBuildingById(bid);
        buildingById.setEmployer(libaray);
        buildingById.setName(admin);
        buildingService.updateBuilding(buildingById);
        List<Building> allBuilding1 = buildingService.findAllBuilding();
        model.addAttribute("allbuidings", allBuilding1);
        return "superadmin_page/Managing_Building_SuperAdmin";
    }

    /**
     * 进入楼层管理
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/managing_Floor_SuperAdmin")
    public String managing_Floor_SuperAdmin(Model model, Integer bid) throws Exception {

        Building buildingById = buildingService.findBuildingById(bid);

        List<Floor> floors = floorService.findfloorsBybid(bid);

        model.addAttribute("floors", floors);
        if (floors.size() == 0) {
            model.addAttribute("nullList", "暂无楼层信息");
        }
        model.addAttribute("building", buildingById);
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    /**
     * 删除楼层
     *
     * @param model
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping("deletefloor")
    public String floorsDelete(Model model, Integer bid, Integer fid) throws Exception {
        floorService.deletefloor(fid);
        Building buildingById = buildingService.findBuildingById(bid);
        List<Floor> floors = floorService.findfloorsBybid(bid);
        model.addAttribute("floors", floors);
        model.addAttribute("building", buildingById);
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    /**
     * 添加楼层信息
     *
     * @param model
     * @param bid
     * @param floorname
     * @param floorsort
     * @param username
     * @return
     * @throws Exception
     */
    @RequestMapping("/adfloorSub")
    public String adfloorsub(Model model, Integer bid, Integer floorname, String floorsort, String username) throws Exception {
        Floor floor = new Floor();
        List<Floor> floors = floorService.findfloorsBybid(bid);
        Building buildingById = buildingService.findBuildingById(bid);
        String number = null;
        if (floors.size() > 0) {
            number = buildingById.getAccountnumber() + "00" + (floors.get(floors.size() - 1).getFid() + 1);
        } else {
            number = buildingById.getAccountnumber() + "00" + 1;
        }
        floor.setAccountnumber(number);
        floor.setBid(bid);
        floor.setEmployer(floorsort);
        floor.setFloor(floorname);
        floor.setName(username);
        floor.setPassword("123456");
        try {
            floorService.insertFloors(floor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Floor> floors1 = floorService.findfloorsBybid(bid);
        model.addAttribute("floors", floors1);
        if (floors1.size() == 0) {
            model.addAttribute("nullList", "暂无楼层信息请添加信息");
        }
        model.addAttribute("building", buildingById);
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    /**
     * 修改楼层信息
     *
     * @param model
     * @param floorname
     * @param floorsort
     * @param username
     * @return
     * @throws Exception
     */
    @RequestMapping("/editfllorSub")
    public String editfloor(Model model, Integer fid, Integer floorname, String floorsort, String username) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        floor.setFloor(floorname);
        floor.setEmployer(floorsort);
        floor.setName(username);
        floorService.updateFloor(floor);
        Building buildingById = buildingService.findBuildingById(floor.getBid());
        List<Floor> floors1 = floorService.findfloorsBybid(floor.getBid());
        model.addAttribute("floors", floors1);
        if (floors1.size() == 0) {
            model.addAttribute("nullList", "暂无楼层信息请添加信息");
        }
        model.addAttribute("building", buildingById);
        return "superadmin_page/Managing_Floor_SuperAdmin";
    }

    @RequestMapping("/managing_User_SuperAdmin")
    public String managing_User_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Managing_User_SuperAdmin";
    }

//    @RequestMapping("/seat_DataStatistics_SuperAdmin")
//    public String seat_DataStatistics_SuperAdmin(Model model) throws Exception {
//        return "superadmin_page/Seat_DataStatistics_SuperAdmin";
//    }

    /**
     * 各院系学习情况统计
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/study_DataStatistics_SuperAdmin")
    public String study_DataStatistics_SuperAdmin(Model model) throws Exception {
        List<String> allCollege = userService.findAllCollege();
        List<CollgeData> collgeDataList = new ArrayList<CollgeData>();
        for (int i = 0; i < allCollege.size(); i++) {
            CollgeData collgeData = new CollgeData();
            collgeData.setVenue(allCollege.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> bookings = bookingService.findbookofCollege(allCollege.get(i));
            for (int j = 0; j < bookings.size(); j++) {
                Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (bookings.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            List<User> users = userService.findallStudentbyCollege(allCollege.get(i));
            collgeData.setAllLearn(allLearn);
            collgeData.setDealpro((int) dealpro);
            collgeData.setLearntime(learntime / 60);
            collgeData.setUndeal(nudeal);
            collgeData.setStudents(users.size());
            collgeDataList.add(collgeData);
        }
        model.addAttribute("collegeDatas", collgeDataList);

        return "superadmin_page/Study_DataStatistics_SuperAdmin";
    }

    /**
     * 获取各学院学习情况
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getcollegelearn")
    public @ResponseBody
    List<CollgeData> findcollegelearntim() throws Exception {
        List<String> allCollege = userService.findAllCollege();
        List<CollgeData> collgeDataList = new ArrayList<CollgeData>();
        for (int i = 0; i < allCollege.size(); i++) {
            CollgeData collgeData = new CollgeData();
            collgeData.setVenue(allCollege.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> bookings = bookingService.findbookofCollege(allCollege.get(i));
            for (int j = 0; j < bookings.size(); j++) {
                Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (bookings.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            List<User> users = userService.findallStudentbyCollege(allCollege.get(i));
            collgeData.setAllLearn(allLearn);
            collgeData.setDealpro((int) dealpro);
            collgeData.setLearntime(learntime / 60);
            collgeData.setUndeal(nudeal);
            collgeData.setStudents(users.size());
            collgeDataList.add(collgeData);
        }
        return collgeDataList;
    }

    /**
     * 获取院系内专业的学习数据
     *
     * @param model
     * @param college
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmajorlearn")
    public String getmajorlearn(Model model, String college) throws Exception {
        List<String> majorByCollege = userService.findMajorByCollege(college);
        List<CollgeData> majorDataList = new ArrayList<CollgeData>();

        for (int i = 0; i < majorByCollege.size(); i++) {
            CollgeData collgeData = new CollgeData();
            collgeData.setVenue(majorByCollege.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> bookings = bookingService.findbookwithCollegeAndMajor(college, majorByCollege.get(i));
            for (int j = 0; j < bookings.size(); j++) {
                Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (bookings.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            List<User> users = userService.findstudentbycollegeandmajor(college, majorByCollege.get(i));
            collgeData.setAllLearn(allLearn);
            collgeData.setDealpro((int) dealpro);
            collgeData.setLearntime(learntime / 60);
            collgeData.setUndeal(nudeal);
            collgeData.setStudents(users.size());
            majorDataList.add(collgeData);
        }
        model.addAttribute("college", college);
        model.addAttribute("majordatas", majorDataList);
        return "superadmin_page/Study_Major_Data_SuperAdmin";
    }


    /**
     * 获取专业学习详情
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmajorslearn")
    public @ResponseBody
    List<CollgeData> findmajorslearn(String college) throws Exception {
        List<String> majorByCollege = userService.findMajorByCollege(college);
        List<CollgeData> majorDataList = new ArrayList<CollgeData>();

        for (int i = 0; i < majorByCollege.size(); i++) {
            CollgeData collgeData = new CollgeData();
            collgeData.setVenue(majorByCollege.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> bookings = bookingService.findbookwithCollegeAndMajor(college, majorByCollege.get(i));
            for (int j = 0; j < bookings.size(); j++) {
                Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (bookings.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            List<User> users = userService.findstudentbycollegeandmajor(college, majorByCollege.get(i));
            collgeData.setAllLearn(allLearn);
            collgeData.setDealpro((int) dealpro);
            collgeData.setLearntime(learntime / 60);
            collgeData.setUndeal(nudeal);
            collgeData.setStudents(users.size());
            majorDataList.add(collgeData);
        }
        return majorDataList;
    }

    /**
     * 获取班级信息
     *
     * @param model
     * @param college
     * @param major
     * @return
     * @throws Exception
     */
    @RequestMapping("/getclassLearn")
    public String getclasslearn(Model model, String college, String major) throws Exception {
        List<String> classes = userService.findclassbymajorcollege(college, major);
        List<CollgeData> classDataList = new ArrayList<CollgeData>();
        for (int i = 0; i < classes.size(); i++) {
            CollgeData collgeData = new CollgeData();
            collgeData.setVenue(classes.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> bookings = bookingService.findbookWithCollegeMajorClass(college, major, classes.get(i));
            for (int j = 0; j < bookings.size(); j++) {
                Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (bookings.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            List<User> users = userService.findstudentbyclassmajor(college, major, classes.get(i));
            collgeData.setAllLearn(allLearn);
            collgeData.setDealpro((int) dealpro);
            collgeData.setLearntime(learntime / 60);
            collgeData.setUndeal(nudeal);
            collgeData.setStudents(users.size());
            classDataList.add(collgeData);
        }
        model.addAttribute("college", college);
        model.addAttribute("major", major);
        model.addAttribute("classdatas", classDataList);
        return "superadmin_page/Study_Class_Data_SuperAdmin";
    }

    @RequestMapping("/getClasseslearn")
    public @ResponseBody
    List<CollgeData> findClasseslearn(String college, String major) throws Exception {
        List<String> classes = userService.findclassbymajorcollege(college, major);
        List<CollgeData> classDataList = new ArrayList<CollgeData>();
        for (int i = 0; i < classes.size(); i++) {
            CollgeData collgeData = new CollgeData();
            collgeData.setVenue(classes.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> bookings = bookingService.findbookWithCollegeMajorClass(college, major, classes.get(i));
            for (int j = 0; j < bookings.size(); j++) {
                Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (bookings.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            List<User> users = userService.findstudentbyclassmajor(college, major, classes.get(i));
            collgeData.setAllLearn(allLearn);
            collgeData.setDealpro((int) dealpro);
            collgeData.setLearntime(learntime / 60);
            collgeData.setUndeal(nudeal);
            collgeData.setStudents(users.size());
            classDataList.add(collgeData);
        }
        return classDataList;
    }

    /**
     * 获取班级学生学习情况
     *
     * @param model
     * @param college
     * @param major
     * @param classes
     * @return
     * @throws Exception
     */
    @RequestMapping("/getStudentsLearn")
    public String getstudentlearn(Model model, String college, String major, String classes) throws Exception {
        List<User> users = userService.findstudentbyclassmajor(college, major, classes);
        List<UserData> userDataList = new ArrayList<UserData>();
        for (int i = 0; i < users.size(); i++) {
            List<Booking> finduserbook = bookingService.finduserbook(users.get(i).getSno());
            UserData userData = new UserData();
            userData.setSex(users.get(i).getSex());
            userData.setSno(users.get(i).getSno());
            userData.setUsername(users.get(i).getName());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < finduserbook.size(); j++) {
                if (finduserbook.get(j).getStatue() == 3) {
                    Integer disTime = DateUtil.getDisTime(finduserbook.get(j).getStime(), finduserbook.get(j).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (finduserbook.get(j).getDeal() == 1) {
                        nudeal++;
                    }
                }
            }
            if (finduserbook.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) finduserbook.size()) * 100;
            }
            userData.setLearntime(learntime / 60);
            userData.setUndeal(nudeal);
            userData.setDealpro((int) dealpro);
            userData.setAllLearn(allLearn);
            userDataList.add(userData);
        }
        model.addAttribute("college", college);
        model.addAttribute("major", major);
        model.addAttribute("classes", classes);
        model.addAttribute("studentsDatas", userDataList);
        return "superadmin_page/Study_Students_Data_SuperAdmin";
    }

    @RequestMapping("/seat_DataStatistics_ForEachBuilding")
    public String seat_DataStatistics_ForEachBuilding(Model model, Integer fid) throws Exception {

        Floor floor = floorService.findfloorByid(fid);
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
                dealpro = (nudeal / (float)findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int)dealpro);
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
                dealpro = (nudeal / (float)findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int)dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        model.addAttribute("weekdatas", weekDataList);
        model.addAttribute("monthdatas", monthDataList);
        model.addAttribute("floor", floor);

        return "superadmin_page/Seat_DataStatistics_ForEachBuilding";
    }


    /**
     * 查询整馆学习情况
     *
     * @param model
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/study_DataStatistics_ForEachBuilding")
    public String study_DataStatistics_ForEachBuilding(Model model, HttpSession httpSession) throws Exception {
        Building building = (Building) httpSession.getAttribute("admin");
        List<Floor> floors = floorService.findfloorsBybid(building.getBid());
        /**
         * 获取每周的统计数据
         */
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数

        List<WeekData> weekDataList = new ArrayList<WeekData>();  //所有周的预约统计


        for (int i = 0; i < findweekofbook.size(); i++) {
            WeekData weekData = new WeekData();
            weekData.setVenue(building.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            Integer books = 0;
            for (int j = 0; j < floors.size(); j++) {
                List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floors.get(j).getFid(), findweekofbook.get(i));
                for (int k = 0; k < findbookfloorofweek.size(); k++) {
                    Integer disTime = DateUtil.getDisTime(findbookfloorofweek.get(k).getStime(), findbookfloorofweek.get(k).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (findbookfloorofweek.get(k).getDeal() == 1) {
                        nudeal++;
                    }
                }
                books = books + findbookfloorofweek.size();
            }

            if (books > 0) {
                dealpro = (nudeal / (float)books) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int)dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);    //添加每周的学习统计数据
        }
        model.addAttribute("weekdatas", weekDataList);
        /**
         * 获取每月的统计数据
         */

        List<MonthData> monthDataList = new ArrayList<MonthData>();   //每月的统计结果数据
        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        for (int i = 0; i < findmonthofbook.size(); i++) {

            MonthData monthData = new MonthData();
            monthData.setVenue(building.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            Integer books = 0;
            for (int j = 0; j < floors.size(); j++) {
                List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floors.get(j).getFid(), findmonthofbook.get(i));
                for (int k = 0; k < findbookfloorofmonth.size(); k++) {
                    Integer disTime = DateUtil.getDisTime(findbookfloorofmonth.get(k).getStime(), findbookfloorofmonth.get(k).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (findbookfloorofmonth.get(k).getDeal() == 1) {
                        nudeal++;
                    }
                }
                books = books + findbookfloorofmonth.size();
            }
            if (books > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float)books) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int)dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        model.addAttribute("monthdatas", monthDataList);
        return "superadmin_page/Study_DataStatistics_ForEachBuilding";
    }

    /**
     * 查询每周整馆的学习情况
     *
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/getweekliblearntime")
    public @ResponseBody
    List<WeekData> findweekliblearntim(HttpSession httpSession) throws Exception {
        Building building = (Building) httpSession.getAttribute("admin");
        List<Floor> floors = floorService.findfloorsBybid(building.getBid());
        /**
         * 获取每周的统计数据
         */
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数

        List<WeekData> weekDataList = new ArrayList<WeekData>();  //所有周的预约统计


        for (int i = 0; i < findweekofbook.size(); i++) {
            WeekData weekData = new WeekData();
            weekData.setVenue(building.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            Integer books = 0;
            for (int j = 0; j < floors.size(); j++) {
                List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floors.get(j).getFid(), findweekofbook.get(i));
                for (int k = 0; k < findbookfloorofweek.size(); k++) {
                    Integer disTime = DateUtil.getDisTime(findbookfloorofweek.get(k).getStime(), findbookfloorofweek.get(k).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (findbookfloorofweek.get(k).getDeal() == 1) {
                        nudeal++;
                    }
                }
                books = books + findbookfloorofweek.size();
            }

            if (books > 0) {
                dealpro = (nudeal / (float)books) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int)dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);    //添加每周的学习统计数据
        }
        return weekDataList;
    }


    /**
     * 查询每月整馆学习情况
     *
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmonthliblearntime")
    public @ResponseBody
    List<MonthData> findmonthliblearntim(HttpSession httpSession) throws Exception {

        Building building = (Building) httpSession.getAttribute("admin");
        List<Floor> floors = floorService.findfloorsBybid(building.getBid());
        List<MonthData> monthDataList = new ArrayList<MonthData>();   //每月的统计结果数据
        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        for (int i = 0; i < findmonthofbook.size(); i++) {

            MonthData monthData = new MonthData();
            monthData.setVenue(building.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            Integer books = 0;
            for (int j = 0; j < floors.size(); j++) {
                List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floors.get(j).getFid(), findmonthofbook.get(i));
                for (int k = 0; k < findbookfloorofmonth.size(); k++) {
                    Integer disTime = DateUtil.getDisTime(findbookfloorofmonth.get(k).getStime(), findbookfloorofmonth.get(k).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (findbookfloorofmonth.get(k).getDeal() == 1) {
                        nudeal++;
                    }
                }
                books = books + findbookfloorofmonth.size();
            }
            if (books > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal /(float) books) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int)dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        return monthDataList;
    }

    /**
     * 获取每月的学习情况
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmonthlearntimefloor")
    public @ResponseBody
    List<MonthData> findmonthlearntimfloor(Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
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
                dealpro = (nudeal / (float)findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int)dealpro);
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
     * @return
     * @throws Exception
     */
    @RequestMapping("/getweeklearntimefloor")
    public @ResponseBody
    List<WeekData> findweeklearntimfloor(Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
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
                dealpro = (nudeal / (float)findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int)dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }
        return weekDataList;
    }


    /**
     * 进入查看学生学习情况页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/intofindStudent")
    public String studentstudy(Model model) {
        return "superadmin_page/One_Student_SuperAdmin";
    }

    /**
     * 根据学号查看学习情况
     *
     * @param sno
     * @return
     * @throws Exception
     */
    @RequestMapping("/findStudentStuday")
    public @ResponseBody
    UserData findStudentStuday(String sno) throws Exception {
        User userBySno = userService.findUserBySno(sno);
        List<Booking> finduserbook = bookingService.finduserbook(userBySno.getSno());
        UserData userData = new UserData();
        userData.setSex(userBySno.getSex());
        userData.setSno(userBySno.getSno());
        userData.setUsername(userBySno.getName());
        Integer learntime = 0;
        Integer allLearn = 0;
        Integer nudeal = 0;
        float dealpro = 0;
        for (int j = 0; j < finduserbook.size(); j++) {
            if (finduserbook.get(j).getStatue() == 3) {
                Integer disTime = DateUtil.getDisTime(finduserbook.get(j).getStime(), finduserbook.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (finduserbook.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
        }
        if (finduserbook.size() > 0) {  //查看某周的预约是否未零
            dealpro = (nudeal / (float) finduserbook.size()) * 100;
        }
        userData.setLearntime(learntime / 60);
        userData.setUndeal(nudeal);
        userData.setDealpro((int) dealpro);
        userData.setAllLearn(allLearn);
        return userData;
    }

    /**
     * 获取学生学习记录
     * @param sno
     * @return
     * @throws Exception
     */
    @RequestMapping("/findStudentBookings")
    public @ResponseBody
    List<BookingUser> findStudentBookings(String sno) throws Exception {
        User userBySno = userService.findUserBySno(sno);
        List<Booking> finduserbook = bookingService.finduserbook(sno);
        List<BookingUser> bookingSeatList = new ArrayList<BookingUser>();
        for (int i = 0; i < finduserbook.size(); i++) {
            BookingUser bookingSeat = new BookingUser();
            BeanUtils.copyProperties(finduserbook.get(i), bookingSeat);
            Seat seat = seatService.findByid(finduserbook.get(i).getSid());
            bookingSeat.setSeatnumber(seat.getSeatnumber());
            bookingSeat.setRow(seat.getRow());
            bookingSeat.setLeftside(seat.getLeftside());
            bookingSeat.setFid(seat.getFid());
            bookingSeat.setColumns(seat.getColumns());
            bookingSeat.setName(userBySno.getName());
            Floor floor = floorService.findfloorByid(seat.getFid());
            bookingSeat.setFloor(floor.getEmployer());
            bookingSeatList.add(bookingSeat);
        }
        return bookingSeatList;
    }


}
