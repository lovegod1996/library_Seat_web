package com.xoqao.web.controller.phone;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.booking.SeatBookings;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.bean.weekopen.WeekOpenCus;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.DateUtil;
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
    @Autowired
    private SeatService seatService;
    @Autowired
    private BookingService bookingService;

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
     *
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

    /**
     * 查找今明天所有开放楼层状态
     *
     * @param bid
     * @param day
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/openfloorsday")
    public @ResponseBody
    Map<String, Object> findopendayFloors(Integer bid, Integer day) throws Exception {
        List<WeekOpen> findopenfloorsday = weekOpenService.findopenfloorsday(day, bid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", findopenfloorsday);
        return map;
    }

    /**
     * 查询今明某天的某个场馆的开放时间段
     *
     * @param fid
     * @param day
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/openfloorday")
    public @ResponseBody
    Map<String, Object> findopendayFloor(Integer fid, Integer day) throws Exception {
        WeekOpen weekOpen = weekOpenService.findopenFloorday(fid, day);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", weekOpen);
        return map;
    }

    /**
     * 查找某楼层今明某天所有可预约座位
     *
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/floorSeats")
    public @ResponseBody
    Map<String, Object> findFloorseat(Integer fid, Integer day) throws Exception {
        List<Seat> seatsByFid = seatService.findOpenSeatsByFid(fid);
        List<SeatBookings> seatBookinges = new ArrayList<SeatBookings>();
        for (int i = 0; i < seatsByFid.size(); i++) {
            SeatBookings seatBookings = new SeatBookings();
            BeanUtils.copyProperties(seatsByFid.get(i), seatBookings);
            List<Booking> bookSeatBooking = bookingService.findBookSeatBookingday(seatsByFid.get(i).getSid(), day);  //返回每个座位的所有预约
            Integer seatStatue = DateUtil.findSeatStatue(bookSeatBooking);  //计算当前时间座位状态
            seatBookings.setSeatStatue(seatStatue);
            seatBookings.setBookings(bookSeatBooking);
            seatBookinges.add(seatBookings);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", seatBookinges);
        return map;
    }

    /**
     * 根据座位id查询座位
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/seat/id")
    public @ResponseBody
    Map<String, Object> findFloorseat(Integer sid) throws Exception {
        Seat byid = seatService.findByid(sid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", byid);
        return map;
    }

    /**
     * 根据楼层id查询楼层
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/floor/id")
    public @ResponseBody
    Map<String, Object> findFloor(Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", floor);
        return map;
    }

    /**
     * 用户添加预约
     *
     * @param sno
     * @param seatNum
     * @param stime
     * @param etime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addBookSeat")
    public @ResponseBody
    Map<String, Object> adBookingSeat(String sno, String seatNum, String stime, String etime) throws Exception {
        Integer disTime = DateUtil.getDisTime(new Date(), DateUtil.getDate(etime));
        Map<String, Object> map = new HashMap<String, Object>();
        if (disTime < CommenValue.MAX_LongTime) {
            Seat seatBynumber = seatService.findSeatBynumber(seatNum);
            Floor floor = floorService.findfloorByid(seatBynumber.getFid());
            WeekOpen weekOpen = weekOpenService.findopenFloortoday(floor.getFid());
            boolean b = DateUtil.getfollowTime(weekOpen, DateUtil.getDate(stime), DateUtil.getDate(etime));
            if (b) {
                List<Booking> bookSeatBooking = bookingService.findBookSeatBooking(seatBynumber.getSid());
                boolean checkbooksclash = DateUtil.checkbooksclash(bookSeatBooking, new Date(), DateUtil.getDate(etime));
                if (checkbooksclash) {
                    map.put("code", 1);
                    map.put("message", "您选择的时间段已经被占用");
                    map.put("data", null);
                } else {
                    //查看近两天的学生预约记录
                    List<Booking> bookingBySno = bookingService.findBookingBySno(sno, 0);
                    List<Booking> bookingBySno2 = bookingService.findBookingBySno(sno, 1);
                    for (int i = 0; i < bookingBySno2.size(); i++) {
                        bookingBySno.add(bookingBySno2.get(i));
                    }
                    boolean checkbooksclash1 = DateUtil.checkbooksclash(bookingBySno, DateUtil.getDate(stime), DateUtil.getDate(etime));
                    if (checkbooksclash1) {
                        map.put("code", 1);
                        map.put("message", "您选择的时间段您已预约过");
                        map.put("data", null);
                    } else {
                        Booking booking = new Booking();
                        booking.setSno(sno);
                        booking.setBstime(DateUtil.getDate(stime));
                        booking.setBetime(DateUtil.getDate(etime));
                        booking.setSid(seatBynumber.getSid());
                        bookingService.insertbooking(booking);
                        map.put("code", 0);
                        map.put("message", "预约成功");
                        map.put("data", null);
                    }
                }
            } else {
                map.put("code", 1);
                map.put("message", "请注意场馆开放时间");
                map.put("data", null);
            }
        } else {
            map.put("code", 1);
            map.put("message", "选择时间超过" + (CommenValue.MAX_LongTime / 60) + "小时");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 根据学号查询今明某天的预约记录
     *
     * @param sno
     * @param day
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findbook")
    public @ResponseBody
    Map<String, Object> findbook(String sno, Integer day) throws Exception {
        List<Booking> bookingBySno = bookingService.findBookingBySno(sno, day);
        List<BookingSeat> bookingSeats=new ArrayList<BookingSeat>();
        for (int i = 0; i <bookingBySno.size() ; i++) {
            Seat byid = seatService.findByid(bookingBySno.get(i).getSid());
            BookingSeat bookingSeat=new BookingSeat();
            bookingSeat.setColumns(byid.getColumns());
            bookingSeat.setFid(byid.getFid());
            bookingSeat.setLeftside(byid.getLeftside());
            bookingSeat.setRow(byid.getRow());
            bookingSeat.setSeatnumber(byid.getSeatnumber());
            bookingSeats.add(bookingSeat);
        }
         Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", bookingSeats);
        return map;
    }




}
