package com.xoqao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class Public_Controller {

    @RequestMapping("/page404")
    public String page404 (Model model) throws Exception{
        return "public_page/404";
    }
}
