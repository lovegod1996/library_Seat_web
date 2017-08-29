package com.xoqao.web.controller.admin;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.news.News;
import com.xoqao.web.bean.news.Notice;
import com.xoqao.web.bean.seat.Floors;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.BaiduPushUtils;
import com.xoqao.web.utils.CodeUtils;
import com.xoqao.web.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private UserService userService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private FloorService floorService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 后台登录提交
     *
     * @param model
     * @param loginId  //输入的登录类型参数
     * @param password //密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/userloginSub")
    public String userLoginSub(Model model, String loginId, String password, HttpSession httpSession,RedirectAttributes attr) throws Exception {
        User userByphoneOrSno = userService.findUserBySno(loginId);
        if (userByphoneOrSno!=null&& MD5Util.encode(password).equals(userByphoneOrSno.getPassword())) {
           userByphoneOrSno.setPassword(null);
            httpSession.setAttribute("user", userByphoneOrSno);
           String orderPage= (String) httpSession.getAttribute("orderPage");
            if (orderPage!=null){
                Map<String, Object> map = (Map<String, Object>) httpSession.getAttribute("parameterMap");
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                        attr.addAttribute(entry.getKey(),entry.getValue());
                }
                return "redirect:"+orderPage;
            }
            return "toIndex";
        } else {
            model.addAttribute("error_msg", "信息输入错误！");
            return "public_page/Login";
        }
    }

    /**
     * 管理员登录
     *
     * @param model
     * @param loginId
     * @param password
     * @param optionsRadiosinline
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/adminLoginSub")
    public String adminLoginSub(Model model, String loginId, String password, Integer optionsRadiosinline, HttpSession httpSession) throws Exception {
        if (optionsRadiosinline == 1) {
            Floor floorBycount = floorService.findFloorBycount(loginId);
            if (floorBycount != null && MD5Util.encode(password).equals(floorBycount.getPassword())) {
               floorBycount.setPassword(null);
                httpSession.setAttribute("admin", floorBycount);
                httpSession.setAttribute("admintype", 1);
                return "admin_page/Index_Admin";
            } else {
                model.addAttribute("error_msg", "信息输入错误！");
                return "public_page/Login_ForAdmin";
            }
        } else if (optionsRadiosinline == 2) {
            Building buildAdminByCount = buildingService.findBuildAdminByCount(loginId);
            if (buildAdminByCount != null && MD5Util.encode(password).equals(buildAdminByCount.getPassword())) {
               buildAdminByCount.setPassword(null);
                httpSession.setAttribute("admin", buildAdminByCount);
                httpSession.setAttribute("admintype", 2);
                return "buildingadmin_page/Index_BuildingAdmin";
            } else {
                model.addAttribute("error_msg", "信息输入错误！");
                return "public_page/Login_ForAdmin";
            }
        } else if (optionsRadiosinline == 3) {
            Admin adminByCount = adminService.findAdminByCount(loginId);
            if (adminByCount != null && MD5Util.encode(password).equals(adminByCount.getPassword())) {
               adminByCount.setPassword(null);
                httpSession.setAttribute("admin", adminByCount);
                httpSession.setAttribute("admintype", 3);
                return "superadmin_page/Index_SuperAdmin";
            } else {
                model.addAttribute("error_msg", "信息输入错误！");
                return "public_page/Login_ForAdmin";
            }
        } else {
            return "public_page/Login_ForAdmin";
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
        httpSession.invalidate();
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

        Notice notice = new Notice();

        Integer type = (Integer) httpSession.getAttribute("admintype");
        if (type == 1) {
            Floor floorBycount = (Floor) httpSession.getAttribute("admin");
            notice.setType(1);
            notice.setUid(floorBycount.getFid());
            notice.setWorkstation(floorBycount.getEmployer());
        } else if (type == 2) {
            Building buildAdminByCount = (Building) httpSession.getAttribute("admin");
            notice.setType(2);
            notice.setUid(buildAdminByCount.getBid());
            notice.setWorkstation(buildAdminByCount.getEmployer());
        } else if (type == 3) {
            Admin adminByCount = (Admin) httpSession.getAttribute("admin");
            notice.setType(3);
            notice.setUid(adminByCount.getAid());
            notice.setWorkstation(adminByCount.getEmployer());
        }
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreattime(new Date());
        try {
            noticeService.insertNotice(notice);
            Map<String, Object> map = BaiduPushUtils.pushMsgToAll("新资讯消息", title, System.currentTimeMillis() / 1000 + 120, 3600*5, 1, 2, null, 3, "");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Notice> allNotice = noticeService.findAllNotice();


        model.addAttribute("NewsSize", allNotice.size());
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

//        List<News> allNewsPage = newsService.findAllNewsPage(startRow, pageSize);
        List<Notice> allNoticepage = noticeService.findAllNoticepage(startRow, pageSize);
        if (allNoticepage.size() > 0) {
            model.addAttribute("notices", allNoticepage);
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
        noticeService.deleteNotice(nid);
        Integer pageSize = 5;
        List<Notice> allNotice = noticeService.findAllNotice();
        model.addAttribute("NoticeSize", allNotice.size());
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
            return "admin_page/News_List_Admin";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/News_List_Admin";
        }
    }

    /**
     * 进入编辑资讯
     *
     * @param model
     * @param nid
     * @return
     * @throws Exception
     */
    @RequestMapping("/editNews")
    public String newsEdit(Model model, Integer nid) throws Exception {
        Notice noticeByid = noticeService.findNoticeByid(nid);
        model.addAttribute("notice", noticeByid);
        return "admin_page/Edit_News";
    }

    /**
     * 更新资讯信息
     *
     * @param model
     * @param nid
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
    @RequestMapping("/editNewsSub")
    public String newsEditSub(Model model, Integer page, Integer nid, String title, String content, HttpSession httpSession) throws Exception {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setNid(nid);
        notice.setContent(content);
        notice.setCreattime(new Date());
        Integer type = (Integer) httpSession.getAttribute("admintype");
        if (type == 1) {
            Floor floorBycount = (Floor) httpSession.getAttribute("admin");
            notice.setType(1);
            notice.setUid(floorBycount.getFid());
            notice.setWorkstation(floorBycount.getEmployer());
        } else if (type == 2) {
            Building buildAdminByCount = (Building) httpSession.getAttribute("admin");
            notice.setType(2);
            notice.setUid(buildAdminByCount.getBid());
            notice.setWorkstation(buildAdminByCount.getEmployer());
        } else if (type == 3) {
            Admin adminByCount = (Admin) httpSession.getAttribute("admin");
            notice.setType(3);
            notice.setUid(adminByCount.getAid());
            notice.setWorkstation(adminByCount.getEmployer());
        }
        noticeService.updateNotice(notice);
        Integer pageSize = 5;
        List<Notice> allNotice = noticeService.findAllNotice();
        model.addAttribute("NoticeSize", allNotice.size());
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
            return "admin_page/News_List_Admin";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/News_List_Admin";
        }
    }

    /**
     * 获取所有楼层信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getfloors")
    public @ResponseBody
    Floors getFloors() throws Exception {

        return null;
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
