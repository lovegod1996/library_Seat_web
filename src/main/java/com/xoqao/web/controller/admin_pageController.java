package com.xoqao.web.controller;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.booking.SeatBookings;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.deal.UnDeal;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.bean.news.News;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class admin_pageController {

    @Autowired
    private SeatService seatService;
    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;


    @Autowired
    private BookingService bookingService;

    @Autowired
    private WeekOpenService weekOpenService;

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UndealService undealService;
    @Autowired
    private FloorService floorService;

    @RequestMapping("/index_Admin")
    public String index_Admin(Model model, HttpSession httpSession) throws Exception {
        Integer type = (Integer) httpSession.getAttribute("admintype");
        if (type == 1) {
            Floor floorBycount = (Floor) httpSession.getAttribute("admin");
            return "admin_page/Index_Admin";
        } else if (type == 2) {
            Building buildAdminByCount = (Building) httpSession.getAttribute("admin");
            return "buildingadmin_page/Index_BuildingAdmin";
        } else if (type == 3) {
            Admin adminByCount = (Admin) httpSession.getAttribute("admin");
            return "superadmin_page/Index_SuperAdmin";
        }
        return "admin_page/Index_Admin";
    }

    @RequestMapping("/main_Admin")
    public String main_Admin(Model model) throws Exception {
        List<Notice> allNoticetop = noticeService.findAllNoticetop();
        model.addAttribute("noticeTop", allNoticetop);
        List<Building> allBuilding = buildingService.findAllBuilding();
        model.addAttribute("buildings", allBuilding);
        return "admin_page/Main_Admin";
    }

    @RequestMapping("/seat_In_Use")
    public String seat_In_Use(Model model, Integer page, HttpSession httpSession) throws Exception {
        int pageSize = 5;
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<BookingSeat> seatInSeat = bookingService.findSeatInSeat(floor.getFid());
        if (seatInSeat.size() > 0) {
            model.addAttribute("inSeatSize", seatInSeat.size());
            int pageTims;
            if (seatInSeat.size() % pageSize == 0) {
                pageTims = seatInSeat.size() / pageSize;
            } else {
                pageTims = seatInSeat.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (seatInSeat.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<BookingSeat> seatInSeatPage = bookingService.findSeatInSeatPage(floor.getFid(), startRow, pageSize);
            model.addAttribute("inseats", seatInSeatPage);
        } else {
            model.addAttribute("nullList", "暂无入座数据");
        }
        return "admin_page/Seat_In_Use";

    }

    /**
     * 查看当前已预约的座位信息状况
     *
     * @param model
     * @param page
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/seat_In_Book")
    public String seat_In_Book(Model model, Integer page, HttpSession httpSession) throws Exception {
        Integer pageSize = 6;
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Seat> bookSeat = bookingService.findBookSeat(floor.getFid());
        if (bookSeat.size() > 0) {
            model.addAttribute("bookSeatSize", bookSeat.size());
            int pageTims;
            if (bookSeat.size() % pageSize == 0) {
                pageTims = bookSeat.size() / pageSize;
            } else {
                pageTims = bookSeat.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (bookSeat.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Seat> bookSeatpage = bookingService.findBookSeatpage(floor.getFid(), startRow, pageSize);//返回当前有预约的所有座位
            List<SeatBookings> seatBookinges = new ArrayList<SeatBookings>();
            for (int i = 0; i < bookSeatpage.size(); i++) {
                SeatBookings seatBookings = new SeatBookings();
                BeanUtils.copyProperties(bookSeatpage.get(i), seatBookings);
                List<Booking> bookSeatBooking = bookingService.findBookSeatBooking(bookSeatpage.get(i).getSid());  //返回每个座位的所有预约
                Integer seatStatue = DateUtil.findSeatStatue(bookSeatBooking);  //计算当前时间座位状态
                seatBookings.setSeatStatue(seatStatue);
                seatBookings.setBookings(bookSeatBooking);
                seatBookinges.add(seatBookings);
            }
            model.addAttribute("seatbooks", seatBookinges);
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "暂无数据");
        }
        return "admin_page/Seat_In_Book";

    }


    /**
     * 添加空闲座位立即入座
     *
     * @param model
     * @param sno
     * @param seatNum
     * @param etime
     * @return
     * @throws Exception
     */
    @RequestMapping("/bookEmptSeat")
    public String bookEmptSeat(Model model, String sno, String seatNum, String etime, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        Integer disTime = DateUtil.getDisTime(new Date(), DateUtil.getTimeDate(etime, 0));
        if (disTime < CommenValue.MAX_LongTime&&disTime>CommenValue.MIN_BOOK) {
            Floor floor = (Floor) httpSession.getAttribute("admin"); //获取当前楼层信息
            WeekOpen weekOpen = weekOpenService.findopenFloortoday(floor.getFid());
            boolean b = DateUtil.getfollowTime(weekOpen, new Date(), DateUtil.getTimeDate(etime, 0));
            if (b) {
                Seat seatBynumber = seatService.findSeatBynumber(seatNum);
                List<Booking> bookSeatBooking = bookingService.findBookSeatBooking(seatBynumber.getSid());
                boolean checkbooksclash = DateUtil.checkbooksclash(bookSeatBooking, new Date(), DateUtil.getTimeDate(etime, 0));
                if (checkbooksclash) {
                    redirectAttributes.addFlashAttribute("error_msg", "您选择的时间段已经被占用");
//                    model.addAttribute("error_msg", "您选择的时间段已经被占用");
                } else {
                    User userBySno = userService.findUserBySno(sno);
                    if (userBySno != null) {
                        //查看近两天的学生预约记录
                        List<Booking> bookingBySno = bookingService.findBookingBySno(sno, 0);
                        List<Booking> bookingBySno2 = bookingService.findBookingBySno(sno, 1);
                        for (int i = 0; i < bookingBySno2.size(); i++) {
                            bookingBySno.add(bookingBySno2.get(i));
                        }
                        boolean checkbooksclash1 = DateUtil.checkbooksclash(bookingBySno, new Date(), DateUtil.getTimeDate(etime, 0));
                        if (checkbooksclash1) {
                            redirectAttributes.addFlashAttribute("error_msg", "您选择的时间段您已预约过");
                        } else {
                            Booking booking = new Booking();
                            booking.setBstime(new Date());
                            booking.setBetime(DateUtil.getTimeDate(etime, 0));
                            booking.setSno(sno);
                            booking.setSid(seatBynumber.getSid());
                            booking.setStime(new Date());
                            try {
                                bookingService.insertbookingnow(booking);
                                redirectAttributes.addFlashAttribute("error_msg", "预约成功");
                            } catch (Exception e) {
                                redirectAttributes.addFlashAttribute("error_msg", "您选择的时间段已经被占用");
                            }
                        }
                        redirectAttributes.addFlashAttribute("error_msg", "预约成功");
                        return "redirect:/jsp/seat_In_Use?page=1";
                    } else {
                        redirectAttributes.addFlashAttribute("error_msg", "学号不存在");
                        return "redirect:/jsp/seat_In_Book?page=1";
                    }
                }
            } else {
//                model.addAttribute("error_msg", "请注意开放场馆时间");
                redirectAttributes.addFlashAttribute("error_msg", "请注意开放场馆时间");
            }
        } else {
//            model.addAttribute("error_msg", "您选择的时间超过" + (CommenValue.MAX_LongTime / 60) + "小时");
            redirectAttributes.addFlashAttribute("error_msg", "您选择的时间超过" + (CommenValue.MAX_LongTime / 60) + "小时或时间过短");
        }
        return "redirect:/jsp/seat_In_Book?page=1";
    }

    /**
     * 查看当天空闲座位
     *
     * @param model
     * @param page
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/seat_In_Empty")
    public String seat_In_Empty(Model model, Integer page, HttpSession httpSession) throws Exception {

        Integer pageSize = 6;
        Floor floor1 = (Floor) httpSession.getAttribute("admin");

        List<Seat> canBookingToday = bookingService.findCanBookingToday(floor1.getFid());
        if (canBookingToday.size() > 0) {
            model.addAttribute("seatSize", canBookingToday.size());
            int pageTims;
            if (canBookingToday.size() % pageSize == 0) {
                pageTims = canBookingToday.size() / pageSize;
            } else {
                pageTims = canBookingToday.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值
            if (null == page) {
                page = 1;
            }
            //每页开始的第几条记录
            int startRow;
            if (canBookingToday.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Seat> canBookingTodayPage = bookingService.findCanBookingTodayPage(floor1.getFid(), startRow, pageSize);
            model.addAttribute("seats", canBookingTodayPage);
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "暂无未预约座位");
        }

        return "admin_page/Seat_In_Empty";
    }

    @RequestMapping("/add_News")
    public String add_News(Model model) throws Exception {
        return "admin_page/Add_News";
    }

    @RequestMapping("/news_List_Admin")
    public String news_List_Admin(Model model, Integer page, HttpSession httpSession) throws Exception {
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
            return "admin_page/News_List_Admin";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/News_List_Admin";
        }
    }

    /**
     * 空闲座位直接入座
     *
     * @param model
     * @param seatNum
     * @param sno
     * @param etime
     * @param page
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/adSeatBookSub")
    public String adSeatBook(Model model, String seatNum, String sno, String etime, Integer page, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        Date date = DateUtil.getTimeDate(etime, 0);
        Integer disTime = DateUtil.getDisTime(new Date(), date);
        if (disTime > CommenValue.MAX_LongTime||disTime<CommenValue.MIN_BOOK) {
            redirectAttributes.addFlashAttribute("error_msg", "您选择的时间超过" + (CommenValue.MAX_LongTime / 60) + "小时或预约时间过短");
        } else {
            //计算预约惩罚天数据周期
            List<UnDeal> unDealCord = undealService.findUnDealCord(sno);
            Integer disTime1 = 0;
            if (unDealCord.size() > 0) {
                Date daysAfter = DateUtil.getDaysAfter(unDealCord.get(unDealCord.size() - 1).getRecord());
                //暂不提供最后一天预约第二天的预约
                disTime1 = DateUtil.getDisTime(new Date(), daysAfter);
            }
            if (disTime1 <= 0) {
                Seat seatBynumber = seatService.findSeatBynumber(seatNum);
                Floor floor = floorService.findfloorByid(seatBynumber.getFid());
                WeekOpen weekOpen = weekOpenService.findopenFloorday(floor.getFid(), 1);
                boolean b = DateUtil.getfollowTime(weekOpen, new Date(), DateUtil.getTimeDate(etime, 0));
                if (b) {
                    List<Booking> bookSeatBooking = bookingService.findBookSeatBookingday(seatBynumber.getSid(), 0);
                    boolean checkbooksclash = DateUtil.checkbooksclash(bookSeatBooking, new Date(), DateUtil.getTimeDate(etime, 0));
                    if (checkbooksclash) {
                        redirectAttributes.addFlashAttribute("error_msg", "您选择的时间段已经被占用");
                    } else {
                        User userBySno = userService.findUserBySno(sno);
                        if(userBySno!=null) {
                            //查看近两天的学生预约记录
                            List<Booking> bookingBySno = bookingService.findBookingBySno(sno, 0);
                            List<Booking> bookingBySno2 = bookingService.findBookingBySno(sno, 1);
                            for (int i = 0; i < bookingBySno2.size(); i++) {
                                bookingBySno.add(bookingBySno2.get(i));
                            }
                            boolean checkbooksclash1 = DateUtil.checkbooksclash(bookingBySno, new Date(), DateUtil.getTimeDate(etime, 0));
                            if (checkbooksclash1) {
                                redirectAttributes.addFlashAttribute("error_msg", "您选择的时间段您已预约过");
                            } else {
                                Booking booking = new Booking();
                                booking.setBstime(new Date());
                                booking.setBetime(date);
                                booking.setSno(sno);
                                booking.setSid(seatBynumber.getSid());
                                booking.setStime(new Date());
                                try {
                                    bookingService.insertbookingnow(booking);
                                    redirectAttributes.addFlashAttribute("error_msg", "预约成功");
                                } catch (Exception e) {
                                    redirectAttributes.addFlashAttribute("error_msg", "您选择的时间段已经被占用");
                                }
                            }
                        }else{
                            redirectAttributes.addFlashAttribute("error_msg", "学号不存在");
                            return "redirect:/jsp/seat_In_Empty?page=1";
                        }
                    }
                } else {
                    redirectAttributes.addFlashAttribute("error_msg", "请注意场馆开放时间");
                }
            } else {
                redirectAttributes.addFlashAttribute("error_msg", "您的失信记录过多，正在惩罚时间内");
            }
        }
        return "redirect:/jsp/seat_In_Empty?page=1";
    }

    /**
     * 释放已经入座的座位
     *
     * @param model
     * @param bid
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("/releaseSeat")
    public String realseSeat(Model model, Integer bid, Integer type) throws Exception {
        Booking byid = bookingService.findByid(bid);
        Integer disTime = DateUtil.getDisTime(new Date(), byid.getBetime());
        if (disTime < CommenValue.MAX_TIME) {
            bookingService.updateEtime(new Date(), 3, 0, 0, bid);
        } else {
            if (type == 0) {  //判断是否是本人释放
                bookingService.updateEtime(new Date(), 3, 0, 0, bid);
            } else {   //他人释放
                if (byid.getStatue() == 2) {   //判断是否已经是已经离开状态
                    Integer disTime1 = DateUtil.getDisTime(byid.getEtime(), new Date());
                    if (disTime1 > byid.getDelay()) {  //判断是否已经超时
                        bookingService.updateDeal(1, 3, bid);//添加失信
                        /**
                         * 失信处理
                         */
                        List<UnDeal> unDealCord = undealService.findUnDealCord(byid.getSno());
                        if (unDealCord.size() > 0) {
                            List<Booking> userBookDeal = bookingService.findUserBookDeal(byid.getSno(), 1, unDealCord.get(unDealCord.size() - 1).getRecord());
                            if (userBookDeal.size() >= CommenValue.MAX_DEAL) {  //失信超过一定次数
                                undealService.insertUndeal(byid.getSno(), new Date());
                            }
                        } else {
                            List<Booking> finduserbookpromise = bookingService.finduserbookpromise(byid.getSno(), 1);
                            if (finduserbookpromise.size() >= CommenValue.MAX_DEAL) {   //失信超过一定次数
                                undealService.insertUndeal(byid.getSno(), new Date());
                            }
                        }
                    }
                } else {
                    bookingService.updateEtime(new Date(), 2, CommenValue.MAX_DELAY, 0, bid);
                }
            }
        }
        return "redirect:/jsp/seat_In_Use?page=1";
    }

    /**
     * 释放预约
     *
     * @param model
     * @param bid
     * @param page
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/releaseBook")
    public String releaseBook(Model model, Integer bid, Integer page, HttpSession httpSession) throws Exception {

        return "admin_page/Seat_In_Empty";
    }

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}
