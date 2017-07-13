package com.xoqao.web.controller;

import com.xoqao.web.bean.news.News;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.service.NewsService;
import com.xoqao.web.service.UserLearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class admin_pageController {
    @Autowired
    private UserLearnService userLearnService;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/index_Admin")
    public String index_Admin(Model model) throws Exception {
        return "admin_page/Index_Admin";
    }

    @RequestMapping("/main_Admin")
    public String main_Admin(Model model) throws Exception {
        List<News> allNewsTop = newsService.findAllNewsTop();
        model.addAttribute("newsTop", allNewsTop);
        return "admin_page/Main_Admin";
    }

    @RequestMapping("/seat_In_Use")
    public String seat_In_Use(Model model, Integer page, String floor, HttpSession httpSession) throws Exception {

        int pageSize = 5;
        if (floor != null && floor.length() > 0) {
            if (floor.contains("%")) {
                floor = URLDecoder.decode(floor, "utf-8");
                floor = floor.replace("南", "S").replace("北", "N");
            } else {
                floor = floor.replace("南", "S").replace("北", "N");
            }
        }

        List<UserLearn> userLearns = userLearnService.findfloorAllSeat(floor);
        model.addAttribute("userLearnSize", userLearns.size());
        int pageTims;
        if (userLearns.size() % pageSize == 0) {
            pageTims = userLearns.size() / pageSize;
        } else {
            pageTims = userLearns.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (userLearns.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("floor", floor);
        List<UserLearn> userLearns1 = userLearnService.findfloorAllSeatPage(floor, startRow, pageSize);
        if (userLearns1.size() > 0) {
            model.addAttribute("userLearned", userLearns1);
            return "admin_page/Seat_In_Use";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Seat_In_Use";
        }

    }

    @RequestMapping("/seat_In_Book")
    public String seat_In_Book(Model model, Integer page, String floor, HttpSession httpSession) throws Exception {

        int pageSize = 5;
        if (floor != null && floor.length() > 0) {
            if (floor.contains("%")) {
                floor = URLDecoder.decode(floor, "utf-8");
                floor = floor.replace("南", "S").replace("北", "N");
            } else {
                floor = floor.replace("南", "S").replace("北", "N");
            }
        }

        List<UserLearn> userLearns = userLearnService.findfloorBookSeat(floor);
        model.addAttribute("userLearnSize", userLearns.size());
        int pageTims;
        if (userLearns.size() % pageSize == 0) {
            pageTims = userLearns.size() / pageSize;
        } else {
            pageTims = userLearns.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (userLearns.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("floor", floor);
        List<UserLearn> userLearns1 = userLearnService.findfloorBookSeatPage(floor, startRow, pageSize);
        if (userLearns1.size() > 0) {
            model.addAttribute("userLearns", userLearns1);
            return "admin_page/Seat_In_Book";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Seat_In_Book";
        }
    }

    @RequestMapping("/seat_In_Empty")
    public String seat_In_Empty(Model model, Integer page, String floor, HttpSession httpSession) throws Exception {

        int pageSize = 5;
        if (floor != null && floor.length() > 0) {
            if (floor.contains("%")) {
                floor = URLDecoder.decode(floor, "utf-8");
                floor = floor.replace("南", "S").replace("北", "N");
            } else {
                floor = floor.replace("南", "S").replace("北", "N");
            }
        }


        List<Seat> allNoSeat = userLearnService.findAllNoSeat(floor);
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
        if (allNoSeatPage.size() > 0) {
            model.addAttribute("seats", allNoSeatPage);
            return "admin_page/Seat_In_Empty";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Seat_In_Empty";
        }

    }

    @RequestMapping("/add_News")
    public String add_News(Model model) throws Exception {
        return "admin_page/Add_News";
    }

    @RequestMapping("/news_List_Admin")
    public String news_List_Admin(Model model, Integer page, HttpSession httpSession) throws Exception {

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
            return "admin_page/News_List_Admin";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/News_List_Admin";
        }
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
