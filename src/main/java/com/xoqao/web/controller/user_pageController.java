package com.xoqao.web.controller;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingCusFloor;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.booking.SeatBookings;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.data.UserData;
import com.xoqao.web.bean.deal.UnDeal;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.news.News;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.seat.SeatState;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.bean.weekopen.WeekOpenCus;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class user_pageController {


    @Autowired
    private UserLearnService userLearnService;
    @Autowired
    private SeatService seatService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private WeekOpenService weekOpenService;

    @Autowired
    private FloorService floorService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private UndealService undealService;

    @RequestMapping("/main_User")
    public String main_User(Model model) throws Exception {
        List<Notice> allNoticetop = noticeService.findAllNoticetop();
        model.addAttribute("noticestop", allNoticetop);
        /**
         * 当天开放的图书馆层
         */
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
        List<Building> allBuilding = buildingService.findAllBuilding();
        model.addAttribute("buildings", allBuilding);

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
        model.addAttribute("userdatas", userDataList);

        model.addAttribute("weekopens", weekOpenCuses);
        return "user_page/Main_User";
    }

    /**
     * 获取当前场馆座位状态
     *
     * @param building
     * @return
     * @throws Exception
     */
    @RequestMapping("/getSeatData")
    public @ResponseBody
    List<SeatState> getSeatDate(Integer building) throws Exception {
        List<SeatState> seatStates = new ArrayList<SeatState>();
        List<Floor> floors = floorService.findfloorsBybid(building);
        for (int i = 0; i < floors.size(); i++) {
            SeatState seatState = new SeatState();
            seatState.setFloor(floors.get(i).getEmployer());
            List<Seat> openSeatsByFid = seatService.findOpenSeatsByFid(floors.get(i).getFid());
            Integer nobook = 0;
            Integer bookNum = 0;
            Integer seatedNum = 0;
            Integer snapNum = 0;
            for (int j = 0; j < openSeatsByFid.size(); j++) {
                List<Booking> bookSeatBooking = bookingService.findBookSeatBookingday(openSeatsByFid.get(j).getSid(), 0);  //返回每个座位的所有预约
                Integer seatStatue = DateUtil.findSeatStatue(bookSeatBooking);  //计算当前时间座位状态
                switch (seatStatue) {
                    case 0:
                        nobook++;
                        break;
                    case 1:
                        bookNum++;
                        break;
                    case 2:
                        seatedNum++;
                        break;
                    case 3:
                        snapNum++;
                        break;
                    default:
                        break;
                }
            }
            seatState.setNobook(nobook);
            seatState.setBookNum(bookNum);
            seatState.setSeatedNum(seatedNum);
            seatState.setSnapNum(snapNum);
            seatStates.add(seatState);
        }
        return seatStates;
    }


    @RequestMapping("/news_List_User")
    public String news_List_User(Model model, Integer page, HttpSession httpSession) throws Exception {
        Integer pageSize = 5;


        List<Notice> allNotice = noticeService.findAllNotice();

        model.addAttribute("noticeSize", allNotice.size());
        int pageTims;
        if (allNotice.size() % pageSize == 0) {
            pageTims = allNotice.size() / pageSize;
        } else {
            pageTims = allNotice.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allNotice.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);


        List<Notice> allNoticepage = noticeService.findAllNoticepage(startRow, pageSize);

        if (allNoticepage.size() > 0) {
            model.addAttribute("notices", allNoticepage);
            return "user_page/News_List_User";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "user_page/News_List_User";
        }


    }

    /**
     * 查看某天所有的座位
     *
     * @param model
     * @param fid
     * @param day
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/book_Seat_User")
    public String book_Seat_User(Model model, Integer fid, Integer day, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");
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
        List<Booking> bookingBySno = bookingService.findBookingBySno(user.getSno(), day);
        List<BookingCusFloor> bookingCusFloorList = new ArrayList<BookingCusFloor>();
        for (int i = 0; i < bookingBySno.size(); i++) {
            Seat byid = seatService.findByid(bookingBySno.get(i).getSid());
            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setColumns(byid.getColumns());
            bookingSeat.setFid(byid.getFid());
            bookingSeat.setLeftside(byid.getLeftside());
            bookingSeat.setRow(byid.getRow());
            bookingSeat.setSeatnumber(byid.getSeatnumber());
            //添加楼层信息
            BookingCusFloor bookingCusFloor = new BookingCusFloor();
            BeanUtils.copyProperties(bookingSeat, bookingCusFloor);
            Floor floor = floorService.findfloorByid(byid.getFid());
            bookingCusFloor.setFloor(floor.getEmployer());
            Building buildingById = buildingService.findBuildingById(floor.getBid());
            bookingCusFloor.setBuilding(buildingById.getEmployer());
            bookingCusFloorList.add(bookingCusFloor);
        }
        model.addAttribute("seatsbooks", seatBookinges);
        model.addAttribute("day", day);
        model.addAttribute("fid", fid);
        model.addAttribute("bookings", bookingCusFloorList);

        return "user_page/Book_Seat_User";
    }

    /**
     * 用户提交预约记录
     *
     * @param model
     * @param seatNum
     * @param stime
     * @param etime
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/bookSeatUserSub")
    public String bookSeatUserSub(Model model, String seatNum, String stime, String etime, Integer day, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");
        Seat seatBynumber = seatService.findSeatBynumber(seatNum);
        Floor floor = floorService.findfloorByid(seatBynumber.getFid());

        List<UnDeal> unDealCord = undealService.findUnDealCord(user.getSno());
        Integer disTime1 = 0;
        if (unDealCord.size() > 0) {
            Date daysAfter = DateUtil.getDaysAfter(unDealCord.get(unDealCord.size() - 1).getRecord());
            disTime1 = DateUtil.getDisTime(new Date(), daysAfter);
        }
        if (disTime1 <= 0) {
            Integer disTime = DateUtil.getDisTime(new Date(), DateUtil.getDate(etime));

            if (disTime < CommenValue.MAX_LongTime) {
                WeekOpen weekOpen = weekOpenService.findopenFloortoday(floor.getFid());
                boolean b = DateUtil.getfollowTime(weekOpen, DateUtil.getDate(stime), DateUtil.getDate(etime));
                if (b) {
                    List<Booking> bookSeatBooking = bookingService.findBookSeatBooking(seatBynumber.getSid());
                    boolean checkbooksclash = DateUtil.checkbooksclash(bookSeatBooking, new Date(), DateUtil.getDate(etime));
                    if (checkbooksclash) {
                        model.addAttribute("error_msg", "您选择的时间段已经被占用");
                    } else {
                        //查看近两天的学生预约记录
                        List<Booking> bookingBySno = bookingService.findBookingBySno(user.getSno(), 0);
                        List<Booking> bookingBySno2 = bookingService.findBookingBySno(user.getSno(), 1);
                        for (int i = 0; i < bookingBySno2.size(); i++) {
                            bookingBySno.add(bookingBySno2.get(i));
                        }
                        boolean checkbooksclash1 = DateUtil.checkbooksclash(bookingBySno, DateUtil.getDate(stime), DateUtil.getDate(etime));
                        if (checkbooksclash1) {
                            model.addAttribute("error_msg", "您选择的时间段您已预约过");
                        } else {
                            Booking booking = new Booking();
                            booking.setSno(user.getSno());
                            booking.setBstime(DateUtil.getDate(stime));
                            booking.setBetime(DateUtil.getDate(etime));
                            booking.setSid(seatBynumber.getSid());
                            bookingService.insertbooking(booking);
                        }
                    }
                } else {
                    model.addAttribute("error_msg", "请注意场馆开放时间");
                }
            } else {
                model.addAttribute("error_msg", "选择时间超过" + (CommenValue.MAX_LongTime / 60) + "小时");
            }
        } else {
            model.addAttribute("error_msg", "目前还在惩罚时间内");
        }

        return "redirect:/jsp/book_Seat_User?fid=" + floor.getFid() + "&day=" + day;
    }


    /**
     * 释放预约
     *
     * @param model
     * @param bid
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/releaseUserBook")
    public String releaseUserBook(Model model, Integer bid, Integer page, HttpSession httpSession) throws Exception {

        User user = (User) httpSession.getAttribute("user");
        UserLearn bookByid = null;
        try {
            bookByid = userLearnService.findBookByid(bid);

            String[] period = bookByid.getPeriod().split("--");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String format = simpleDateFormat.format(new Date());

            Integer disTime = DateUtil.getDisTime(period[0], format);

            if (disTime > CommenValue.MAX_LATER) {
                userLearnService.updateUnpromise(bookByid.getBid());
            } else {
                userLearnService.deleteBook(bid);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "释放失败！");
        }
        String floor = bookByid.getSeatnumber().substring(0, 2);
        int pageSize = 5;
        List<Seat> allNoSeat = userLearnService.findAllNoSeat(floor);

        if (allNoSeat.size() > 0) {
            model.addAttribute("SeatSize", allNoSeat.size());
            int pageTims;
            if (allNoSeat.size() % pageSize == 0) {
                pageTims = allNoSeat.size() / pageSize;
            } else {
                pageTims = allNoSeat.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (allNoSeat.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            model.addAttribute("floor", floor);
            List<Seat> allNoSeatPage = userLearnService.findAllNoSeatPage(floor, startRow, pageSize);
            model.addAttribute("seats", allNoSeatPage);
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "暂无空闲座位，请稍后查看！");
        }

        UserLearn userLearnNew = userLearnService.findUserLearnNew(user.getUid());
        if (userLearnNew != null) {
            model.addAttribute("userLearn", userLearnNew);
        }
        return "user_page/Book_Seat_User";

    }


}
