package com.xoqao.web.controller.phone;

import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.bean.weekopen.WeekOpenCus;
import com.xoqao.web.service.*;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/24.
 * Email:dx96_j@163.com
 */
@Controller
@RequestMapping("/phone")
public class PhoneServerController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;
    @Autowired
    private WeekOpenService weekOpenService;

    /**
     * 用户登录
     *
     * @param sno
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public
    @ResponseBody
    Map<String, Object> login(String sno, String password) throws Exception {
        User userBySno = userService.findUserBySno(sno);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userBySno != null && password.equals(userBySno.getPassword())) {
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", userBySno);
        } else {
            map.put("code", 1);
            map.put("message", "请输入正确参数");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 获取所有资讯
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allNotice", method = {RequestMethod.GET})
    public @ResponseBody
    Map<String, Object> allNotice() throws Exception {
        List<Notice> allNotice = noticeService.findAllNotice();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", allNotice);
        return map;
    }

    /**
     * 获取所有的楼层
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allBuilding")
    public @ResponseBody
    Map<String, Object> findAllBuilding() throws Exception {
        List<Building> allBuilding = buildingService.findAllBuilding();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", allBuilding);
        return map;
    }

    /**
     * 根据楼id查询层
     *
     * @param bid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allFloors")
    public @ResponseBody
    Map<String, Object> findAllFloorsByid(Integer bid) throws Exception {
        List<Floor> floors = floorService.findfloorsBybid(bid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", floors);
        return map;
    }

    /**
     * 获取今天开放场馆时间段
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/opentoday")
    public @ResponseBody
    Map<String, Object> findopenToday() throws Exception {
        List<WeekOpen> findopentody = weekOpenService.findopentody();
        Iterator<WeekOpen> iterator = findopentody.iterator();
        List<WeekOpenCus> weekOpenCuses = new ArrayList<WeekOpenCus>();
        while (iterator.hasNext()) {
            WeekOpen next = iterator.next();
            Floor floor = floorService.findfloorByid(next.getLid());
            Building buildingById = buildingService.findBuildingById(floor.getBid());
            WeekOpenCus weekOpenCus = new WeekOpenCus();
            BeanUtils.copyProperties(next, weekOpenCus);
            weekOpenCus.setFloor(floor.getEmployer());
            weekOpenCus.setBuilding(buildingById.getEmployer());
            weekOpenCuses.add(weekOpenCus);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", weekOpenCuses);
        return map;
    }

    /**
     * 查找今天某场馆的开放时间
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/openfloortoday")
    public @ResponseBody
    Map<String, Object> findopenToday(Integer fid) throws Exception {
        WeekOpen weekOpen = weekOpenService.findopenFloortoday(fid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", weekOpen);
        return map;
    }

}
