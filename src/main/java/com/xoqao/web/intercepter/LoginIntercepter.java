package com.xoqao.web.intercepter;

import com.xoqao.web.bean.admin.Admin;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.interceptor.Intercepter;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.service.InterCeptorService;
import com.xoqao.web.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: lovegod
 * Created by 123 on 2017/2/26.
 */
public class LoginIntercepter implements HandlerInterceptor {
    @Autowired
    private InterCeptorService interCeptorService;


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //注入userService
        HttpSession session = httpServletRequest.getSession();
        interCeptorService = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(InterCeptorService.class);
        //将需要验证的页面添加到urlList；
        //urlList.add();
        String url = httpServletRequest.getServletPath();
        //截取路径
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }

        List<Intercepter> allIntoceptor = interCeptorService.findAllIntoceptor();

        for (int i = 0; i < allIntoceptor.size(); i++) {
            if (url.contains(allIntoceptor.get(i).getUri())) {
                Intercepter intercepter = allIntoceptor.get(i);
                switch (intercepter.getType()) {
                    case 1:  //学生
                        User user = (User) session.getAttribute("user");
                        if (user == null) {
                            session.setAttribute("orderPage", url);
                            Map<String, Object> map = new HashMap<String, Object>();
                            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
                            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                                if (entry.getValue()[0].length() < 4) {
                                    boolean integer = CodeUtils.isInteger(entry.getValue()[0]);
                                    if (integer) {
                                        map.put(entry.getKey(), Integer.parseInt(entry.getValue()[0]));
                                    } else {
                                        map.put(entry.getKey(), entry.getValue()[0]);
                                    }
                                } else {
                                    map.put(entry.getKey(), entry.getValue()[0]);
                                }
                            }
                            session.setAttribute("parameterMap", map);
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/login';</script>");
//                            httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        }
                        break;
                    case 2://场馆管理员
                        Floor floor = (Floor) session.getAttribute("admin");
                        if (floor == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
//                            httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        }
                        break;
                    case 3:  //楼
                        Building building = (Building) session.getAttribute("admin");
                        if (building == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
//                            httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        }
                        break;
                    case 4:  //管理员
                        Admin admin = (Admin) session.getAttribute("admin");
                        if (admin == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
//                            httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        }
                        break;
                    case 5:  //消息管理员
                        Admin admin1 = (Admin) session.getAttribute("admin");
                        if (admin1 == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
                            //  httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        } else {
                            if (admin1.getNotice() == 0) {   //管理员没有这个权限
                                httpServletResponse.setContentType("text/html;charset=UTF-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                out.println("<script type='text/javascript'>alert('您没有这个权限！');location='../jsp/main_Admin';</script>");
//                                httpServletRequest.getRequestDispatcher("/jsp/main_Admin").forward(httpServletRequest, httpServletResponse);
                                return false;
                            }
                        }
                        break;
                    case 6:  //楼层管理员
                        Admin admin2 = (Admin) session.getAttribute("admin");
                        if (admin2 == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
                            // httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        } else {
                            if (admin2.getFloor() == 0) {   //管理员没有这个权限
                                httpServletResponse.setContentType("text/html;charset=UTF-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                out.println("<script type='text/javascript'>alert('您没有这个权限！');location='../jsp/main_Admin';</script>");
                                //  httpServletRequest.getRequestDispatcher("/jsp/main_Admin").forward(httpServletRequest, httpServletResponse);
                                return false;
                            }
                        }
                        break;
                    case 7:  //学生管理员
                        Admin admin3 = (Admin) session.getAttribute("admin");
                        if (admin3 == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
                            // httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        } else {
                            if (admin3.getUser() == 0) {   //管理员没有这个权限
                                httpServletResponse.setContentType("text/html;charset=UTF-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                out.println("<script type='text/javascript'>alert('您没有这个权限！');location='../jsp/main_Admin';</script>");
                                //  httpServletRequest.getRequestDispatcher("/jsp/main_Admin").forward(httpServletRequest, httpServletResponse);
                                return false;
                            }
                        }
                        break;
                    case 8:  //学生管理员
                        Admin admin4 = (Admin) session.getAttribute("admin");
                        if (admin4 == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
                            // httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        } else {
                            if (admin4.getSeat() == 0) {   //管理员没有这个权限
                                httpServletResponse.setContentType("text/html;charset=UTF-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                out.println("<script type='text/javascript'>alert('您没有这个权限！');location='../jsp/main_Admin';</script>");
                                // httpServletRequest.getRequestDispatcher("/jsp/main_Admin").forward(httpServletRequest, httpServletResponse);
                                return false;
                            }
                        }
                        break;
                    case 9:  //学生管理员
                        Admin admin5 = (Admin) session.getAttribute("admin");
                        if (admin5 == null) {
                            httpServletResponse.setContentType("text/html;charset=UTF-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            out.println("<script type='text/javascript'>alert('请您先登录！');location='../jsp/main_User';</script>");
                            // httpServletRequest.getRequestDispatcher("/jsp/main_User").forward(httpServletRequest, httpServletResponse);
                            return false;
                        } else {
                            if (admin5.getAdmin() == 0) {   //管理员没有这个权限
                                httpServletResponse.setContentType("text/html;charset=UTF-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                out.println("<script type='text/javascript'>alert('您没有这个权限！');location='../jsp/main_Admin';</script>");
                                // httpServletRequest.getRequestDispatcher("/jsp/main_Admin").forward(httpServletRequest, httpServletResponse);
                                return false;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
//        //获取请求url
//        List<String> list = new ArrayList<String>();
//        list.add("phone");
//        list.add("index.jsp");
//        list.add("header_User");
//        list.add("header_User");
//        list.add("login");
//        list.add("footer");
//        list.add("login_ForAdmin");


//        String url1 = httpServletRequest.getRequestURI();
////        for (int i = 0; i < list.size(); i++) {
////            if (url.indexOf(list.get(i)) > 0) {
////                return true;
////            }
////        }
//
//        //这里公开地址就是登陆提交的地址
////        if(url.indexOf("login.form")>0){
////            return true;
////        }
//
//        //判断session
////        HttpSession session = httpServletRequest.getSession();
//        //从session中取出用户的身份信息
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            //身份验证通过，放行
//            return true;
//        }
//
//        Integer type = (Integer) session.getAttribute("admintype");
//        if (type != null) {
//            return true;
//        }
//        //执行到这里表示用户身份需要认证，跳转登陆界面
//        httpServletRequest.getRequestDispatcher("/public_page/Login.jsp").forward(httpServletRequest, httpServletResponse);

        return true;
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
