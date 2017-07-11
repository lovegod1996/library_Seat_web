package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/10.
 */
@Controller
@RequestMapping("/jsp")
public class news_pageController {

    @RequestMapping("/news_Content")
    public String news_Content(Model model) throws Exception {
        return "news_page/News_Content";
    }

    @RequestMapping("/news_List")
    public String news_List(Model model) throws Exception {
        return "news_page/News_List";
    }
}
