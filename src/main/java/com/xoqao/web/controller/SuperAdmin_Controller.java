package com.xoqao.web.controller;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.data.MonthData;
import com.xoqao.web.bean.data.WeekData;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.service.BookingService;
import com.xoqao.web.service.BuildingService;
import com.xoqao.web.service.FloorService;
import com.xoqao.web.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
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

    @RequestMapping("/seat_DataStatistics_SuperAdmin")
    public String seat_DataStatistics_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Seat_DataStatistics_SuperAdmin";
    }

    @RequestMapping("/study_DataStatistics_SuperAdmin")
    public String study_DataStatistics_SuperAdmin(Model model) throws Exception {
        return "superadmin_page/Study_DataStatistics_SuperAdmin";
    }

    @RequestMapping("/seat_DataStatistics_ForEachBuilding")
    public String seat_DataStatistics_ForEachBuilding(Model model,Integer fid) throws Exception {

        Floor floor =floorService.findfloorByid(fid);
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数
        List<WeekData> weekDataList = new ArrayList<WeekData>();
        for (int i = 0; i < findweekofbook.size(); i++) {
            List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floor.getFid(), findweekofbook.get(i));  //查看某楼层在某州的预约情况
            WeekData weekData = new WeekData();
            weekData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            Integer dealpro = 0;
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
                dealpro = (nudeal / findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro(dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }

        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        List<MonthData> monthDataList = new ArrayList<MonthData>();
        for (int i = 0; i <findmonthofbook.size() ; i++) {
            List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floor.getFid(), findmonthofbook.get(i));
            MonthData monthData = new MonthData();
            monthData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            Integer dealpro = 0;
            for (int j = 0; j <findbookfloorofmonth.size() ; j++) {
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
                dealpro = (nudeal / findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro(dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        model.addAttribute("weekdatas", weekDataList);
        model.addAttribute("monthdatas", monthDataList);
       model.addAttribute("floor",floor);

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
            Integer dealpro = 0;
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
                dealpro = (nudeal / books) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro(dealpro);
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
            Integer dealpro = 0;
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
                dealpro = (nudeal / books) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro(dealpro);
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
            Integer dealpro = 0;
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
                dealpro = (nudeal / books) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro(dealpro);
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
            Integer dealpro = 0;
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
                dealpro = (nudeal / books) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro(dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        return monthDataList;
    }

    /**
     * 获取每月的学习情况
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmonthlearntimefloor")
    public @ResponseBody
    List<MonthData> findmonthlearntimfloor(Integer fid) throws Exception {
        Floor floor =floorService.findfloorByid(fid);
        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        List<MonthData> monthDataList = new ArrayList<MonthData>();
        for (int i = 0; i <findmonthofbook.size() ; i++) {
            List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floor.getFid(), findmonthofbook.get(i));
            MonthData monthData = new MonthData();
            monthData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            Integer dealpro = 0;
            for (int j = 0; j <findbookfloorofmonth.size() ; j++) {
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
                dealpro = (nudeal / findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro(dealpro);
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
            Integer dealpro = 0;
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
                dealpro = (nudeal / findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro(dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }
        return weekDataList;
    }

}
