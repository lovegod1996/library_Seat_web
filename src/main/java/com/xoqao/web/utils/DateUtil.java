package com.xoqao.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
public class DateUtil {

    public static Date getDate(String time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date date = sdf.parse(time);
        return date;
    }

    public static Integer getDisTime(String inTime, String ouTime) {

        Integer timedis = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date ot = df.parse(ouTime);
            Date it = df.parse(inTime);
            long l = ot.getTime() - it.getTime();

            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

            System.out.println("" + day + "天" + hour + "小时" + min + "分" );
            timedis=(int)(day*24*60+hour*60+min);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timedis;
    }



}
