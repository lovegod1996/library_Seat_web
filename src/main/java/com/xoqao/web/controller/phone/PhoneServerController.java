package com.xoqao.web.controller.phone;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingCusFloor;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.booking.SeatBookings;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.data.FloorData;
import com.xoqao.web.bean.data.UserData;
import com.xoqao.web.bean.data.WeekData;
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
     *
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
     *
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
    Map<String, Object> adBookingSeat(String sno, String seatNum, String stime, String etime, Integer day) throws Exception {
        Integer disTime = DateUtil.getDisTime(DateUtil.getDate(stime), DateUtil.getDate(etime));
        Map<String, Object> map = new HashMap<String, Object>();
        if (disTime < CommenValue.MAX_LongTime) {
            Seat seatBynumber = seatService.findSeatBynumber(seatNum);
            Floor floor = floorService.findfloorByid(seatBynumber.getFid());
            WeekOpen weekOpen = weekOpenService.findopenFloorday(floor.getFid(), day + 1);
            boolean b = DateUtil.getfollowTime(weekOpen, DateUtil.getDate(stime), DateUtil.getDate(etime));
            if (b) {
                List<Booking> bookSeatBooking = bookingService.findBookSeatBookingday(seatBynumber.getSid(), day);
                boolean checkbooksclash = DateUtil.checkbooksclash(bookSeatBooking, DateUtil.getDate(stime), DateUtil.getDate(etime));
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
        List<BookingCusFloor> bookingCusFloorList = new ArrayList<BookingCusFloor>();
        for (int i = 0; i < bookingBySno.size(); i++) {
            Seat byid = seatService.findByid(bookingBySno.get(i).getSid());
            BookingSeat bookingSeat = new BookingSeat();
            BeanUtils.copyProperties(bookingBySno.get(i), bookingSeat);
            bookingSeat.setColumns(byid.getColumns());
            bookingSeat.setFid(byid.getFid());
            bookingSeat.setLeftside(byid.getLeftside());
            bookingSeat.setRow(byid.getRow());
            bookingSeat.setSeatnumber(byid.getSeatnumber());


            BookingCusFloor bookingCusFloor = new BookingCusFloor();
            BeanUtils.copyProperties(bookingSeat, bookingCusFloor);
            Floor floor = floorService.findfloorByid(byid.getFid());
            bookingCusFloor.setFloor(floor.getEmployer());
            Building buildingById = buildingService.findBuildingById(floor.getBid());
            bookingCusFloor.setBuilding(buildingById.getEmployer());
            bookingCusFloorList.add(bookingCusFloor);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", bookingCusFloorList);
        return map;
    }

    /**
     * 扫描入座
     *
     * @param sno
     * @param seatnumber
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/scanSeat")
    public @ResponseBody
    Map<String, Object> scanSeat(String sno, String seatnumber) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Seat seatBynumber = seatService.findSeatBynumber(seatnumber);
        List<Booking> bookSeatBooking = bookingService.findBookSeatBookingday(seatBynumber.getSid(), 0);  //返回每个座位的所有预约
        Integer seatStatue = DateUtil.findSeatStatue(bookSeatBooking);  //计算当前时间座位状态
        if (seatStatue == 0) {
            map.put("code", 1);
            map.put("message", "当前座位为空，您可以选择预约入座");
            map.put("data", null);
        } else if (seatStatue == 1) {
            Booking findbooknow = DateUtil.findbooknow(bookSeatBooking);
            if (findbooknow != null) {
                Integer disTime = DateUtil.getDisTime(findbooknow.getBstime(), new Date());//计算当前时间与预约时间的时间差
                if (sno.equals(findbooknow.getSno())) {
                    //确定是本人
                    if (disTime > CommenValue.MAX_LATER) {
                        //超过规定时间到达，违约
                        bookingService.updateStime(new Date(), findbooknow.getBid());
                        bookingService.updateEtime(new Date(), 3, 0, 1, findbooknow.getBid());
                        map.put("code", 2);
                        map.put("message", "您已经迟到，迟到时间" + disTime + "分钟，请重新预约");
                        map.put("data", findbooknow);
                    } else {
                        bookingService.updateStime(new Date(), findbooknow.getBid());
                        map.put("code", 0);
                        map.put("message", "入座成功");
                        map.put("data", findbooknow);
                    }
                } else {
                    //不是本人
                    if (disTime > CommenValue.MAX_LATER) {
                        bookingService.updateStime(new Date(), findbooknow.getBid());
                        bookingService.updateEtime(new Date(), 3, 0, 1, findbooknow.getBid());
                        map.put("code", 1);
                        map.put("message", "当前座位为空，您可以选择预约入座");
                        map.put("data", findbooknow);
                    } else {
                        map.put("code", 3);
                        map.put("message", "当前座位已预约，请选择其他座位");
                        map.put("data", findbooknow);
                    }
                }
            }
        } else if (seatStatue == 2) {
            Booking booking = DateUtil.findbooknow(bookSeatBooking);
            if (booking != null) {
                if (sno.equals(booking.getSno())) {
                    //本人扫描
                    map.put("code", 4);
                    map.put("message", "当前正在学习，想要临时离开或离开？");
                    map.put("data", booking);
                } else {
                    bookingService.updateEtime(new Date(), 2, CommenValue.MAX_TIME, 0, booking.getBid());
                    map.put("code", 3);
                    map.put("message", "当前座位已预约，请选择其他座位");
                    map.put("data", booking);
                }
            }
        } else if (seatStatue == 3) {
            Booking booking = DateUtil.findbooknow(bookSeatBooking);
            if (booking != null) {
                Integer disTime = DateUtil.getDisTime(booking.getEtime(), new Date());//计算当前时间与预约时间的时间差
                if (sno.equals(booking.getSno())) {
                    if (disTime > booking.getDelay()) {
                        bookingService.updateEtime(new Date(), 3, booking.getDelay(), 1, booking.getBid());
                        map.put("code", 5);
                        map.put("message", "您的离开时间过长，该座位已释放，请再次预约入座");
                        map.put("data", booking);
                    } else {
                        bookingService.updateEtime(new Date(), 1, booking.getDelay(), 0, booking.getBid());
                        map.put("code", 6);
                        map.put("message", "暂时离开状态已取消，继续学习吧");
                        map.put("data", booking);
                    }
                } else {
                    if (disTime > booking.getDelay()) {
                        bookingService.updateEtime(new Date(), 3, booking.getDelay(), 1, booking.getBid());
                    }
                    map.put("code", 7);
                    map.put("message", "该座位有人正在占用，换用其他座位吧");
                    map.put("data", booking);
                }
            }
        } else {
            map.put("code", 1);
            map.put("message", "当前座位为空，您可以选择预约入座");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 释放座位
     *
     * @param bid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/leaveSeat")
    public @ResponseBody
    Map<String, Object> leaveSeat(Integer bid) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        Booking byid = bookingService.findByid(bid);
        switch (byid.getStatue()) {
            case 0:
                Integer disTime = DateUtil.getDisTime(byid.getBstime(), new Date());
                if (disTime > CommenValue.MAX_LATER) {
                    bookingService.updateStime(new Date(), bid);
                    bookingService.updateEtime(new Date(), 3, 0, 1, bid);
                    map.put("code", 0);
                    map.put("message", "您以迟到");
                    map.put("data", null);
                } else {
                    bookingService.deleteByid(bid);
                    map.put("code", 0);
                    map.put("message", "此次预约移除");
                    map.put("data", null);
                }
                break;
            case 1:
                bookingService.updateEtime(new Date(), 3, 0, 0, bid);
                map.put("code", 0);
                map.put("message", "此次学习结束");
                map.put("data", null);
                break;
            case 2:
                Integer distTime = DateUtil.getDisTime(byid.getEtime(), new Date());
                if (distTime > byid.getDelay()) {
                    bookingService.updateEtime(new Date(), 3, 0, 1, bid);
                    map.put("code", 0);
                    map.put("message", "您超过临时离开时间");
                    map.put("data", null);
                } else {
                    bookingService.updateEtime(new Date(), 3, 0, 0, bid);
                    map.put("code", 0);
                    map.put("message", "离开成功");
                    map.put("data", null);
                }
                break;
            default:
                break;
        }
        return map;
    }

    /**
     * 设置临时离开
     *
     * @param bid
     * @param delay
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/leavetem")
    public @ResponseBody
    Map<String, Object> leavetem(Integer bid, Integer delay) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (delay < CommenValue.MAX_DELAY) {
            bookingService.updateEtime(new Date(), 2, delay, 0, bid);
            map.put("code", 0);
            map.put("message", "临时离开成功");
            map.put("data", null);
        } else {
            map.put("code", 1);
            map.put("message", "不能超过" + CommenValue.MAX_DELAY + "分钟");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 查看当前的预约情况
     *
     * @param sno
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/nowbook")
    public @ResponseBody
    Map<String, Object> nowbook(String sno) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Booking> bookingBySno = bookingService.findBookingBySno(sno, 0);
        Booking findbooknow = DateUtil.findbooknow(bookingBySno);
        BookingCusFloor bookingCusFloor = new BookingCusFloor();
        if (findbooknow != null) {
            BeanUtils.copyProperties(findbooknow, bookingCusFloor);
            Seat byid = seatService.findByid(findbooknow.getSid());
            bookingCusFloor.setColumns(byid.getColumns());
            bookingCusFloor.setFid(byid.getFid());
            bookingCusFloor.setLeftside(byid.getLeftside());
            bookingCusFloor.setRow(byid.getRow());
            bookingCusFloor.setSeatnumber(byid.getSeatnumber());
            Floor floor = floorService.findfloorByid(byid.getFid());
            bookingCusFloor.setFloor(floor.getEmployer());
            Building buildingById = buildingService.findBuildingById(floor.getBid());
            bookingCusFloor.setBuilding(buildingById.getEmployer());
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", bookingCusFloor);
        } else {
            map.put("code", 1);
            map.put("message", "暂无预约记录");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 根据座位号查询今明某天预约情况
     *
     * @param seatnumber
     * @param day
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/seatbook")
    public @ResponseBody
    Map<String, Object> seatbook(String seatnumber, Integer day) throws Exception {
        Seat seatBynumber = seatService.findSeatBynumber(seatnumber);
        List<Booking> bookSeatBooking = bookingService.findBookSeatBookingday(seatBynumber.getSid(), day);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", bookSeatBooking);
        return map;
    }

    /**
     * 根据学号查询所有预约记录
     *
     * @param sno
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userbooks")
    public @ResponseBody
    Map<String, Object> userbooks(String sno) throws Exception {
        List<Booking> finduserbook = bookingService.finduserbook(sno);
        List<BookingCusFloor> bookingCusFloorList = new ArrayList<BookingCusFloor>();
        for (int i = 0; i < finduserbook.size(); i++) {
            BookingCusFloor bookingCusFloor = new BookingCusFloor();
            BeanUtils.copyProperties(finduserbook.get(i), bookingCusFloor);
            Seat byid = seatService.findByid(finduserbook.get(i).getSid());
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", bookingCusFloorList);
        return map;
    }

    /**
     * 获取本月排名前20的学生
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/thisMoonLearnSort")
    public @ResponseBody
    Map<String, Object> getThisMonthLearnSort() throws Exception {
        /**
         * 查询本月学习的时长最多的
         */
        List<String> bookThisMonthSno = bookingService.findBookThisMonthSno();
        List<UserData> userDataList = new ArrayList<UserData>();
        for (int i = 0; i < bookThisMonthSno.size(); i++) {
            User userBySno = userService.findUserBySno(bookThisMonthSno.get(i));
            UserData userData = new UserData();
            userData.setUsername(userBySno.getName());
            userData.setSno(userBySno.getSno());
            userData.setSex(userBySno.getSex());
            userData.setVenue(userBySno.getCollege());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            List<Booking> thisMonthBook = bookingService.findThisMonthBook(bookThisMonthSno.get(i));
            for (int j = 0; j < thisMonthBook.size(); j++) {
                if (thisMonthBook.get(j).getStatue() == 3) {
                    Integer disTime = DateUtil.getDisTime(thisMonthBook.get(j).getStime(), thisMonthBook.get(j).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (thisMonthBook.get(j).getDeal() == 1) {
                        nudeal++;
                    }
                }
            }
            if (thisMonthBook.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) thisMonthBook.size()) * 100;
            }
            userData.setLearntime(learntime / 60);
            userData.setUndeal(nudeal);
            userData.setDealpro((int) dealpro);
            userData.setAllLearn(allLearn);
            userDataList.add(userData);
        }
        Collections.sort(userDataList);
        List<UserData> userDatas = new ArrayList<UserData>();
        for (int i = 0; i < userDataList.size(); i++) {
            if (i < 20) {
                userDatas.add(userDataList.get(i));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", userDatas);
        return map;
    }

    /**
     * 获取某一楼层的座位情况
     *
     * @param fid
     * @param sno
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findFloorHead")
    public @ResponseBody
    Map<String, Object> findfloorHead(Integer fid, String sno) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        FloorData floorData = new FloorData();
        floorData.setFloor(floor.getEmployer());
        List<Seat> openSeatsByFid = seatService.findOpenSeatsByFid(floor.getFid());
        floorData.setSeatCount(openSeatsByFid.size());
        List<Seat> bookSeat = bookingService.findBookSeat(floor.getFid());
        floorData.setHasBook(bookSeat.size());
        floorData.setNoBook(openSeatsByFid.size() - bookSeat.size());
        List<Seat> seats = bookingService.findbookSeatofUpWeek(fid);
        float pro = (seats.size() / (float) openSeatsByFid.size()) * 100;
        floorData.setUpWeekUsePro((double) pro);
        List<Booking> floorBookOfUpWeek = bookingService.findFloorBookOfUpWeek(fid);
        int manCount = 0;
        for (int i = 0; i < floorBookOfUpWeek.size(); i++) {
            User userBySno1 = userService.findUserBySno(floorBookOfUpWeek.get(i).getSno());
            if (userBySno1.getSex() == 0) {
                manCount++;
            }
        }
        float v = (manCount / (float) floorBookOfUpWeek.size()) * 100;
        floorData.setMan((double) v);
        List<Booking> userBookOfUpWeek = bookingService.findUserBookOfUpWeek(fid, sno);
        floorData.setMybook(userBookOfUpWeek.size());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", floorData);
        return map;
    }

    /**
     * 查找往周预约记录结果统计
     * @param sno
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findBookWeek")
    public @ResponseBody
    Map<String, Object> findBookWeek(String sno) throws Exception {
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数
        List<WeekData> weekDataList = new ArrayList<WeekData>();
        for (int i = 0; i < findweekofbook.size(); i++) {
            WeekData weekData = new WeekData();
            List<Booking> bookings = bookingService.findsaomeWeekBookUser(findweekofbook.get(i), sno);
            weekData.setWeek(findweekofbook.get(i));
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < bookings.size(); j++) {
                if (bookings.get(j).getStatue() == 3) {
                    Integer disTime = DateUtil.getDisTime(bookings.get(j).getStime(), bookings.get(j).getEtime());
                    learntime = learntime + disTime;
                    if (disTime > 0) {
                        allLearn++;
                    }
                    if (bookings.get(j).getDeal() == 1) {
                        nudeal++;
                    }
                }
            }
            if (bookings.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) bookings.size()) * 100;
            }
            weekData.setLearntime(learntime/60);
            weekData.setAllLearn(allLearn);
            weekData.setUndeal(nudeal);
            weekData.setDealpro((int) dealpro);
            weekDataList.add(weekData);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", weekDataList);
        return map;
    }

}
