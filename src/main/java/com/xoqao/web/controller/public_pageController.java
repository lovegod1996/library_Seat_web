package com.xoqao.web.controller;

import com.xoqao.web.bean.news.News;
import com.xoqao.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class public_pageController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/header_Admin")
    public String header_Admin(Model model) throws Exception {
        return "public_page/Header_Admin";
    }

    @RequestMapping("/leftmenu")
    public String leftmenu(Model model) throws Exception {
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
        News newsByid = newsService.findNewsByid(nid);
        if(newsByid!=null){
            model.addAttribute("news",newsByid);
            return "public_page/News_Content";
        }else{
            model.addAttribute("error_msg","暂无资讯信息");
            return "public_page/News_Content";
        }
    }
}
