package com.xoqao.web.controller;

import com.xoqao.web.bean.news.News;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.seat.SeatState;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.service.NewsService;
import com.xoqao.web.service.NoticeService;
import com.xoqao.web.service.SeatService;
import com.xoqao.web.service.UserLearnService;
import com.xoqao.web.utils.DateUtil;
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
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class user_pageController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserLearnService userLearnService;
    @Autowired
    private SeatService seatService;

    @Autowired
    private NoticeService noticeService;
    @RequestMapping("/main_User")
    public String main_User(Model model) throws Exception {
<<<<<<< HEAD
        List<Notice> allNoticetop = noticeService.findAllNoticetop();
        model.addAttribute("noticestop",allNoticetop);
=======

//        List<News> allNewsTop = newsService.findAllNewsTop();
//        model.addAttribute("newsTop", allNewsTop);
>>>>>>> 2ef2feb752cc96347c20560a3a4cc001b48bcc32
        return "user_page/Main_User";
    }

    @RequestMapping("/getSeatData")
    public @ResponseBody
    List<SeatState> getSeatDate(String floor) throws Exception {
        if (null == floor) {
            floor = "南";
        }else{
            floor=floor.substring(0,1);
        }
        List<SeatState> seatStates = new ArrayList<SeatState>();
        List<String> floor1 = seatService.findFloor(floor);
        if (floor1.size() > 0) {
            for (int i = 0; i < floor1.size(); i++) {
                SeatState seatState = new SeatState();
                seatState.setFloor(floor1.get(i));
                Integer nobook = seatService.findCountBystate(floor1.get(i), 0);
                Integer bookNum = seatService.findCountBystate(floor1.get(i), 1);
                Integer seatedNum = seatService.findCountBystate(floor1.get(i), 2);
                seatState.setNobook(nobook);
                seatState.setBookNum(bookNum);
                seatState.setSeatedNum(seatedNum);
                seatStates.add(seatState);
            }
        }
        return seatStates;
    }


    @RequestMapping("/news_List_User")
    public String news_List_User(Model model, Integer page, HttpSession httpSession) throws Exception {
        Integer pageSize = 5;

        List<News> allNews = newsService.findAllNews();

        model.addAttribute("NewsSize", allNews.size());
        int pageTims;
        if (allNews.size() % pageSize == 0) {
            pageTims = allNews.size() / pageSize;
        } else {
            pageTims = allNews.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allNews.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);

        List<News> allNewsPage = newsService.findAllNewsPage(startRow, pageSize);
        if (allNewsPage.size() > 0) {
            model.addAttribute("news", allNewsPage);
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
        User user = (User) httpSession.getAttribute("user");

        String floor = seatNum.substring(0, 2);

        Integer disTime = DateUtil.getDisTime(stime, etime);
        if (disTime < CommenValue.MAX_LongTime) {

            List<UserLearn> userLearnPerByUid = userLearnService.findUserLearnPerByUid(user.getUid());
            if (userLearnPerByUid.size() < CommenValue.MAX_UNPERMISE) {
                Seat seatByNum = seatService.findSeatByNum(seatNum);
                try {
                    userLearnService.insertBook(user.getUid(), seatByNum.getSid(), new Date(), stime + "--" + etime);
                    seatService.updateSeat(1, user.getUid(), seatByNum.getSid());
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("error_msg", "预约失败");
                }
            } else {
                model.addAttribute("error_msg", "预约失败,您已失信超过" + CommenValue.MAX_UNPERMISE + "次");
            }
        } else {
            model.addAttribute("error_msg", "选取时间超过" + (CommenValue.MAX_LongTime / 60) + "小时");
        }
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
            seatService.updateSeat(0, null, bookByid.getSid());

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
