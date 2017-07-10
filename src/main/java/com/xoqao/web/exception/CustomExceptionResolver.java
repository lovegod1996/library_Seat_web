package com.xoqao.web.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: lovegod
 * Created by 123 on 2016/11/26.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {


    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //handler就是处理器适配要执行handler对象
        //解析异常类型
        //如果该异常是系统自定义异常，直接取出一样信息，在错误也买能显示
//        String message=null;
//        if(e instanceof CustomException){
//message=((CustomException)e).getMessage();
//        }else{
//            message="未知错误";
//        }
        //上边代码变为
        CustomException customException=null;
        if(e instanceof CustomException){
            customException=(CustomException)e;
        }else{
            customException=new CustomException("该功能正在研发.......");
        }
        //错误信息
        String message=customException.getMessage();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message",message);
        //指向错误界面

        modelAndView.setViewName("err");

        return modelAndView;
    }
}
