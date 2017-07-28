package com.xoqao.web.utils;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.weekopen.WeekOpen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date ot = df.parse(ouTime);
            Date it = df.parse(inTime);
            long l = ot.getTime() - it.getTime();

            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

            System.out.println("" + day + "天" + hour + "小时" + min + "分");
            timedis = (int) (day * 24 * 60 + hour * 60 + min);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timedis;
    }

    public static Integer getDisTime(Date inTime, Date ouTime) {
        Integer timedis = 0;
        long l = ouTime.getTime() - inTime.getTime();

        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

        System.out.println("" + day + "天" + hour + "小时" + min + "分");
        timedis = (int) (day * 24 * 60 + hour * 60 + min);
        return timedis;
    }


    /**
     * 　　* 判断时间是否在时间段内 *
     * <p>
     * 　　* @param date
     * <p>
     * 　　* 当前时间 yyyy-MM-dd HH:mm:ss
     * <p>
     * 　　* @param strDateBegin
     * <p>
     * 　　* 开始时间 00:00:00
     * <p>
     * 　　* @param strDateEnd
     * <p>
     * 　　* 结束时间 00:05:00
     * <p>
     * 　　* @return
     * <p>
     */

    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
// 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
// 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
// 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
// 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
                    && strDateM <= strDateEndM) {
                return true;
// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM <= strDateEndM) {
                return true;
// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算座位当前时间状态
     *
     * @param bookings
     * @return
     */
    public static Integer findSeatStatue(List<Booking> bookings) {
        Integer statue = 0;
        for (int i = 0; i < bookings.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          boolean inDate = isInDate(new Date(), sdf.format(bookings.get(i).getBstime()), sdf.format(bookings.get(i).getBetime()));
            boolean b = belongCalendar(new Date(), bookings.get(i).getBstime(), bookings.get(i).getBetime());
            if (b) {
                if (bookings.get(i).getStime() == null) {  //如果还没有入座
                    statue = 1;
                } else {
                    if (bookings.get(i).getEtime() == null) {   //如果还没有离开
                        statue = 2;
                    } else {
                        if (bookings.get(i).getStatue() == 2) {
                            statue = 3;
                        } else {
                            statue = 0;
                        }
                    }
                }
            } else {
                statue = 0;
            }

        }
        return statue;
    }

    /**
     * 判断用户预约时间是否在开放时间段内
     *
     * @return
     */
    public static boolean getfollowTime(WeekOpen weekOpen, Date stime, Date etime) {
        boolean b = false;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date opentime = null;
        Date closeTime = null;
        try {
            String[] split = weekOpen.getParam1().split("-");
            opentime = df.parse(split[0]);
            closeTime = df.parse(split[1]);
            String format = df.format(stime);
            String format1 = df.format(etime);
            stime = df.parse(format);
            etime = df.parse(format1);
            boolean b1 = belongCalendar(stime, opentime, closeTime);
            if (b1) {
                boolean b2 = belongCalendar(etime, opentime, closeTime);
                if (b2) {
                    b = true;
                }
            } else {
                if (weekOpen.getParam2() != null) {
                    String[] split2 = weekOpen.getParam2().split("-");
                    opentime = df.parse(split2[0]);
                    closeTime = df.parse(split2[1]);
                    boolean b3 = belongCalendar(stime, opentime, closeTime);
                    if (b3) {
                        boolean b4 = belongCalendar(etime, opentime, closeTime);
                        if (b4) {
                            b = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 检查与某一预约是否右冲突
     *
     * @param booking
     * @param stime
     * @param etime
     * @return
     */
    public static boolean checkbookclash(Booking booking, Date stime, Date etime) {
        boolean bb = false;
        boolean b = belongCalendar(stime, booking.getBstime(), booking.getBetime());
        if (b) {
            bb = true;
        } else {
            boolean b1 = belongCalendar(etime, booking.getBstime(), booking.getBetime());
            if (b1) {
                bb = true;
            }
        }
        return bb;
    }


    /**
     * 检查预约时间是否有冲突
     *
     * @param bookings
     * @param stime
     * @param etime
     * @return
     */
    public static boolean checkbooksclash(List<Booking> bookings, Date stime, Date etime) {
        boolean b = false;
        for (int i = 0; i < bookings.size(); i++) {
            boolean checkbookclash = checkbookclash(bookings.get(i), stime, etime);
            if (checkbookclash) {
                b = true;
            }
        }
        return b;
    }

}
