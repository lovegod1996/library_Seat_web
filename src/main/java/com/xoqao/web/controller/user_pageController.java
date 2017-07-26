package com.xoqao.web.controller;

import com.xoqao.web.bean.building.Building;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    @RequestMapping("/main_User")
    public String main_User(Model model) throws Exception {
        List<Notice> allNoticetop = noticeService.findAllNoticetop();
        model.addAttribute("noticestop",allNoticetop);

        List<WeekOpen> findopentody = weekOpenService.findopentody();

        Iterator<WeekOpen> iterator = findopentody.iterator();
        List<WeekOpenCus> weekOpenCuses=new ArrayList<WeekOpenCus>();
        while (iterator.hasNext()){
            WeekOpen next = iterator.next();
            Floor floor = floorService.findfloorByid(next.getLid());
            Building buildingById = buildingService.findBuildingById(floor.getBid());
            WeekOpenCus weekOpenCus=new WeekOpenCus();
            BeanUtils.copyProperties(next,weekOpenCus);
            weekOpenCus.setFloor(floor.getEmployer());
            weekOpenCus.setBuilding(buildingById.getEmployer());
            weekOpenCuses.add(weekOpenCus);
        }
        model.addAttribute("weekopens",weekOpenCuses);
        return "user_page/Main_User";
    }

    @RequestMapping("/getSeatData")
    public @ResponseBody
    List<SeatState> getSeatDate(String floor) throws Exception {

        return null;
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

    @RequestMapping("/book_Seat_User")
    public String book_Seat_User(Model model, Integer page, String floor, HttpSession httpSession) throws Exception {

//        User user = (User) httpSession.getAttribute("user");
//
//        int pageSize = 5;
//        if (floor != null && floor.length() > 0) {
//            if (floor.contains("%")) {
//                floor = URLDecoder.decode(floor, "utf-8");
//                floor = floor.replace("南", "S").replace("北", "N");
//            } else {
//                floor = floor.replace("南", "S").replace("北", "N");
//            }
//        }
//
//        List<Seat> allNoSeat = userLearnService.findAllNoSeat(floor);
//        if (allNoSeat.size() > 0) {
//            model.addAttribute("SeatSize", allNoSeat.size());
//            int pageTims;
//            if (allNoSeat.size() % pageSize == 0) {
//                pageTims = allNoSeat.size() / pageSize;
//            } else {
//                pageTims = allNoSeat.size() / pageSize + 1;
//            }
//            httpSession.setAttribute("pageTimes", pageTims);
//            //页面初始的时候没有初试值
//            if (null == page) {
//                page = 1;
//            }
//            //每页开始的第几条记录
//            int startRow;
//            if (allNoSeat.size() < pageSize) {
//                startRow = 0;
//            } else {
//                startRow = (page - 1) * pageSize;
//            }
//            model.addAttribute("currentPage", page);
//            model.addAttribute("floor", floor);
//            List<Seat> allNoSeatPage = userLearnService.findAllNoSeatPage(floor, startRow, pageSize);
//            model.addAttribute("seats", allNoSeatPage);
//        } else {
//            httpSession.setAttribute("pageTimes", 1);
//            model.addAttribute("nullList", "暂无空闲座位，请稍后查看！");
//        }
//
//        UserLearn userLearnNew = userLearnService.findUserLearnNew(user.getUid());
//
//        if (userLearnNew != null) {
//            model.addAttribute("userLearn", userLearnNew);
//        }
        return "user_page/Book_Seat_User";
    }

    @RequestMapping("/bookSeatUserSub")
    public String bookSeatUserSub(Model model, String seatNum, String stime, String etime, Integer page, HttpSession httpSession) throws Exception {

        return "user_page/Book_Seat_User";
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
