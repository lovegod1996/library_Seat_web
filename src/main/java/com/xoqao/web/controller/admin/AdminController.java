package com.xoqao.web.controller.admin;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/11.
 * Email:dx96_j@163.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
   private  AdminService adminService;



    /**
     * 后台登录提交
     * @param model
     * @param loginId //输入的登录类型参数
     * @param password //密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginSub")
    public String adminLoginSub(Model model, String loginId, String password, HttpSession httpSession)throws Exception{
        Admin admin = adminService.findadminBynameOrid(loginId);
          if(password.trim().equals(admin.getPassword())){
              httpSession.setAttribute("admin",admin);
              return "admin_page/Index_Admin";
          }else{
              model.addAttribute("error_msg","密码输入错误！");
              return "public_page/Login";
          }
    }

    /**
     * 退出登录
     * @param model
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginOut")
public String adminLogout(Model model,HttpSession httpSession)throws Exception{
        httpSession.removeAttribute("admin");
        return "public_page/Login";
}


}
