package com.xoqao.web.controller.phone;

import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.service.NoticeService;
import com.xoqao.web.service.UserService;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/24.
 * Email:dx96_j@163.com
 */
@Controller
@RequestMapping("/phone")
public class PhoneServerController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;


    /**
     * 用户登录
     *
     * @param sno
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public
    @ResponseBody
    Map<String, Object> login(String sno, String password) throws Exception {
        User userBySno = userService.findUserBySno(sno);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userBySno != null && password.equals(userBySno.getPassword())) {
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", userBySno);
        } else {
            map.put("code", 1);
            map.put("message", "请输入正确参数");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 获取所有资讯
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allNotice", method = {RequestMethod.GET})
    public @ResponseBody
    Map<String, Object> allNotice() throws Exception {
        List<Notice> allNotice = noticeService.findAllNotice();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", allNotice);
        return map;
    }



}
