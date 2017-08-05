package com.xoqao.web.controller;


import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class public_pageController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private WeekOpenService weekOpenService;

    @RequestMapping("/header_Admin")
    public String header_Admin(Model model) throws Exception {
        return "public_page/Header_Admin";
    }

    @RequestMapping("/leftmenu")
    public String leftmenu(Model model, HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Seat> seatsByFid = seatService.findSeatsByFid(floor.getFid());
        model.addAttribute("seatSize", seatsByFid.size());
        Building buildingById = buildingService.findBuildingById(floor.getBid());
        model.addAttribute("building", buildingById);
        List<BookingSeat> seatInSeat = bookingService.findSeatInSeat(floor.getFid());
        model.addAttribute("inSeat", seatInSeat.size());
        List<Seat> canBookingToday = bookingService.findCanBookingToday(floor.getFid());
        model.addAttribute("canbook", canBookingToday.size());
        WeekOpen weekOpen = weekOpenService.findopenFloortoday(floor.getFid());
        model.addAttribute("opentoday", weekOpen);
        return "public_page/Leftmenu";
    }

    @RequestMapping("/login")
    public String login(Model model) throws Exception {
        return "public_page/Login";
    }

    @RequestMapping("/header_User")
    public String header_User(Model model) throws Exception {
        return "public_page/Header_User";
    }

    @RequestMapping("/news_Content")
    public String news_Content(Model model, Integer nid) throws Exception {
        Notice noticeByid = noticeService.findNoticeByid(nid);
        if (noticeByid != null) {
            model.addAttribute("notice", noticeByid);
            return "public_page/News_Content";
        } else {
            model.addAttribute("error_msg", "暂无资讯信息");
            return "public_page/News_Content";
        }
    }

    @RequestMapping("/footer")
    public String footer(Model model) throws Exception{
        return "public_page/Footer";
    }
}
