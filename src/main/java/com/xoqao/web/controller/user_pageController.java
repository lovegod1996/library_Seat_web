package com.xoqao.web.controller;

import com.xoqao.web.bean.news.News;
import com.xoqao.web.service.NewsService;
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
public class user_pageController {

    @Autowired
   private NewsService newsService;

    @RequestMapping("/main_User")
    public String main_User(Model model) throws Exception {

        List<News> allNewsTop = newsService.findAllNewsTop();
        model.addAttribute("newsTop", allNewsTop);
        return "user_page/Main_User";
    }

    @RequestMapping("/news_List_User")
    public String news_List_User(Model model,Integer page, HttpSession httpSession) throws Exception {
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
    public String book_Seat_User(Model model) throws Exception {
        return "user_page/Book_Seat_User";
    }
}
