package com.xoqao.web.controller.admin;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.bean.news.News;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.service.AdminService;
import com.xoqao.web.service.NewsService;
import com.xoqao.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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
    private AdminService adminService;

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    /**
     * 后台登录提交
     *
     * @param model
     * @param loginId  //输入的登录类型参数
     * @param password //密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginSub")
    public String adminLoginSub(Model model, String loginId, String password, Integer optionsRadiosinline, HttpSession httpSession) throws Exception {

        if (optionsRadiosinline == 1) {
            User userByphoneOrSno = userService.findUserByphoneOrSno(loginId);
            if (password.trim().equals(userByphoneOrSno.getPassword())) {
                httpSession.setAttribute("user", userByphoneOrSno);
                return "toIndex";
            } else {
                model.addAttribute("error_msg", "密码输入错误！");
                return "public_page/Login";
            }
        } else {
            Admin admin = adminService.findadminBynameOrid(loginId);
            if (password.trim().equals(admin.getPassword())) {
                httpSession.setAttribute("admin", admin);
                return "admin_page/Index_Admin";
            } else {
                model.addAttribute("error_msg", "密码输入错误！");
                return "public_page/Login";
            }
        }


    }

    /**
     * 退出登录
     *
     * @param model
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginOut")
    public String adminLogout(Model model, HttpSession httpSession) throws Exception {
        httpSession.removeAttribute("admin");
        httpSession.removeAttribute("user");
        return "toIndex";
    }

    /**
     * 添加资讯
     *
     * @param model
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
    @RequestMapping("/adNewsSub")
    public String newsSub(Model model, Integer page, String title, String content, HttpSession httpSession) throws Exception {
        Integer pageSize = 5;

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCreattime(new Date());
        try {
            newsService.insertNews(news);
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    /**
     * 删除资讯
     *
     * @param model
     * @param page
     * @param nid
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/newsDele")
    public String newsDelte(Model model, Integer page, Integer nid, HttpSession httpSession) throws Exception {
        newsService.deleteNews(nid);

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
