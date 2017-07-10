package com.xoqao.web.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lovegod
 * Created by 123 on 2017/2/26.
 */
public class LoginIntercepter implements HandlerInterceptor {





    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取请求url
        List<String> list=new ArrayList<String>();
        list.add("login.form");
        list.add("userRigist.form");
        list.add("userLogin.form");
        list.add("findUser.form");
        list.add("findMainMeeting.form");
        list.add("findleaveRecorde.form");
        list.add("finduserNoticeBefore.form");
        list.add("finduserNoticelate.form");
        list.add("finduserNoticeToday.form");
        list.add("wantLeave.form");
        list.add("userSign.form");

        String url=httpServletRequest.getRequestURI();
        for (int i=0;i<list.size();i++){
            if(url.indexOf(list.get(i))>0){
                return true;
            }
        }

        //这里公开地址就是登陆提交的地址
//        if(url.indexOf("login.form")>0){
//            return true;
//        }
        //判断session
        HttpSession session=httpServletRequest.getSession();
        //从session中取出用户的身份信息
        String username= (String) session.getAttribute("username");
        if(username!=null){
            //身份验证通过，放行
            return  true;
        }
        //执行到这里表示用户身份需要认证，跳转登陆界面
        httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest,httpServletResponse);

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
